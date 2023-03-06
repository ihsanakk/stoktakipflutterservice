package com.tempter.stoktakipservice.service;

import java.util.List;

import com.tempter.stoktakipservice.entity.User;
import com.tempter.stoktakipservice.rest.dto.ProductDTO;

public interface ProductService {
    

    List<ProductDTO> getUserProducts(User user);

    ProductDTO findUserProductByBarcode(User user, String barcode);

    ProductDTO saveUserProduct(User user, ProductDTO productDTO);

    ProductDTO deleteUserProductByBarcode(User user, String barcode);

}
