package com.kodlamaio.rentAcar.business.abstracts;

import com.kodlamaio.rentAcar.Entities.concretes.IndividualCustomer;

public interface UserCheckService {

		boolean checkIfRealPerson(IndividualCustomer individualCustomer);
	}

