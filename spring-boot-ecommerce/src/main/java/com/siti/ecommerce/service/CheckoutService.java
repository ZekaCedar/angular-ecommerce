package com.siti.ecommerce.service;

import com.siti.ecommerce.dto.Purchase;
import com.siti.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);

}
