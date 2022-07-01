package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.Entities.concretes.City;

public interface CityRepository extends JpaRepository<City,Integer> {
	City findById(int id);

}
