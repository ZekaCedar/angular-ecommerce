package com.siti.ecommerce.dto;

import com.siti.ecommerce.entity.Address;
import com.siti.ecommerce.entity.Customer;
import com.siti.ecommerce.entity.Order;
import com.siti.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private Order order;
	// a collection of orderItems to take use of the JSON Array
	private Set<OrderItem> orderItems;
}
