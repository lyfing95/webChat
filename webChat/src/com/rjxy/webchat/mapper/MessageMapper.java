package com.rjxy.webchat.mapper;

import java.util.List;
import java.util.Map;

import com.rjxy.webchat.entity.Message;

public interface MessageMapper {
	//��ȡ��֤��Ϣ�б����ݵ�½��ID��
	public List<Message> selectCheckMessages(String id);
	//��ȡ������Ϣ�б�
	public List<Message> selectMessages(Map map);
	//������Ϣ��״̬
	public int updateMessageStatus(Map map);
	
	//��ѯ���ѷ��͸��ҵ�δ��ȡ����Ϣ����
	public int selectNoReadMessageCount(Map map);
	
    int deleteByPrimaryKey(String id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}