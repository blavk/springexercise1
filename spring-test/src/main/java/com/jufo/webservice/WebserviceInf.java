package com.jufo.webservice;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace="http://service.webservice.kexin.com")
public interface WebserviceInf {
	@WebMethod
    @WebResult(name="String",targetNamespace="")
	public String ping();
}
