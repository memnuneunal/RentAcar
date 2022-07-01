package com.kodlamaio.rentAcar.business.request.car;

import com.kodlamaio.rentAcar.Entities.concretes.Brand;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    private String description;
    private Double dailyPrice;
    private Double kilometer;
    private String carPlate;
    private int brand_id;
    private int color_id;
    private Brand brand;
    
}
