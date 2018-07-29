package com.rjxy.webchat.biz;

import java.util.List;
import java.util.Map;

import com.rjxy.webchat.entity.Chatuser;
import com.rjxy.webchat.entity.Message;

public interface MessageBiz {
	
	//��ȡ��֤��Ϣ�б����ݵ�½��ID��
	public List<Message> selectCheckMessages(String id);
	//�Ķ�������Ϣ
	public List<Message> selectMessages(String fromid,String toid);
	//�����Ϣ�ķ���
	public int insertMessage(Message message);
	
   //��ѯ���ѷ��͸��ҵ�δ��ȡ��Ϣ����
	public int selectNoReadMessageCount(Map map);
}
