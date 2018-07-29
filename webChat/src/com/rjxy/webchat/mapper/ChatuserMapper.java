package com.rjxy.webchat.mapper;

import java.util.List;
import java.util.Map;

import com.rjxy.webchat.entity.Chatuser;

public interface ChatuserMapper {
	//增加一个好友的方法
	public int insertFriendUser(Map map);
	//查找登录用户的不是好友列表
	public List<Chatuser> selectNotFriends(String id);
	//查找登录用户的好友列表
	public List<Chatuser> selectUserFriends(String id);
	//登录方法（利用Map对象保存多个参数）
	public Chatuser selectLoginUser(Map map);
    int deleteByPrimaryKey(String id);

    int insert(Chatuser record);

    int insertSelective(Chatuser record);

    Chatuser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Chatuser record);

    int updateByPrimaryKey(Chatuser record);
}