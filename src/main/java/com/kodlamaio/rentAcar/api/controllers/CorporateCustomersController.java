package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentAcar.business.request.corporateCustomer.CreateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.business.request.corporateCustomer.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentAcar.business.request.corporateCustomer.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentAcar.business.response.corporateCustomers.GetAllCorporateCustomerResponse;
import com.kodlamaio.rentAcar.business.response.corporateCustomers.ReadCorporateCustomerResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

@RestController
@RequestMapping("/api/corporatecustomers")
public class CorporateCustomersController {

	@Autowired
	private CorporateCustomerService corporateCustomerService;

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		return this.corporateCustomerService.add(createCorporateCustomerRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
	}

	@PostMapping("/getbyid")
	public DataResult<ReadCorporateCustomerResponse> getbyid(@RequestParam int id) {
		return this.corporateCustomerService.getById(id);
	}

	@PostMapping("/getall")
	public DataResult<List<GetAllCorporateCustomerResponse>> getAll() {
		return this.corporateCustomerService.getAll();
	}

}
