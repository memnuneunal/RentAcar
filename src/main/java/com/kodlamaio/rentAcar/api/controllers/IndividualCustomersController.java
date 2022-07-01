package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentAcar.business.request.individualCustomer.CreateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.business.request.individualCustomer.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentAcar.business.request.individualCustomer.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentAcar.business.response.indivualCustomer.GetAllIndividualCustomersResponse;
import com.kodlamaio.rentAcar.business.response.indivualCustomer.ReadIndividualCustomerResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomersController {

	private IndividualCustomerService individualCustomerService;

	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		this.individualCustomerService = individualCustomerService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}

	@GetMapping("/getbyid")
	public DataResult<ReadIndividualCustomerResponse> getById(@RequestParam int id) {
		return this.individualCustomerService.getById(id);
	}

	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<GetAllIndividualCustomersResponse>> getAll() {
		return this.individualCustomerService.getAll();
	}

}
