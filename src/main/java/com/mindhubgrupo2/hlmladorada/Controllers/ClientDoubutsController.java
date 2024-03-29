package com.mindhubgrupo2.hlmladorada.Controllers;

import com.mindhubgrupo2.hlmladorada.DTO.ClientDoubutsDTO;
import com.mindhubgrupo2.hlmladorada.DTO.EmployeeDTO;
import com.mindhubgrupo2.hlmladorada.DTO.LoginDTO;
import com.mindhubgrupo2.hlmladorada.DTO.RecordDoubutsDTO;
import com.mindhubgrupo2.hlmladorada.Repositories.ClientDoubutsRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.ClientStoreRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.EmployeeRepository;
import com.mindhubgrupo2.hlmladorada.models.ClientDoubuts;
import com.mindhubgrupo2.hlmladorada.models.ClientStore;
import com.mindhubgrupo2.hlmladorada.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientDoubuts")
public class ClientDoubutsController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ClientDoubutsRepository clientDoubutsRepository;

    @Autowired
    private ClientStoreRepository clientStoreRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllClientDoubuts() {
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeRepository.findByEmail(userMail);
        if (employee.getRole().toString().equals("ADMIN")) {
            List<ClientDoubuts> doubuts = clientDoubutsRepository.findAll();
            return new ResponseEntity<>(doubuts.stream().map(ClientDoubutsDTO::new).collect(Collectors.toList()), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to perform this action");
    }

    @PostMapping("/")
    public  ResponseEntity<?> createDoubut(@RequestBody RecordDoubutsDTO recordDoubutsDTO) {
        String userMail = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeRepository.findByEmail(userMail);
        if (employee.getRole().toString().equals("ADMIN") || employee.getRole().toString().equals("EMPLOYEE")) {
            if(clientStoreRepository.existsByRut(recordDoubutsDTO.rut())) {
                ClientStore client = clientStoreRepository.findByRut(recordDoubutsDTO.rut());

                ClientDoubuts clientDoubuts1 = new ClientDoubuts(
                        recordDoubutsDTO.amount(),
                        recordDoubutsDTO.date(),
                        recordDoubutsDTO.details()
                );

                client.addClientDoubuts(clientDoubuts1);

                clientStoreRepository.save(client);
                clientDoubutsRepository.save(clientDoubuts1);

                return ResponseEntity.status(HttpStatus.OK).body("successfully registered debt");

            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Client not found in records");
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to perform this action");
    }
}
