package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.Entities.concretes.AdditionalItems;


public interface AdditionalItemRepository extends JpaRepository<AdditionalItems, Integer> {

	AdditionalItems findByName(String name);

}
