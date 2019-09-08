package com.elephant.contoller.customer1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elephant.constant.*;
import com.elephant.dao.CustomerRepository;
import com.elephant.model.PaymentModel;
import com.elephant.model.Product;
import com.elephant.service.OrderService;
import com.elephant.service.PayPalService;
import com.elephant.config.PayPalResult;
import com.elephant.config.PayPalSucess;



@Controller
@RequestMapping("/cart")

public class PaymentController {
	
	/*static String email="null";
	static long addressid=0;*/
	
	@Autowired
	private PayPalService payPalService; 
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	OrderService orderService;
	
	
	 PaymentModel paymentModel=new PaymentModel();

	@RequestMapping(value="/paypal",method=RequestMethod.GET, produces="application/json")
	public String cartpage(ModelMap modelMap) {
		
	   
	    //paymentModel.setEmail(Email);
		//paymentModel.setAddressId(addressId);
		List<Product> products=new ArrayList<Product>();
		/*------------------Get Total Price from Cart-------------------------------*/
		
		//CustomerDomain customerDomain=customerRepository.findByEmail(Email);
		//List<CartItemDomain> listCartItemDomain=customerDomain.getCartItemDomain();
		double grandtotal=0;
		//for(CartItemDomain cartItemDomain:listCartItemDomain) {
			//ProductDomain productDomain=cartItemDomain.getProduct();
			//grandtotal+= (productDomain.getPrice()-((productDomain.getPrice()*productDomain.getDiscount()/100)))*cartItemDomain.getQuantity();
		//}
		
		products.add(new Product("Total products", 20));
		/*--------------------------------------------------------------------------*/
		
		modelMap.put("products", products);
		modelMap.put("payPalConfig",payPalService.getPayPalConfig() );
		
		return "ResetPassword2";
	}
	
	
	@RequestMapping(value = "success",  method=RequestMethod.GET)
	public String success(HttpServletRequest request) {
		
		try {
			
		PayPalSucess payPalSucess = new PayPalSucess();
		PayPalResult payPalResult = payPalSucess.getPayPal(request, payPalService.getPayPalConfig());
		/*---------------------Set Payment to PayPal Mode and Get transactionId through PayPalResult Class--------*/
		paymentModel.setPaymentMode(Constants.PAYPAL);
		paymentModel.setTransactionId(payPalResult.getTxn_id());
		/*---------------------------------------------------------------------------------------------------------*/
		orderService.createOrder(paymentModel);

		System.out.println("Order Info");
		System.out.println("First Name: " + payPalResult.getFirst_name());
		System.out.println("Last Name" + payPalResult.getLast_name() );
		System.out.println("Country" + payPalResult.getAddress_country());
		System.out.println("Email" + payPalResult.getPayer_email());
		System.out.println("Country" + payPalResult.getMc_gross());
		}catch(Exception ex) {
			System.out.println("Exception in success"+ex);
		}
		
		return "cart/success";
		}

}
