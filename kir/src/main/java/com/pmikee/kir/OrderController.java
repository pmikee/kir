package com.pmikee.kir;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.pmikee.kir.wsdl.GetOrderRequest;
import com.pmikee.kir.wsdl.GetOrderResponse;
import com.pmikee.kir.wsdl.Order;
import com.pmikee.kir.wsdl.Positions;

@Controller
public class OrderController extends WebServiceGatewaySupport {

	@Autowired
	OrderClient orderClient;
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String getOrder(Model model) {
		model.addAttribute("order", new Order());
		return "order";
	}

	@RequestMapping(value = "/order", params = { "addRow" })
	public String addRow(final Order order, final BindingResult bindingResult, Model model) {
		order.getPosition().add(new Positions());
		model.addAttribute("product", getProductList());
		return "order";
	}
	
	@RequestMapping(value="/order", params={"removeRow"})
	public String removeRow(
	        final Order order, final BindingResult bindingResult, 
	        final HttpServletRequest req) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	    order.getPosition().remove(rowId.intValue());
	    return "order";
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String orderSubmit(@ModelAttribute Order order, @ModelAttribute Positions position, Model model) {
		model.addAttribute("order", order);
		GetOrderResponse response = orderClient.postOrder(order);
		model.addAttribute("response", response);
		return "result";
	}
	
	public List<Product> getProductList(){
        RestTemplate restTemplate = new RestTemplate();
        Product[] products = restTemplate.getForObject("http://localhost:8080/products/", Product[].class);
        return Arrays.asList(products);

	}

}
