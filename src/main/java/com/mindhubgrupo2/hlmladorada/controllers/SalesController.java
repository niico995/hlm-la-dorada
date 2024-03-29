package com.mindhubgrupo2.hlmladorada.Controllers;

import com.mindhubgrupo2.hlmladorada.DTO.SalesDTO;
import com.mindhubgrupo2.hlmladorada.Repositories.CartRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.ProductRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.SalesRepository;
import com.mindhubgrupo2.hlmladorada.models.Cart;
import com.mindhubgrupo2.hlmladorada.models.Product;
import com.mindhubgrupo2.hlmladorada.models.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sales")
public class SalesController {


    @Autowired
    CartRepository cartRepository;

    @Autowired
    SalesRepository salesRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getSales(){
        List<Sales> allSales = (List<Sales>) salesRepository.findAll();
        if(allSales.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No sales done yet");
        }
        return new ResponseEntity<>(allSales.stream().map(SalesDTO::new).collect(Collectors.toList()), HttpStatus.OK);
    }

}
