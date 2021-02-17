package com.jufo.webservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class ExampleServiceInterceptor implements CallbackHandler {

	private Map<String, String> passwords = new HashMap<String, String>();

	public ExampleServiceInterceptor() {
		passwords.put("admin", "password");// 此处的对应的是验证信息-用户名+密码，必须与客户端一致才可验证通过
	}

	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        System.out.println("getIdentifier: " + pc.getIdentifier());
	    //通过数据库得到该用户名的密码，这里略去该过程
	    pc.setPassword("123");//直接设置从数据库得到的密码，WSS4J自动匹配该值与客户端传入的值，不需要调用pc.getPassword();因为它总是返回null
	        
	}

}
