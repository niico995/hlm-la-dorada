package com.mindhubgrupo2.hlmladorada.Controllers;

import com.mindhubgrupo2.hlmladorada.DTO.CartDTO;
import com.mindhubgrupo2.hlmladorada.DTO.CartDetailsDTO;
import com.mindhubgrupo2.hlmladorada.DTO.RecordCartDetailDTO;
import com.mindhubgrupo2.hlmladorada.Repositories.CartRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.EmployeeRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.ProductRepository;
import com.mindhubgrupo2.hlmladorada.models.Cart;
import com.mindhubgrupo2.hlmladorada.models.CartDetails;
import com.mindhubgrupo2.hlmladorada.models.Employee;
import com.mindhubgrupo2.hlmladorada.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/cartsDetails")
    public ResponseEntity<?> postCartDetails(@RequestBody RecordCartDetailDTO recordCartDetailDTO) {
        Product product = productRepository.findById(recordCartDetailDTO.productoId()).orElse(null);
        if (product != null) {
            CartDetails cartDetails = new CartDetails(recordCartDetailDTO.quantity(), recordCartDetailDTO.amount());


        }
        return null;
    }

    @PostMapping("/carts")
    public ResponseEntity<?> postCart(@RequestBody RecordCartDetailDTO recordCartDetailDTO) {

    }
}
