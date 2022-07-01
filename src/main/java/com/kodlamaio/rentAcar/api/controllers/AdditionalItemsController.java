package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.business.abstracts.OrderedAdditionalItemService;
import com.kodlamaio.rentAcar.business.request.additionalItem.CreateAdditionalItemsRequest;
import com.kodlamaio.rentAcar.business.request.additionalItem.UpdateAdditionalItemsRequest;
import com.kodlamaio.rentAcar.business.response.additionalitems.GetAllAdditionalResponse;
import com.kodlamaio.rentAcar.business.response.additionalitems.ReadAdditionalResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
@RestController
@RequestMapping("api/additionalItem")
public class AdditionalItemsController {
	private OrderedAdditionalItemService additionalItemService;

	public AdditionalItemsController(OrderedAdditionalItemService additionalItemService) {
		this.additionalItemService = additionalItemService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateAdditionalItemsRequest creAdditionalItemRequest) {
		return this.additionalItemService.add(creAdditionalItemRequest);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@RequestBody int id) {
		return this.additionalItemService.delete(id);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody UpdateAdditionalItemsRequest updateAdditionalItemRequest) {
		return this.additionalItemService.update(updateAdditionalItemRequest);
	}
	
	@GetMapping("/getById")
	public DataResult<ReadAdditionalResponse> getById(@RequestParam int id) {
		return this.additionalItemService.getById(id);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllAdditionalResponse>> getAll(){
		return this.additionalItemService.getAll();
	}
}
