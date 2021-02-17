package com.jufo.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jufo.webservice.ExampleServiceInterceptor;
import com.jufo.webservice.WebserviceInfImpl;

@Configuration
public class WebServiceConfig {
	
//	@Bean
//	public ServletRegistrationBean<MessageDispatcherServlet> name(ApplicationContext applicationContext) {
//		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//		servlet.setApplicationContext(applicationContext);
//		servlet.setTransformWsdlLocations(true);
//		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
//	}
//	@Bean(name = "countries")//http://localhost:8080/countries.wsdl
//    public DefaultWsdl11Definition pdaWsdl11Definition(XsdSchema pda2wmsSchema) {
//        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
//        wsdl11Definition.setPortTypeName("CountriesPort");
//        wsdl11Definition.setLocationUri("/ws");
//        wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
//        wsdl11Definition.setSchema(pda2wmsSchema);
//        return wsdl11Definition;
//    }
//    @Bean
//    public XsdSchema pda2wmsSchema() {
//        return new SimpleXsdSchema(new ClassPathResource("/xsd/countries.xsd"));
//    }
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
	public ExampleServiceInterceptor exampleServiceInterceptor() {
		return new ExampleServiceInterceptor();
	}
	
	@Bean
	public WebserviceInfImpl webserviceInfImpl() {
		return new WebserviceInfImpl();
		
	}
	
	@Bean
    public Endpoint endpoint(ExampleServiceInterceptor exampleServiceInterceptor, WebserviceInfImpl webserviceInfImpl) {
        EndpointImpl endpoint=new EndpointImpl(cxf(), webserviceInfImpl);//绑定要发布的服务
        Map<String, Object> inProps = new HashMap<String, Object>();
//        inProps.put(WSHandlerConstants.USER, "admin");
        inProps.put(WSHandlerConstants.ACTION,WSHandlerConstants.USERNAME_TOKEN);//设置加密类型
        inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT); //设置密码类型为加密 
        inProps.put(WSHandlerConstants.PW_CALLBACK_REF,  exampleServiceInterceptor);  //密码回调函数
//        endpoint.getInInterceptors().add( new LoggingInInterceptor());
//        endpoint.getInInterceptors().add( new SAAJInInterceptor());
        endpoint.getInInterceptors().add(new WSS4JInInterceptor(inProps));
        endpoint.publish("/user"); //显示要发布的名称
        return endpoint;
    }
	
//	@Bean("serviceUseC")
//	public CryptoFactoryBean cryptoFactoryBean() throws IOException {
//		CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
//		cryptoFactoryBean.setKeyStoreLocation(new ClassPathResource("trust_store.jks"));
//		cryptoFactoryBean.setKeyStorePassword("storepass");
//		return cryptoFactoryBean;
//		
//	}
//	
//	
//	@Bean("serviceUseI")
//	public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
//		Wss4jSecurityInterceptor wss4jSecurityInterceptor = new Wss4jSecurityInterceptor();
//		wss4jSecurityInterceptor.setValidationActions("Signature");
//		wss4jSecurityInterceptor.setValidationSignatureCrypto(cryptoFactoryBean().getObject());
//		return wss4jSecurityInterceptor;
//		
//	}
//	
//	@Override
//    public void addInterceptors(List<EndpointInterceptor> interceptors) {
//        try {
//            super.addInterceptors(interceptors);
//            interceptors.add(securityInterceptor());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
	
}
