package com.rjxy.webchat.biz;

import java.util.List;
import java.util.Map;

import com.rjxy.webchat.entity.Chatuser;

public interface ChatUserBiz {
	//�����û��Ļ�����Ϣ
	  public int updateChatUser(Chatuser user);
	//������֤��Ϣ
	  public int doCheckMessage(String mid,String resu);
	//��Ӻ���
	  public int insertUserFriend(Chatuser loginuser,String friendid);
	//���ҵ�¼�û��Ĳ��Ǻ����б�
	  public List<Chatuser> selectNotFriends(String id);
	//���ҵ�¼�û��ĺ����б�
	  public List<Chatuser> selectUserFriends(String id);
	//ʵ�ֵ�¼ҵ��
      public Chatuser selectLoginUser (Map map);
    //ʵ��ע���ҵ��
      public int insertUser(Chatuser user);
}
