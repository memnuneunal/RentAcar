package com.kodlamaio.rentAcar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.Entities.concretes.Car;
//primary key id integer 

//Integer defaultı nulldır
//String referans tipi newleyerek oluşur.
//findByID!yazmana gerek yok
//data manupilation 
//checif car user  exists
//bakımda araba var mı rent içinde kontrol et
//tarih kontrol rental için


public interface CarRepository extends JpaRepository<Car, Integer>{

	Car findById(int id);
	List<Car> findByBrandId(int id);

	Car findBycarPlate(String carPlate);
}
