package com.mindhubgrupo2.hlmladorada.Controllers;

import com.mindhubgrupo2.hlmladorada.DTO.CartDetailsDTO;
import com.mindhubgrupo2.hlmladorada.DTO.ClientOnlineDTO;
import com.mindhubgrupo2.hlmladorada.Repositories.CartDetalsRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.EmployeeRepository;
import com.mindhubgrupo2.hlmladorada.models.CartDetails;
import com.mindhubgrupo2.hlmladorada.models.ClientOnline;
import com.mindhubgrupo2.hlmladorada.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cartDetails")
public class CartDetailController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CartDetalsRepository cartDetalsRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllCartsDetails(){
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeRepository.findByEmail(userMail);
        if (employee.getRole().toString().equals("ADMIN")) {
            List<CartDetails> cartsDetails = cartDetalsRepository.findAll();
            return new ResponseEntity<>(cartsDetails.stream().map(CartDetailsDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to perform this action");
    }
}
