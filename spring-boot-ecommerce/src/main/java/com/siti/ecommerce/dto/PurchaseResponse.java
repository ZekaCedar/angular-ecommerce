package com.siti.ecommerce.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PurchaseResponse {
	
//	private final String orderTrackingNumber;
	
	@NonNull
	private String orderTrackingNumber;
}
