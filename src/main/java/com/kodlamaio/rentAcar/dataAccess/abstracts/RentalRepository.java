package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.Entities.concretes.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer>{
	Rental findById(int id);
}
