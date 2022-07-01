package com.kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.Address;
import com.kodlamaio.rentAcar.business.abstracts.AddressService;
import com.kodlamaio.rentAcar.business.request.address.CreateAddressRequest;
import com.kodlamaio.rentAcar.business.request.address.UpdateAddressRequest;
import com.kodlamaio.rentAcar.business.response.address.GetAllAddressResponse;
import com.kodlamaio.rentAcar.business.response.address.ReadAddressResponse;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.AddressRepository;
@Service
public class AddressManager implements AddressService{
	
	private AddressRepository addressRepository;
	private ModelMapperService modelMapperService;

	@Override
	public Result add(CreateAddressRequest createAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.ADDED");
	}

	@Override
	public Result delete(int id) {
		this.addressRepository.deleteById(id);
		return new SuccessResult("ADDRESS.DELETED");
	}

	@Override
	public Result update(UpdateAddressRequest updateAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.UPDATED");
	}

	@Override
	public DataResult<List<GetAllAddressResponse>> getAll() {
		List<Address> getAllAddressesResponses = this.addressRepository.findAll();
		
		List<GetAllAddressResponse> response = getAllAddressesResponses.stream()
				.map(address -> this.modelMapperService.forResponse().map(address, GetAllAddressResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAddressResponse>>(response);
	}

	@Override
	public DataResult<ReadAddressResponse> getById(int id) {
		Address address = this.addressRepository.findById(id).get();
		
		ReadAddressResponse response = this.modelMapperService.forResponse().map(address, ReadAddressResponse.class);
		
		return new SuccessDataResult<ReadAddressResponse>(response);
	}

	@Override
	public DataResult<List<GetAllAddressResponse>> getAllBillAddress(int userId, int addressType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<GetAllAddressResponse>> getAllContactAddress(int userId, int addressType) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public DataResult<List<GetAllAddressResponse>> getAllBillAddress(int userId, int addressType) {
		List<Address> addresses = this.addressRepository.getByUserIdAndAddressType(userId, addressType);
		List<GetAllAddressResponse> response = addresses.stream()
				.map(address -> this.modelMapperService.forResponse()
				.map(address, GetAllAddressResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAddressResponse>>(response);
	}

	@Override
	public DataResult<List<GetAllAddressResponse>> getAllContactAddress(int userId, int addressType) {
		List<Address> addresses = this.addressRepository.getByUserIdAndAddressType(userId, addressType);
		List<GetAllAddressResponse> response = addresses.stream()
				.map(address -> this.modelMapperService.forResponse()
				.map(address, GetAllAddressResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllAddressResponse>>(response);
	}*/

}
