package com.mindhubgrupo2.hlmladorada.Controllers;

import com.mindhubgrupo2.hlmladorada.DTO.NewPurchaseDTO;
import com.mindhubgrupo2.hlmladorada.DTO.PurchasesDTO;
import com.mindhubgrupo2.hlmladorada.Repositories.ProductRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.ProviderRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.PurchaseRepository;
import com.mindhubgrupo2.hlmladorada.models.Product;
import com.mindhubgrupo2.hlmladorada.models.Provider;
import com.mindhubgrupo2.hlmladorada.models.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

        @Autowired
        PurchaseRepository purchaseRepository;

        @Autowired
        ProviderRepository providerRepository;

        @Autowired
        ProductRepository productRepository;

        @GetMapping("/viewByDate")
        public ResponseEntity<?> viewPurchasesByDate(@PathVariable LocalDate date) {
            List<Purchase> purchases = purchaseRepository.findByDate(date);

            if(purchases.size() == 0){
                return new ResponseEntity<>("No purchases done that day", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(purchases.stream().map(PurchasesDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        }

        @GetMapping("/all")
        public ResponseEntity<?> allPruchases(){
            String user = SecurityContextHolder.getContext().getAuthentication().getName();
            List<Purchase> allPurchases = purchaseRepository.findAll();
            if(allPurchases.size() == 0){
                return new ResponseEntity<>("No purchases done yet",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(allPurchases.stream().map(PurchasesDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        }


        @PostMapping("/newPurchase")
        public ResponseEntity<?> newPurchase(@RequestBody NewPurchaseDTO newPurchaseDTO){
            try {
                String user = SecurityContextHolder.getContext().getAuthentication().getName();

                if(newPurchaseDTO.quantity() <= 0){
                    return new ResponseEntity<>("Only positive quantitys allowed",HttpStatus.BAD_REQUEST);
                }
                if(newPurchaseDTO.details().isBlank()){
                    return new ResponseEntity<>("Please, tell us something about the purchase", HttpStatus.BAD_REQUEST);
                }
                if(newPurchaseDTO.unitCost() <= 0){
                    return new ResponseEntity<>("Insert a valid cost",HttpStatus.BAD_REQUEST);
                }

                if(newPurchaseDTO.providerID().toString().isBlank()){
                    return new ResponseEntity<>("Provider must be selected",HttpStatus.BAD_REQUEST);
                }

                Provider providerName = providerRepository.findById(newPurchaseDTO.providerID()).orElse(null);

                if(providerName == null){
                    return new ResponseEntity<>("The provider do not exist",HttpStatus.BAD_REQUEST);
                }

                Product productName = productRepository.findById(newPurchaseDTO.productID()).orElse(null);
                if(productName == null){
                    return new ResponseEntity<>("The product do not exist",HttpStatus.BAD_REQUEST);
                }


                double totalCost = newPurchaseDTO.unitCost() * newPurchaseDTO.quantity();
                LocalDateTime date = LocalDateTime.now();

                Purchase newPurchase = new Purchase(newPurchaseDTO.quantity(),newPurchaseDTO.details(),date,newPurchaseDTO.unitCost(), totalCost);
                newPurchase.setProviderHolder(providerName);
                purchaseRepository.save(newPurchase);

                productName.setCost(newPurchaseDTO.unitCost());
                productName.setStock(productName.getStock()+ newPurchaseDTO.quantity());

                productRepository.save(productName);


                return new ResponseEntity<>("Your purchase has been recorded!", HttpStatus.CREATED);

            }catch (Exception e) {
                return new ResponseEntity<>("Sorry, something went wrong "+e, HttpStatus.BAD_REQUEST);
            }
        }
}
