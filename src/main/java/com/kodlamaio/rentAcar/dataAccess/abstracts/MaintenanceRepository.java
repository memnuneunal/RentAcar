package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.Entities.concretes.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
  Maintenance findById(int carId);

}
