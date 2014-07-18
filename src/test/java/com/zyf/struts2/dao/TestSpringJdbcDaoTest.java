package com.zyf.struts2.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zyf.struts2.TestBase;
import com.zyf.struts2.po.TestPO;

public class TestSpringJdbcDaoTest extends TestBase {
	@Autowired
	private TestSpringJdbcDao testSpringJdbcDao;

	@Test
	public void testInsert() {
		TestPO test = new TestPO();
		test.setRemark("123");
		testSpringJdbcDao.insert(test);
		Assert.assertTrue(test.getId() > 0);
	}

	@Test
	public void testQuery() {
		int id = 1;
		TestPO test = testSpringJdbcDao.queryById(id);
		Assert.assertTrue(test != null);
	}
}
