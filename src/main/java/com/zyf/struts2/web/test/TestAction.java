package com.zyf.struts2.web.test;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.zyf.struts2.dao.TestSpringJdbcDao;
import com.zyf.struts2.po.TestPO;

/**
 * Struts2基于注解的Action配置
 * 
 * 
 */
@ParentPackage(value = "struts-default")
@Namespace("/test")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.Exception", result = "error") })
@InterceptorRefs({ @InterceptorRef("defaultStack") })
@Results({ @Result(name = "success", location = "/index.jsp"),
		@Result(name = "error", location = "/index.jsp") })
public class TestAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Autowired
	private TestSpringJdbcDao testSpringJdbcDao;
	private String username;
	private String password;
	private String id;

	@Action("login")
	public String login() throws Exception {
		System.out.println("===========" + username);
		return SUCCESS;
	}

	@Action("add")
	public String add() throws Exception {
		TestPO testPO = new TestPO();
		testPO.setRemark(username);
		testSpringJdbcDao.insert(testPO);
		id = String.valueOf(testPO.getId());
		return "/index.jsp";
	}

	@Action("query")
	public String query() throws Exception {
		TestPO testPO = new TestPO();
		return "/index.jsp";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}