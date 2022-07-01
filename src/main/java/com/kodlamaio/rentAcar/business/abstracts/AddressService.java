package com.kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentAcar.business.request.address.CreateAddressRequest;
import com.kodlamaio.rentAcar.business.request.address.UpdateAddressRequest;
import com.kodlamaio.rentAcar.business.response.address.GetAllAddressResponse;
import com.kodlamaio.rentAcar.business.response.address.ReadAddressResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface AddressService {

	Result add(CreateAddressRequest createAddressRequest);

	Result delete(int id);

	Result update(UpdateAddressRequest updateAddressRequest);

	DataResult<ReadAddressResponse> getById(int id);

	DataResult<List<GetAllAddressResponse>> getAll();

	DataResult<List<GetAllAddressResponse>> getAllBillAddress(int userId, int addressType);

	DataResult<List<GetAllAddressResponse>> getAllContactAddress(int userId, int addressType);

}
