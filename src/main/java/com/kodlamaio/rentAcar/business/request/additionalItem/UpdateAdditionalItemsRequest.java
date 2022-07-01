package com.kodlamaio.rentAcar.business.request.additionalItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalItemsRequest {
	private String name;
	private double price;

}