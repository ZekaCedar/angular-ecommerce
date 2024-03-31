package com.siti.ecommerce.service;

import com.siti.ecommerce.dao.CustomerRepository;
import com.siti.ecommerce.dto.Purchase;
import com.siti.ecommerce.dto.PurchaseResponse;
import com.siti.ecommerce.entity.Customer;
import com.siti.ecommerce.entity.Order;
import com.siti.ecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public PurchaseResponse placeOrder(Purchase purchase) {
		// TODO: retrieve the order info from dto
		Order order = purchase.getOrder();
		
		// TODO: generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		// TODO: populate order with orderItems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		// TODO: populate order with billingAddress and shippingAddress
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		// TODO: populate customer with order
		Customer customer = purchase.getCustomer();

		// check if this is an existing customer
		String theEmail = customer.getEmail();

		Customer customerFromDB = customerRepository.findByEmail(theEmail);

		if (customerFromDB != null){
			// we found them ... let's assign them accordingly
			customer = customerFromDB;
		}

		customer.add(order);
		
		// TODO: save to the database
		customerRepository.save(customer);
		
		// TODO: return a response
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		// TODO: generate a random UUID (UUID version-4)
		// TODO: For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
		// INFO: Probability of duplicate is 103 trillion version is one in a billion
		// absolute 0% of collision?
		return UUID.randomUUID().toString();
	}

}
