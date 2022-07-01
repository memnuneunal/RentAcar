package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.Entities.concretes.AdditionalItems;
import com.kodlamaio.rentAcar.business.abstracts.InvoiceService;
import com.kodlamaio.rentAcar.business.request.invoice.CreateInvoicesRequest;
import com.kodlamaio.rentAcar.business.request.invoice.UpdateInvoicesRequest;
import com.kodlamaio.rentAcar.business.response.invoice.GetAllInvoiceResponse;
import com.kodlamaio.rentAcar.business.response.invoice.ReadInvoiceResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

private InvoiceService invoiceService;
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateInvoicesRequest createInvoiceRequest) {
		return this.invoiceService.add(createInvoiceRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@RequestBody int id) {
		return this.invoiceService.delete(id);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateInvoicesRequest updateInvoiceRequest) {
		return this.invoiceService.update(updateInvoiceRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<ReadInvoiceResponse> getById(int id){
		return this.invoiceService.getById(id);
	}
	
	@GetMapping("getAll")
	public DataResult<List<GetAllInvoiceResponse>> getAll(){
		return this.invoiceService.getAll();
	}
	
	@GetMapping("/getAllAdditionalItems")
	public DataResult<List<AdditionalItems>> getAllAdditionalItems(@RequestParam int rentalId) {
		return this.invoiceService.getAllAdditionalItems(rentalId);
	}

}
