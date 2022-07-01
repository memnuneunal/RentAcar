package com.kodlamaio.rentAcar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentAcar.business.abstracts.ColorServices;
import com.kodlamaio.rentAcar.business.request.colors.CreateColorRequest;
import com.kodlamaio.rentAcar.business.request.colors.UpdateColorRequest;
import com.kodlamaio.rentAcar.business.response.color.GetAllColorResponse;
import com.kodlamaio.rentAcar.business.response.color.ReadColorResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
@RequestMapping("/api/colors")//yolunu tanımlıyorum
@RestController//istekler için
public class ColorsController {
	@Autowired
	private ColorServices colorServices;
	
	@GetMapping("/getColor")
	public String getColor() {
		return "Color";
	}

	@PostMapping("/add")
	public void add(@RequestBody CreateColorRequest createColorRequest) {
		this.colorServices.add(createColorRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateColorRequest updateColorRequest) {
		return this.colorServices.update(updateColorRequest);
	}
	
	@PostMapping("/{id}")
	public Result delete(@RequestBody int id) {
		return this.colorServices.delete(id);
	}
	
	@GetMapping("/getById")
	public DataResult<ReadColorResponse> findById(@RequestParam int id){
		return this.colorServices.findById(id);
		
	}
	@GetMapping("/getAll")
	public DataResult<List<GetAllColorResponse>> getAll(){
		return this.colorServices.getAll();
	
	}

}
