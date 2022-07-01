package com.kodlamaio.rentAcar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.Entities.concretes.Address;

public interface AddressRepository  extends JpaRepository<Address, Integer>{


}