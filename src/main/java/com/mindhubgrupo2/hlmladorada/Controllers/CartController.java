package com.mindhubgrupo2.hlmladorada.Controllers;

import com.mindhubgrupo2.hlmladorada.DTO.CartDTO;
import com.mindhubgrupo2.hlmladorada.DTO.CartDetailsDTO;
import com.mindhubgrupo2.hlmladorada.DTO.RecordCartDetailDTO;
import com.mindhubgrupo2.hlmladorada.Repositories.*;
import com.mindhubgrupo2.hlmladorada.models.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartDetalsRepository cartDetalsRepository;

    @Autowired
    private ClientOnlineRepository clientOnlineRepository;

    @Autowired
    private ClientStoreRepository clientStoreRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllCarts(){
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeRepository.findByEmail(userMail);
        if (employee.getRole().toString().equals("ADMIN")) {
            List<Cart> carts = cartRepository.findAll();
            return new ResponseEntity<>(carts.stream().map(CartDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to perform this action");
    }

    @Transactional
    @PostMapping("/")
    public ResponseEntity<?> postCart(@RequestBody List<RecordCartDetailDTO> cartsDetails) {

        Cart cartCurrent = new Cart();

        cartsDetails.stream().map(cartDetail -> {
            Product product = productRepository.findById(cartDetail.productoId()).orElse(null);

            if(product != null) {

                if (cartDetail.quantity() > product.getStock()) {
                    return new ResponseEntity<>("Insufficient " + product.getName() + " stock", HttpStatus.FORBIDDEN);
                }

                CartDetails newCartDetails = new CartDetails(cartDetail.quantity(), cartDetail.amount());

                product.addCartDetail(newCartDetails);

                cartCurrent.addCartDetails(newCartDetails);

                productRepository.save(product);
                cartDetalsRepository.save(newCartDetails);
                cartRepository.save(cartCurrent);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Product not found by its ID");
            }

            return cartCurrent;
        });
        if(!cartCurrent.getCartDetails().isEmpty()) {
            String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
            ClientOnline clientOnlineCurrent = clientOnlineRepository.findByEmail(userMail);

            clientOnlineCurrent.addCart(cartCurrent);

            clientOnlineRepository.save(clientOnlineCurrent);
            cartRepository.save(cartCurrent);
        }


        return new ResponseEntity<>(cartCurrent, HttpStatus.OK);
    }

}
