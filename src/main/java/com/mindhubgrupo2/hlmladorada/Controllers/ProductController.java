package com.mindhubgrupo2.hlmladorada.Controllers;


import com.mindhubgrupo2.hlmladorada.DTO.ChangeQuantityDTO;
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
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();

        if (products.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry, no products in our store yet");
        }
        return new ResponseEntity<>(products.stream().map(ProductDTO::new).collect(Collectors.toList()), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> productByID(@PathVariable Long id){
        Product product = productRepository.findById(id).orElse(null);

        if(product == null){
            return  new ResponseEntity<>("No product matches the id: " +id, HttpStatus.NOT_FOUND);
        }

        ProductDTO productDTO = new ProductDTO(product);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PostMapping("/newProduct")
    public ResponseEntity<?> addNewProducto(@RequestBody RegisterProductDTO registerProductDTO) {
        try {
            if (registerProductDTO.name().isBlank()) {
                return new ResponseEntity<>("Please, complete the name", HttpStatus.BAD_REQUEST);
            }
            if (registerProductDTO.brand().isBlank()) {
                return new ResponseEntity<>("Please, complete the brand", HttpStatus.BAD_REQUEST);
            }
            if (registerProductDTO.details().isBlank()) {
                return new ResponseEntity<>("Please, add some details about the product", HttpStatus.BAD_REQUEST);
            }
            if (registerProductDTO.revenue() < 20) {
                return new ResponseEntity<>("Introduce a value of 20 or above ", HttpStatus.BAD_REQUEST);
            }
            if (registerProductDTO.category().isBlank()) {
                return new ResponseEntity<>("Please, complete the category", HttpStatus.BAD_REQUEST);
            }

            Product newProduct = new Product(registerProductDTO.name(), 0, 0, 0, registerProductDTO.revenue(), registerProductDTO.details(), registerProductDTO.brand(), registerProductDTO.category(), 0, registerProductDTO.imageURL());
            productRepository.save(newProduct);

            return new ResponseEntity<>("Congrats, new product added to the inventory: " + registerProductDTO.name(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Sorry, something went wrong: " + e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/changeQuantity")
    public ResponseEntity<?> changeProductQuantity(@RequestBody ChangeQuantityDTO changeQuantityDTO){
        try {
            Product productChange = productRepository.findById(changeQuantityDTO.productID()).orElse(null);

            if (productChange == null){
                return new ResponseEntity<>("Producto incorrecto, intente nuevamente",HttpStatus.BAD_REQUEST);
            }

            if (changeQuantityDTO.quantity() < 0){
                productChange.setStock(productChange.getStock() - changeQuantityDTO.quantity());
            } else {
                productChange.setStock(productChange.getStock() + changeQuantityDTO.quantity());
            }
            productRepository.save(productChange);

            return new ResponseEntity<>("Stock modificado en el producto: "+productChange.getName(),HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Lo sentimos, algo salio mal: "+e, HttpStatus.BAD_REQUEST);
        }
    }
}


