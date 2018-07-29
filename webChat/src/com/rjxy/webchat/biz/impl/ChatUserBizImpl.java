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
	//�����û��Ļ�����Ϣ
	  public int updateChatUser(Chatuser user){
		  return chatuserMapper.updateByPrimaryKeySelective(user);
	  }
	//������֤��Ϣ
	  public int doCheckMessage(String mid,String resu){
		  //��ȡ��֤��Ϣ������
		 Message message = messageMapper.selectByPrimaryKey(mid); 
		  //�޸���Ϣ��״̬
		    Map<String, String> pmap = new HashMap<String, String>();
			pmap.put("fromid", message.getFromid());
			pmap.put("toid", message.getToid());
			pmap.put("type", "0"); //�޸���֤��Ϣ��״̬
			messageMapper.updateMessageStatus(pmap);
			//�жϴ�����
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

	// ��Ӻ���
	public int insertUserFriend(Chatuser loginuser, String friendid) {
		// ��ѯҪ��ӵĺ�����Ϣ
        Chatuser friend = chatuserMapper.selectByPrimaryKey(friendid);
		// �жϺ��������Ϣ
		if (friend.getChecktype() == 0) {
			// �������(ֻҪ��Ӻ��ѣ���˫����ӣ�
			Map<String, String> pmap = new HashMap<String, String>();
			pmap.put("mineid", loginuser.getId());
			pmap.put("friendid", friendid);
			chatuserMapper.insertFriendUser(pmap);
			pmap.put("mineid", friendid);
			pmap.put("friendid", loginuser.getId());
			chatuserMapper.insertFriendUser(pmap);
		} else {
			// ��Ҫ�����֤
			Message message = new Message();
			message.setFromid(loginuser.getId()); // ��¼�û�������Ϣ�ķ�����
			message.setId(DateUtil.getId()); // ���ù���������ID
			message.setToid(friendid);
			message.setContent(loginuser.getNickname() + "��Ҫ�������Ϊ����");
			message.setStatus(0); // δ����Ϣ
			message.setTime(new Date());
			message.setType(0); // ������Ϣ
			messageMapper.insert(message);
		}
		    return 0;
	}

	// ���ҵ�¼�û��Ĳ��Ǻ����б�
	public List<Chatuser> selectNotFriends(String id) {
		return chatuserMapper.selectNotFriends(id);
	}

	// ���ҵ�¼�û��ĺ����б�
	public List<Chatuser> selectUserFriends(String id) {
		return chatuserMapper.selectUserFriends(id);
	}

	// ʵ��ע���ҵ��
	public int insertUser(Chatuser user) {
		// ע��֮ǰ��֤�û���ID�������Ƿ����
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("userid", user.getUserid());
		paramMap.put("password", user.getPassword());
		Chatuser checkuser = chatuserMapper.selectLoginUser(paramMap);
		if (checkuser != null) {
			return -1; // ��ʾ�û��Ѵ���
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
