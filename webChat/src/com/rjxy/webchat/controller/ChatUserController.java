package com.rjxy.webchat.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rjxy.webchat.biz.ChatUserBiz;
import com.rjxy.webchat.biz.MessageBiz;
import com.rjxy.webchat.entity.Chatuser;
import com.rjxy.webchat.entity.Message;
import com.ryxy.webchat.util.DateUtil;

@Controller
//һ��·��
@RequestMapping(value="chatUser")
public class ChatUserController {
	@Autowired
    private MessageBiz messageBiz;
    @Autowired
    private ChatUserBiz chatUserBiz;
    
    @RequestMapping(value="updateuser")
    public String updateuser(Chatuser user){
    	//�����û�����
        chatUserBiz.updateChatUser(user);
    	//����������µ�¼
    	return "redirect:/login.jsp";
    }
    @RequestMapping(value="doCheckMessage")
    public String doCheckMessage(String mid,String resu){
    	//������֤��Ϣ
        chatUserBiz.doCheckMessage(mid, resu);
    	//��ӳɹ��󣬷�����ҳ
    	return "redirect:/chatUser/main.do";
    }
    
    @RequestMapping(value="addfriend")
    public String selectnotfriends(HttpSession session,String friendid){
    	//��ȡ��¼�û�����Ϣ
        Chatuser loginUser = (Chatuser)session.getAttribute("loginUser");
    	if(loginUser ==null){
    		return "login"; //û�е�¼��ת����¼ҳ��
    	}
    	//ֱ����Ӻ���
    	chatUserBiz.insertUserFriend(loginUser,friendid);
    	//��ӳɹ��󣬷�����ҳ��
    	return "redirect:/chatUser/main.do";
     }
    
    @RequestMapping(value="selectnotfriends")
    public String selectnotfriends(HttpSession session,Map map){
    	//��ȡ��¼�û�����Ϣ
        Chatuser loginUser = (Chatuser)session.getAttribute("loginUser");
    	if(loginUser ==null){
    		return "login"; //û�е�¼��ת����¼ҳ��
    	}
    	//��ȡ���ǵ�ǰ�û��ĺ����б�
    	List<Chatuser> notfriendList=chatUserBiz.selectNotFriends(loginUser.getId());
    	map.put("notfriendList", notfriendList);
    	//��ӳɹ��󣬷��غ��Ѷ�̬ҳ��
    	return "friend";
     }
    	
    
     @RequestMapping(value="addmessage")
    public String main(HttpSession session,Message message){
    	//��ȡ��¼�û�����Ϣ
        Chatuser loginUser = (Chatuser)session.getAttribute("loginUser");
    	if(loginUser ==null){
    		return "login"; //û�е�¼��ת����¼ҳ��
    	}
    	message.setFromid(loginUser.getId()); //��¼�û�������Ϣ�ķ�����
    	message.setId(DateUtil.getId()); //���ù���������ID
    	message.setStatus(0); //δ����Ϣ
    	message.setTime(new Date());
    	message.setType(1);  //������Ϣ
    	messageBiz.insertMessage(message);
    	//��ӳɹ��󣬷�����ҳ
    	return "redirect:/chatUser/main.do?friendid="+message.getToid();
    }
    	
    	
    	
    //����·�� 
    @RequestMapping(value="main")
    public String main(HttpSession session,Map map,String friendid){
    	//��ȡ��¼�û�����Ϣ
        Chatuser loginUser = (Chatuser)session.getAttribute("loginUser");
    	if(loginUser ==null){
    		return "login"; //û�е�¼��ת����¼ҳ��
    	}
    	//��ȡ�õ�¼�û�����֤��Ϣ�б����ݵ�½��ID��
    	List<Message> checkList=messageBiz.selectCheckMessages(loginUser.getId());
    	map.put("checkList", checkList);
    	//ʵ�ֶ�ȡ����Ĺ���
    	if(friendid!=null && !"".equals(friendid)){
    		//���Һ��Ѹ��ҷ���������Ϣ�б�
    		List<Message> messageList= messageBiz.selectMessages(friendid, loginUser.getId());
    	    map.put("messageList", messageList);
    	}
    	
    	//���Һ�����Ϣ
    	List<Chatuser> friendList = chatUserBiz.selectUserFriends(loginUser.getId());
    	//��ȡÿһ�����ѵ�δ��ȡ��Ϣ�ĸ���
    	for(int i = 0;i<friendList.size();i++){
    		Chatuser friend = friendList.get(i);
    		Map<String,String>pmap = new HashMap<String,String>();
    		pmap.put("fromid", friend.getId());//�����Ƿ�����
    		pmap.put("toid", loginUser.getId());//��¼�û��ǽ�����
    		int count = messageBiz.selectNoReadMessageCount(pmap);
    		//��ʱʹ��checktype����δ��ȡ��Ϣ����
    		friend.setChecktype(count);
    	}
    	map.put("friendList", friendList);
    	return "index";	
    	}
    
    @RequestMapping(value="add")
    public String add(Chatuser user,Map map){
    	user.setId(DateUtil.getId());
    	int num = chatUserBiz.insertUser(user);
    	//�жϽ��
    	if(num ==-1){
    		map.put("msg","�û����������Ѵ���");
    		return "register";
    	}
    	//��½�ɹ�
    	return "login";	
    	}
    
   
        @RequestMapping(value="login")
        public String login(Chatuser user,HttpSession session){
        	Map<String,String> paramMap = new HashMap<String,String>();
        	paramMap.put("userid", user.getUserid());
        	paramMap.put("password", user.getPassword());
        	Chatuser loginUser = chatUserBiz.selectLoginUser(paramMap);
        	//�жϽ��
        	if(loginUser ==null){
        		return "login";
        	}
        	//��½�ɹ�
        	session.setAttribute("loginUser",loginUser);
        	return "redirect:/chatUser/main.do";	
        	}
    }
