package com.mindhubgrupo2.hlmladorada.Controllers;


import com.mindhubgrupo2.hlmladorada.DTO.ProductDTO;
import com.mindhubgrupo2.hlmladorada.DTO.RegisterProductDTO;
import com.mindhubgrupo2.hlmladorada.Repositories.ProductRepository;
import com.mindhubgrupo2.hlmladorada.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
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



    @PostMapping("/newProduct")
    public ResponseEntity<?> addNewProducto(@RequestBody RegisterProductDTO registerProductDTO){
        try {
            if(registerProductDTO.name().isBlank()){
                return new ResponseEntity<>("Please, complete the name", HttpStatus.BAD_REQUEST);
            }
            if(registerProductDTO.brand().isBlank()){
                return new ResponseEntity<>("Please, complete the brand", HttpStatus.BAD_REQUEST);
            }
            if (registerProductDTO.details().isBlank()){
                return new ResponseEntity<>("Please, add some details about the product", HttpStatus.BAD_REQUEST);
            }
            if (registerProductDTO.revenue() < 20){
                return new ResponseEntity<>("Introduce a value of 20 or above ", HttpStatus.BAD_REQUEST);
            }
            if (registerProductDTO.category().isBlank()){
                return new ResponseEntity<>("Please, complete the category", HttpStatus.BAD_REQUEST);
            }

            Product newProduct = new Product(registerProductDTO.name(),0,0,0, registerProductDTO.revenue(), registerProductDTO.details(), registerProductDTO.brand(), registerProductDTO.category(), Set.of(0));
            productRepository.save(newProduct);

            return new ResponseEntity<>("Congrats, new product added to the inventory: "+ registerProductDTO.name(),HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>("Sorry, something went wrong: " + e, HttpStatus.BAD_REQUEST);
        }
    }



