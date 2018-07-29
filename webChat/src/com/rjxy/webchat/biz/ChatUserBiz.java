package com.rjxy.webchat.biz;

import java.util.List;
import java.util.Map;

import com.rjxy.webchat.entity.Chatuser;

public interface ChatUserBiz {
	//更新用户的基础信息
	  public int updateChatUser(Chatuser user);
	//处理验证消息
	  public int doCheckMessage(String mid,String resu);
	//添加好友
	  public int insertUserFriend(Chatuser loginuser,String friendid);
	//查找登录用户的不是好友列表
	  public List<Chatuser> selectNotFriends(String id);
	//查找登录用户的好友列表
	  public List<Chatuser> selectUserFriends(String id);
	//实现登录业务
      public Chatuser selectLoginUser (Map map);
    //实现注册的业务
      public int insertUser(Chatuser user);
}
