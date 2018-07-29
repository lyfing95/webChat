package com.rjxy.webchat.biz.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxy.webchat.biz.ChatUserBiz;
import com.rjxy.webchat.entity.Chatuser;
import com.rjxy.webchat.entity.Message;
import com.rjxy.webchat.mapper.ChatuserMapper;
import com.rjxy.webchat.mapper.MessageMapper;
import com.ryxy.webchat.util.DateUtil;

@Service(value = "chatUserBiz")
public class ChatUserBizImpl implements ChatUserBiz {
	@Autowired
	private ChatuserMapper chatuserMapper;
	@Autowired
	private MessageMapper messageMapper;
	//更新用户的基础信息
	  public int updateChatUser(Chatuser user){
		  return chatuserMapper.updateByPrimaryKeySelective(user);
	  }
	//处理验证消息
	  public int doCheckMessage(String mid,String resu){
		  //读取验证消息的内容
		 Message message = messageMapper.selectByPrimaryKey(mid); 
		  //修改消息的状态
		    Map<String, String> pmap = new HashMap<String, String>();
			pmap.put("fromid", message.getFromid());
			pmap.put("toid", message.getToid());
			pmap.put("type", "0"); //修改验证消息的状态
			messageMapper.updateMessageStatus(pmap);
			//判断处理结果
			if("yes".equals(resu)){
			pmap.put("mineid", message.getFromid());
			pmap.put("friendid", message.getToid());
			chatuserMapper.insertFriendUser(pmap);
			pmap.put("mineid", message.getToid());
			pmap.put("friendid",message.getFromid());
			chatuserMapper.insertFriendUser(pmap);
			}
			return 0;
	  }

	// 添加好友
	public int insertUserFriend(Chatuser loginuser, String friendid) {
		// 查询要添加的好友信息
        Chatuser friend = chatuserMapper.selectByPrimaryKey(friendid);
		// 判断好友身份信息
		if (friend.getChecktype() == 0) {
			// 允许添加(只要添加好友，则双向添加）
			Map<String, String> pmap = new HashMap<String, String>();
			pmap.put("mineid", loginuser.getId());
			pmap.put("friendid", friendid);
			chatuserMapper.insertFriendUser(pmap);
			pmap.put("mineid", friendid);
			pmap.put("friendid", loginuser.getId());
			chatuserMapper.insertFriendUser(pmap);
		} else {
			// 需要身份验证
			Message message = new Message();
			message.setFromid(loginuser.getId()); // 登录用户就是消息的发送者
			message.setId(DateUtil.getId()); // 利用工具类生产ID
			message.setToid(friendid);
			message.setContent(loginuser.getNickname() + "，要求添加你为好友");
			message.setStatus(0); // 未读消息
			message.setTime(new Date());
			message.setType(0); // 聊天消息
			messageMapper.insert(message);
		}
		    return 0;
	}

	// 查找登录用户的不是好友列表
	public List<Chatuser> selectNotFriends(String id) {
		return chatuserMapper.selectNotFriends(id);
	}

	// 查找登录用户的好友列表
	public List<Chatuser> selectUserFriends(String id) {
		return chatuserMapper.selectUserFriends(id);
	}

	// 实现注册的业务
	public int insertUser(Chatuser user) {
		// 注册之前验证用户的ID和密码是否存在
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", user.getUserid());
		paramMap.put("password", user.getPassword());
		Chatuser checkuser = chatuserMapper.selectLoginUser(paramMap);
		if (checkuser != null) {
			return -1; // 表示用户已存在
		}
		return chatuserMapper.insert(user);
	}

	public Chatuser selectLoginUser(Map map) {
		return chatuserMapper.selectLoginUser(map);

	}

	public ChatuserMapper getChatuserMapper() {
		return chatuserMapper;
	}

	public void setChatuserMapper(ChatuserMapper chatuserMapper) {
		this.chatuserMapper = chatuserMapper;
	}

}
