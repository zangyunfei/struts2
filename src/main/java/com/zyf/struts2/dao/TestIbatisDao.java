package com.zyf.struts2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.zyf.struts2.po.TestPO;

public interface TestIbatisDao {

	@Insert("INSERT INTO TEST (remark) VALUES (#{remark}) ")
	public void insert(TestPO testPO);

	@Select("SELECT  * FROM  test ")
	public List<TestPO> queryAll();

}
