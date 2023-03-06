package com.tempter.stoktakipservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tempter.stoktakipservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    

}
