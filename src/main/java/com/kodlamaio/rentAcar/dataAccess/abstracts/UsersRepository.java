package com.kodlamaio.rentAcar.dataAccess.abstracts;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.Entities.concretes.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
	Users findById(int id);

	//Page<Users> findAll(Pageable pageable);

}
