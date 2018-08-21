package com.zhiyou100.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

public class jdbcRealmTest {
	DruidDataSource dataSource=new DruidDataSource();
	{
		dataSource.setUrl("jdbc:mysql://localhost:3306/world");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
	}
	@Test
	public void testAuthentication() {
		JdbcRealm jdbcRealm=new JdbcRealm();		
		jdbcRealm.setDataSource(dataSource);
		// 构建SecurityManager环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(jdbcRealm);
		
		// 主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
		subject.login(token);

		System.out.println("isAuthenticated:" + subject.isAuthenticated());
		
	}
}
