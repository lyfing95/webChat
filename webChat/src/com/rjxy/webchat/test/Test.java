package com.rjxy.webchat.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rjxy.webchat.entity.Chatuser;
import com.rjxy.webchat.mapper.ChatuserMapper;

public abstract class Test {
     public static void main(String[] args) {
		//��ȡspring������Ϣ
    	 ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml","spring_mvc.xml");
    	//��ȡspring�����е�mapper����
    	//spring����mapper����Ĺ�����������ĸСд������mapper�����ID
    	 ChatuserMapper userMapper=(ChatuserMapper)ac.getBean("chatuserMapper");
    	 //Chatuser user =userMapper.selectByPrimaryKey("201111120112");
    	 Map<String,String> paramMap = new HashMap<String,String>();
    	 paramMap.put("userid","admin");
    	 paramMap.put("password","admin");
    	 Chatuser user = userMapper.selectLoginUser(paramMap);
    	 if(user == null)
    		  System.out.println("�û���¼ʧ��");
    	 else
    		 System.out.println("��ӭ��"+user.getNickname());
    	 //������ݵĽ��
    	 //System.out.println(user.getNickname());
	}
}
