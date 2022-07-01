package com.kodlamaio.rentAcar.business.response.additionalitems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAdditionalResponse {
	private int totalDays;
	private double totalPrice;
	private int additionalItemId;
	private int rentalId;

}
