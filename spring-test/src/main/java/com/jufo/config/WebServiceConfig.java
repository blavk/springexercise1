package com.jufo.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;

import com.jufo.webservice.WebserviceInfImpl;

@EnableWs
@Configuration
public class WebServiceConfig {
	
//	@Bean
//	public ServletRegistrationBean<MessageDispatcherServlet> name(ApplicationContext applicationContext) {
//		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//		servlet.setApplicationContext(applicationContext);
//		servlet.setTransformWsdlLocations(true);
//		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/LWS/*");
//	}
//	
//	@Bean(name="PBLocateService")
//	public SimpleWsdl11Definition simpleWsdl11Definition() {
//		SimpleWsdl11Definition simpleWsdl11Definition = new SimpleWsdl11Definition();
//		simpleWsdl11Definition.setWsdl(new ClassPathResource("wsdl/ws.wsdl"));
//		return simpleWsdl11Definition;
//	}
	
	@Bean
	public ServletRegistrationBean<CXFServlet> name() {
		CXFServlet cxfServlet = new CXFServlet();
		return new ServletRegistrationBean<CXFServlet>(cxfServlet, "/*");
	}
	
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus cxf() {
		return new SpringBus();
	}
	
	@Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint=new EndpointImpl(cxf(), new WebserviceInfImpl());//绑定要发布的服务
        endpoint.publish("/user"); //显示要发布的名称
        return endpoint;
    }
	
	
}
