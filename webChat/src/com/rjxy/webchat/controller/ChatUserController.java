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
//一级路径
@RequestMapping(value="chatUser")
public class ChatUserController {
	@Autowired
    private MessageBiz messageBiz;
    @Autowired
    private ChatUserBiz chatUserBiz;
    
    @RequestMapping(value="updateuser")
    public String updateuser(Chatuser user){
    	//处理用户更新
        chatUserBiz.updateChatUser(user);
    	//更新完后，重新登录
    	return "redirect:/login.jsp";
    }
    @RequestMapping(value="doCheckMessage")
    public String doCheckMessage(String mid,String resu){
    	//处理验证消息
        chatUserBiz.doCheckMessage(mid, resu);
    	//添加成功后，返回主页
    	return "redirect:/chatUser/main.do";
    }
    
    @RequestMapping(value="addfriend")
    public String selectnotfriends(HttpSession session,String friendid){
    	//获取登录用户的信息
        Chatuser loginUser = (Chatuser)session.getAttribute("loginUser");
    	if(loginUser ==null){
    		return "login"; //没有登录跳转到登录页面
    	}
    	//直接添加好友
    	chatUserBiz.insertUserFriend(loginUser,friendid);
    	//添加成功后，返回主页面
    	return "redirect:/chatUser/main.do";
     }
    
    @RequestMapping(value="selectnotfriends")
    public String selectnotfriends(HttpSession session,Map map){
    	//获取登录用户的信息
        Chatuser loginUser = (Chatuser)session.getAttribute("loginUser");
    	if(loginUser ==null){
    		return "login"; //没有登录跳转到登录页面
    	}
    	//获取不是当前用户的好友列表
    	List<Chatuser> notfriendList=chatUserBiz.selectNotFriends(loginUser.getId());
    	map.put("notfriendList", notfriendList);
    	//添加成功后，返回好友动态页面
    	return "friend";
     }
    	
    
     @RequestMapping(value="addmessage")
    public String main(HttpSession session,Message message){
    	//获取登录用户的信息
        Chatuser loginUser = (Chatuser)session.getAttribute("loginUser");
    	if(loginUser ==null){
    		return "login"; //没有登录跳转到登录页面
    	}
    	message.setFromid(loginUser.getId()); //登录用户就是消息的发送者
    	message.setId(DateUtil.getId()); //利用工具类生产ID
    	message.setStatus(0); //未读消息
    	message.setTime(new Date());
    	message.setType(1);  //聊天消息
    	messageBiz.insertMessage(message);
    	//添加成功后，返回主页
    	return "redirect:/chatUser/main.do?friendid="+message.getToid();
    }
    	
    	
    	
    //二级路径 
    @RequestMapping(value="main")
    public String main(HttpSession session,Map map,String friendid){
    	//获取登录用户的信息
        Chatuser loginUser = (Chatuser)session.getAttribute("loginUser");
    	if(loginUser ==null){
    		return "login"; //没有登录跳转到登录页面
    	}
    	//读取该登录用户的验证消息列表（根据登陆者ID）
    	List<Message> checkList=messageBiz.selectCheckMessages(loginUser.getId());
    	map.put("checkList", checkList);
    	//实现读取聊天的功能
    	if(friendid!=null && !"".equals(friendid)){
    		//查找好友给我发送来的消息列表
    		List<Message> messageList= messageBiz.selectMessages(friendid, loginUser.getId());
    	    map.put("messageList", messageList);
    	}
    	
    	//查找好友信息
    	List<Chatuser> friendList = chatUserBiz.selectUserFriends(loginUser.getId());
    	//读取每一个好友的未读取消息的个数
    	for(int i = 0;i<friendList.size();i++){
    		Chatuser friend = friendList.get(i);
    		Map<String,String>pmap = new HashMap<String,String>();
    		pmap.put("fromid", friend.getId());//好友是发送者
    		pmap.put("toid", loginUser.getId());//登录用户是接收着
    		int count = messageBiz.selectNoReadMessageCount(pmap);
    		//临时使用checktype保存未读取消息个数
    		friend.setChecktype(count);
    	}
    	map.put("friendList", friendList);
    	return "index";	
    	}
    
    @RequestMapping(value="add")
    public String add(Chatuser user,Map map){
    	user.setId(DateUtil.getId());
    	int num = chatUserBiz.insertUser(user);
    	//判断结果
    	if(num ==-1){
    		map.put("msg","用户名或密码已存在");
    		return "register";
    	}
    	//登陆成功
    	return "login";	
    	}
    
   
        @RequestMapping(value="login")
        public String login(Chatuser user,HttpSession session){
        	Map<String,String> paramMap = new HashMap<String,String>();
        	paramMap.put("userid", user.getUserid());
        	paramMap.put("password", user.getPassword());
        	Chatuser loginUser = chatUserBiz.selectLoginUser(paramMap);
        	//判断结果
        	if(loginUser ==null){
        		return "login";
        	}
        	//登陆成功
        	session.setAttribute("loginUser",loginUser);
        	return "redirect:/chatUser/main.do";	
        	}
    }
