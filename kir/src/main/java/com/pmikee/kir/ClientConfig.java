package com.pmikee.kir;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientConfig {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.pmikee.kir.wsdl");
		return marshaller;
	}
	@Bean
	public OrderClient orderClient(Jaxb2Marshaller marshaller) {
		OrderClient client = new OrderClient();
		client.setDefaultUri("http://localhost:8080/ws/order.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

	
	
}
