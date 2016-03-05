package com.pmikee.kir;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.pmikee.kir.wsdl.GetOrderRequest;
import com.pmikee.kir.wsdl.GetOrderResponse;
import com.pmikee.kir.wsdl.Order;

public class OrderClient extends WebServiceGatewaySupport{

	public GetOrderResponse postOrder(Order order) {
		GetOrderRequest request = new GetOrderRequest();
		request.setOrder(order);
		GetOrderResponse response = (GetOrderResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8080/ws/getStudentResponse"));
		return response;
	}
	
}
