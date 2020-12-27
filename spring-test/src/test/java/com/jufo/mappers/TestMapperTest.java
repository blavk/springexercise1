package com.jufo.mappers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jufo.app.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Application.class})
public class TestMapperTest {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	TestMapper testMapper;

	@Test
	public void testDataSource() {
		System.out.println(dataSource);
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select count(*) bookcnt from books");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("bookcnt"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMapperTest() {
		int bookCnt = testMapper.selectBookCnt();
		System.out.println(bookCnt);
	}
}
