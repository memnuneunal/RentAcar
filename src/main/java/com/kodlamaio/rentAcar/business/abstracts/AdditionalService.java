package com.kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.business.request.additionalService.CreateAdditionalsRequest;
import com.kodlamaio.rentAcar.business.request.additionalService.UpdateAdditionalsRequest;
import com.kodlamaio.rentAcar.business.response.additionalitems.GetAllAdditionalResponse;
import com.kodlamaio.rentAcar.business.response.additionalitems.ReadAdditionalResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface AdditionalService {

	Result delete(int id);

	Result update(UpdateAdditionalsRequest updateAdditionalRequest);

	Result add(CreateAdditionalsRequest createAdditionalRequest);

	DataResult<ReadAdditionalResponse> findById(int id);

	DataResult<List<GetAllAdditionalResponse>> getAll();

}
