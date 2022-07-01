package com.kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.business.request.colors.CreateColorRequest;
import com.kodlamaio.rentAcar.business.request.colors.UpdateColorRequest;
import com.kodlamaio.rentAcar.business.response.color.GetAllColorResponse;
import com.kodlamaio.rentAcar.business.response.color.ReadColorResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface ColorServices {
	Result add(CreateColorRequest createColorRequest );
	
	Result update(UpdateColorRequest updateColorRequest);
	

	DataResult<List<GetAllColorResponse>> getAll();

	

	Result delete(int id);

	DataResult<ReadColorResponse> findById(int id);
}
