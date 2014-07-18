package com.zyf.struts2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zyf.struts2.po.TestPO;

@Repository
public class TestSpringJdbcDao extends SpringJdbcBase {

	public void insert(TestPO testPO) {
		String remark = testPO.getRemark();
		String sql = "INSERT INTO test(remark) VALUES(?)";
		String key = insert(sql, new Object[] { remark });
		testPO.setId(Integer.parseInt(key));
	}

	public TestPO queryById(int id) {
		return jdbcTemplate.queryForObject("select * from test where id = ?",
				new Object[] { id }, new RowMapper<TestPO>() {

					@Override
					public TestPO mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						TestPO test = new TestPO();
						test.setId(rs.getInt("id"));
						test.setUsername(rs.getString("username"));
						test.setPassword(rs.getString("password"));
						return test;
					}

				});
	}
}
