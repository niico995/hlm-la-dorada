package com.mindhubgrupo2.hlmladorada.controllers;


import com.mindhubgrupo2.hlmladorada.DTO.ProductDTO;
import com.mindhubgrupo2.hlmladorada.Repositories.ProductRepository;
import com.mindhubgrupo2.hlmladorada.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts(){
        List<Product> products = (List<Product>) productRepository.findAll();

        if(products.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry, no products in our store yet");
        }
        return new ResponseEntity<>(products.stream().map(ProductDTO::new).collect(Collectors.toList()), HttpStatus.OK);

    }


}
