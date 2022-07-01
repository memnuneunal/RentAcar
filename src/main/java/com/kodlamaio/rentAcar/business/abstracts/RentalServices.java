package com.kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.business.request.rental.CreateRentalRequest;
import com.kodlamaio.rentAcar.business.request.rental.UpdateRentalRequest;
import com.kodlamaio.rentAcar.business.response.rental.GetAllRentalResponse;
import com.kodlamaio.rentAcar.business.response.rental.ReadRentalResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface RentalServices {

	Result add(CreateRentalRequest createRentalRequest);

	Result delete(int id);

	DataResult<ReadRentalResponse> findById(int id);

	DataResult<List<GetAllRentalResponse>> getAll();

	Result update(UpdateRentalRequest updateRentalRequest);

	
}
