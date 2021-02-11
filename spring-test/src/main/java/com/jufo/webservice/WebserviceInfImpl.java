package com.jufo.webservice;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

@WebService(serviceName="WebService",//对外发布的服务名
targetNamespace="http://service.webservice.kexin.com",//指定你想要的名称空间，通常使用使用包名反转
endpointInterface="com.jufo.webservice.WebserviceInf")
@Component
public class WebserviceInfImpl implements WebserviceInf{
	@Override
	public String ping() {
		return "pong";
	}
}
