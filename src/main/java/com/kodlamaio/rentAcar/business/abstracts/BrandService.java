package com.kodlamaio.rentAcar.business.abstracts;


import java.util.List;

import com.kodlamaio.rentAcar.business.request.brands.CreateBrandRequest;
import com.kodlamaio.rentAcar.business.request.brands.UpdateBrandRequest;
import com.kodlamaio.rentAcar.business.response.brand.GetAllBrandsResponse;
import com.kodlamaio.rentAcar.business.response.brand.GetBrandResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
public interface BrandService {
	
	Result add(CreateBrandRequest createBrandRequest);
	Result update(UpdateBrandRequest updateBrandRequest);
	

	//DataResult< List <Brand>> getall();
	DataResult <List<GetAllBrandsResponse>> getall();
	
  DataResult<List<GetBrandResponse>> getById(int id);
   Result deleteById(int id);


	


}
