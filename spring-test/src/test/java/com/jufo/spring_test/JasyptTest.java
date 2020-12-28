package com.jufo.spring_test;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jufo.app.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Application.class})
public class JasyptTest {
	
	@Autowired
	StringEncryptor encryptor;
	
	@Test
	public void testEncrypt() {
		String encrypt = encryptor.encrypt("123456");
		System.out.println(encrypt);
	}

}
