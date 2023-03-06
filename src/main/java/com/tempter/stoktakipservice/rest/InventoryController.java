package com.tempter.stoktakipservice.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tempter.stoktakipservice.entity.User;
import com.tempter.stoktakipservice.repository.UserRepository;
import com.tempter.stoktakipservice.rest.dto.ProductDTO;
import com.tempter.stoktakipservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    
    private final UserRepository userRepository;
    private final ProductService productService;

    @GetMapping("/all")
    ResponseEntity<List<ProductDTO>> getUserProducts(Principal principal) {
        
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Anonymous user"));

        return ResponseEntity.ok().body(productService.getUserProducts(user));
    }

    @GetMapping("/product/{barcode}")
    ResponseEntity<ProductDTO> getUserProductByBarcode(Principal principal, @PathVariable String barcode) {
        
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Anonymous user"));

        return ResponseEntity.ok().body(productService.findUserProductByBarcode(user, barcode));
    }

    @PostMapping("/save")
    ResponseEntity<ProductDTO> saveUserProduct(Principal principal, @RequestBody ProductDTO productDTO) {
        
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Anonymous user"));

        return ResponseEntity.ok().body(productService.saveUserProduct(user, productDTO));
    }

    @DeleteMapping("/product/{barcode}")
    ResponseEntity<ProductDTO> deleteUserProductByBarcode(Principal principal, @PathVariable String barcode) {
        
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Anonymous user"));

        return ResponseEntity.ok().body(productService.deleteUserProductByBarcode(user, barcode));
    }

}
