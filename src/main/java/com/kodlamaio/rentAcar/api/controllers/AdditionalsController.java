package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.business.abstracts.AdditionalService;
import com.kodlamaio.rentAcar.business.request.additionalService.CreateAdditionalsRequest;
import com.kodlamaio.rentAcar.business.request.additionalService.UpdateAdditionalsRequest;
import com.kodlamaio.rentAcar.business.response.additionalitems.GetAllAdditionalResponse;
import com.kodlamaio.rentAcar.business.response.additionalitems.ReadAdditionalResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

@RestController
@RequestMapping("/api/additionals")
public class AdditionalsController {
	private AdditionalService additionalService;

	public AdditionalsController(AdditionalService additionalService) {
		super();
		this.additionalService= additionalService;
	}
	
	@PostMapping("/add")
	public Result add(CreateAdditionalsRequest createAdditionalRequest) {
		return this.additionalService.add(createAdditionalRequest);
	}
	
	@PostMapping("/update")
	public Result update(UpdateAdditionalsRequest updateAdditionalRequest) {
		return this.additionalService.update(updateAdditionalRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@RequestBody int id) {
		return this.additionalService.delete(id);
	}
	
	@GetMapping("/getById")
	public DataResult<ReadAdditionalResponse> getById(@RequestParam int id) {
		return additionalService.findById(id);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllAdditionalResponse>> getAll() {
		return this.additionalService.getAll();
	}

}