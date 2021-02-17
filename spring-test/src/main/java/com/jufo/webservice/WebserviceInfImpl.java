package com.jufo.webservice;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
//@WebService(serviceName="WebService",//对外发布的服务名
//targetNamespace="http://service.jufo.com",//指定你想要的名称空间，通常使用使用包名反转
//endpointInterface="com.jufo.webservice.WebserviceInf")
@WebService(targetNamespace="http://service.jufo.com")
public class WebserviceInfImpl implements WebserviceInf{
	@Override
	public String ping() {
		System.out.println("ping invoke");
		return "pong";
	}
}
