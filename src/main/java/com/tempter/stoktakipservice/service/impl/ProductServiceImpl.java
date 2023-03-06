package com.tempter.stoktakipservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tempter.stoktakipservice.advice.exceptions.EntityNotFoundException;
import com.tempter.stoktakipservice.entity.Product;
import com.tempter.stoktakipservice.entity.User;
import com.tempter.stoktakipservice.repository.ProductRepository;
import com.tempter.stoktakipservice.rest.dto.ProductDTO;
import com.tempter.stoktakipservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{


    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public List<ProductDTO> getUserProducts(User user) {
        return Arrays.asList(mapper.map(user.getProducts(), ProductDTO[].class));
    }

    @Override
    public ProductDTO findUserProductByBarcode(User user, String barcode) {
        List<Product> productList = user.getProducts();
        Optional<Product> optProduct = productList.stream().filter((e) -> {
            return e.getBarcode().equals(barcode);
        }).findFirst();
        Product product = optProduct.orElseThrow(() -> new EntityNotFoundException("Product not found by barcode: "+ barcode));
        return mapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO saveUserProduct(User user, ProductDTO productDTO) {
        List<Product> productList = user.getProducts();
        Optional<Product> optProduct = productList.stream().filter((e) -> {
            return e.getBarcode().equals(productDTO.getBarcode());
        }).findFirst();
        Product product;
        if (optProduct.isEmpty()) 
            product = mapper.map(productDTO, Product.class);
        else 
            product = optProduct.get();
        
        product.setName(productDTO.getName());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        
        product.setUser(user);
        productRepository.save(product);
        return productDTO;

    }

    @Override
    public ProductDTO deleteUserProductByBarcode(User user, String barcode) {
        List<Product> productList = user.getProducts();
        Optional<Product> optProduct = productList.stream().filter((e) -> {
            return e.getBarcode().equals(barcode);
        }).findFirst();
        Product product = optProduct.orElseThrow(() -> new EntityNotFoundException("Product not found by barcode: "+ barcode));
        
        ProductDTO productDTO = mapper.map(product, ProductDTO.class);
        productRepository.deleteById(product.getId());
        return productDTO;
    }
    
}
