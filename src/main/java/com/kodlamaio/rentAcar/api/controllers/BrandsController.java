package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.business.abstracts.BrandService;
import com.kodlamaio.rentAcar.business.request.brands.CreateBrandRequest;
import com.kodlamaio.rentAcar.business.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentAcar.business.response.brand.GetAllBrandsResponse;
import com.kodlamaio.rentAcar.business.response.brand.GetBrandResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

@RestController
@RequestMapping("/api/brands")

public class BrandsController {
	@Autowired
	private BrandService brandServices;

	@GetMapping("/sayHello") // endpoint
	public String sayHello() {
		return "hello Spring";
	}

	@PostMapping("/add") // veri göndermek için
	public Result add(@RequestBody CreateBrandRequest createBrandRequest) {
	   return this.brandServices.add(createBrandRequest);
	}
	@PostMapping("/update")
	public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) {
		return this.brandServices.update(updateBrandRequest);
	}

	@DeleteMapping("/{id}")
	public Result delete(@RequestBody int id) {
		return this.brandServices.deleteById(id);
	}
	
	@GetMapping("/getById")
	public DataResult<List<GetBrandResponse>> getById(@RequestParam int id) {
		return brandServices.getById(id);
	}
	@GetMapping("/getAll")
	public  DataResult <List<GetAllBrandsResponse>>  GetAll() {
		
		return brandServices.getall();
	}
}
