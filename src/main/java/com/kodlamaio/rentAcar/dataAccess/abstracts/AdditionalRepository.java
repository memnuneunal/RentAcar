package com.kodlamaio.rentAcar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.Entities.concretes.OrderedAdditionalItems;

public interface AdditionalRepository extends JpaRepository<OrderedAdditionalItems, Integer> {
//	List<Additional> findById(int rentalId);

	List<OrderedAdditionalItems> findByRentalId(int rentalId);
}
