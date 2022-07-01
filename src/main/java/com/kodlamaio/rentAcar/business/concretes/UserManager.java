package com.kodlamaio.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentAcar.Entities.concretes.IndividualCustomer;
import com.kodlamaio.rentAcar.Entities.concretes.Users;
import com.kodlamaio.rentAcar.business.abstracts.UserService;
import com.kodlamaio.rentAcar.business.request.users.CreateUserRequest;
import com.kodlamaio.rentAcar.business.response.users.GetAllUserResponse;
import com.kodlamaio.rentAcar.core.Utilies.exceptions.BusinessException;
import com.kodlamaio.rentAcar.core.Utilies.mapping.ModelMapperService;
import com.kodlamaio.rentAcar.core.Utilies.results.DataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.Result;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessDataResult;
import com.kodlamaio.rentAcar.core.Utilies.results.SuccessResult;
import com.kodlamaio.rentAcar.dataAccess.abstracts.UsersRepository;

@Service
public class UserManager implements UserService {

	ModelMapperService modelMapperService;
	UsersRepository userRepository;

	@Autowired
	public UserManager(UsersRepository userRepository, ModelMapperService modelMapperService) {
		super();
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) {
		Users user = this.modelMapperService.forRequest().map(createUserRequest, Users.class);

		this.userRepository.save(user);
		return new SuccessResult("ADDED.USER");

	}

	@Override
	public DataResult<List<GetAllUserResponse>> getAll(Integer pageNumber, Integer pageSize) {
		PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);

		List<Users> users = this.userRepository.findAll(pageable).getContent();
		List<GetAllUserResponse> response = users.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, GetAllUserResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllUserResponse>>(response);
	}

	@Override
	public Users getByUserId(int id) {
		return checkIfUserExistsById(id);
	}

	private Users checkIfUserExistsById(int id) {
		Users currentUser;
		try {
			currentUser = this.userRepository.findById(id);

		} catch (Exception e) {
			throw new BusinessException("USER.NOT.EXISTS");
		}
		return currentUser;

	}




}