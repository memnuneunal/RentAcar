package com.kodlamaio.rentAcar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.kodlamaio.rentAcar.Entities.concretes.IndividualCustomer;
import com.kodlamaio.rentAcar.Entities.concretes.Users;
import com.kodlamaio.rentAcar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentAcar.business.response.users.GetAllUserResponse;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;

public interface UserService {

	DataResult<List<GetAllUserResponse>> getAll(Integer pageNumber, Integer pageSize);

	Result add(@Valid CreateUserRequest createUserRequest);
	public Users getByUserId(int id);


}
