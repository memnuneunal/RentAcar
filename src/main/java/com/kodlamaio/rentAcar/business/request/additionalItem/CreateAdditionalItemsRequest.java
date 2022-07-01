package com.kodlamaio.rentAcar.business.request.additionalItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalItemsRequest {
	private String name;
	private double price;
}