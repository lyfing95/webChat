package com.rjxy.webchat.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxy.webchat.biz.MessageBiz;
import com.rjxy.webchat.entity.Message;
import com.rjxy.webchat.mapper.MessageMapper;


@Service(value="messageBiz")
public class MessageBizImpl implements MessageBiz{
	@Autowired
	private MessageMapper messageMapper;
	
	
	//��ȡ��֤��Ϣ�б����ݵ�½��ID��
	public List<Message> selectCheckMessages(String id){
		return messageMapper.selectCheckMessages(id);
	}
	//�Ķ�������Ϣ
		public List<Message> selectMessages(String fromid,String toid){
			//��ȡ������Ϣ
			Map<String,String> pmap = new HashMap<String,String>();
			pmap.put("fromid", fromid);
			pmap.put("toid", toid);
			List<Message> messageList=messageMapper.selectMessages(pmap);
			//����������Ϣ��״̬
			pmap.put("type", "1"); //��ʾ����������Ϣ��״̬
			messageMapper.updateMessageStatus(pmap);
			return messageList;
		}
	//�����Ϣ�ķ���
	public int insertMessage(Message message){
		return messageMapper.insert(message);
	}
	
	public int selectNoReadMessageCount(Map map){
		return messageMapper.selectNoReadMessageCount(map);
	}
	public MessageMapper getMessageMapper(){
		return messageMapper;
	}
	public void setMessageMapper(MessageMapper messageMapepr){
		this.messageMapper = messageMapper;
	}

}
