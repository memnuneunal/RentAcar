package com.kodlamaio.rentAcar.business.response.invoice;


import java.time.LocalDate;
import java.util.List;

import com.kodlamaio.rentAcar.Entities.concretes.AdditionalItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllInvoiceResponse {
	private int id;
	private int rentalId;
	private int invoiceNumber;
	private String carNumberPlate;
	private String carDescription;
	private int additionalServiceId;
	private String userFirstNmae;
	private LocalDate currentlyDate;
	private double totalPrice;
	private List<AdditionalItems> additionalItems;


}
