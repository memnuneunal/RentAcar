package com.kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.business.request.additionalItem.CreateAdditionalItemsRequest;
import com.kodlamaio.rentAcar.business.request.additionalItem.UpdateAdditionalItemsRequest;
import com.kodlamaio.rentAcar.business.response.additionalitems.GetAllAdditionalResponse;
import com.kodlamaio.rentAcar.business.response.additionalitems.ReadAdditionalResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface OrderedAdditionalItemService {

	Result delete(int id);

	DataResult<ReadAdditionalResponse> getById(int id);

	DataResult<List<GetAllAdditionalResponse>> getAll();

	Result add(CreateAdditionalItemsRequest createAdditionalItemRequest);

	Result update(UpdateAdditionalItemsRequest updateAdditionalItemRequest);

}
