package com.ryxy.webchat.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * MyBatisSessionFactory宸ュ巶锟�
 * @author 闂
 *
 */
public class MyBatisSessionFactory {
	
	public static SqlSession getSession(){
		
			SqlSessionFactory sqlSessionFactory = null;
			Reader reader = null;
			try {
				reader = Resources.getResourceAsReader("mybatis.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "dataSource");
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return sqlSessionFactory.openSession();
	}
	
}
