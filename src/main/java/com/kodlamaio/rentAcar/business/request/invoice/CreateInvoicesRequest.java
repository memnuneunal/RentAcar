package com.kodlamaio.rentAcar.business.request.invoice;

	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class CreateInvoicesRequest {
		private int invoiceNumber;
		private int rentalId;

	}