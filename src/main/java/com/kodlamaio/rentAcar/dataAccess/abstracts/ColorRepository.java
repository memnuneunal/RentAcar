package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.Entities.concretes.Color;

public interface ColorRepository extends JpaRepository<Color, Integer>{

	Color findByName(String name);

}
