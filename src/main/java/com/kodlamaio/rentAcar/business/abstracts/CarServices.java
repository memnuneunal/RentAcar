package com.kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.business.request.car.CreateCarRequest;
import com.kodlamaio.rentAcar.business.request.car.UpdateCarRequest;
import com.kodlamaio.rentAcar.business.response.car.GetAllCarsResponse;
import com.kodlamaio.rentAcar.business.response.car.ReadCarResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface CarServices {

	Result add(CreateCarRequest createCarRequest);
	Result update(UpdateCarRequest updateCarRequest);
	//DataResult< List<Car>> getAll();
	DataResult<List<GetAllCarsResponse>> getAll();
	DataResult<ReadCarResponse> findById(int id);
	Result delete(int id);


}
