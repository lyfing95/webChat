package com.rjxy.webchat.mapper;

import java.util.List;
import java.util.Map;

import com.rjxy.webchat.entity.Chatuser;

public interface ChatuserMapper {
	//����һ�����ѵķ���
	public int insertFriendUser(Map map);
	//���ҵ�¼�û��Ĳ��Ǻ����б�
	public List<Chatuser> selectNotFriends(String id);
	//���ҵ�¼�û��ĺ����б�
	public List<Chatuser> selectUserFriends(String id);
	//��¼����������Map���󱣴���������
	public Chatuser selectLoginUser(Map map);
    int deleteByPrimaryKey(String id);

    int insert(Chatuser record);

    int insertSelective(Chatuser record);

    Chatuser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Chatuser record);

    int updateByPrimaryKey(Chatuser record);
}