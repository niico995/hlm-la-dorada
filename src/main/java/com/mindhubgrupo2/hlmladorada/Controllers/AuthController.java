package com.mindhubgrupo2.hlmladorada.Controllers;

import com.mindhubgrupo2.hlmladorada.DTO.LoginDTO;
import com.mindhubgrupo2.hlmladorada.DTO.RegisterDTO;
import com.mindhubgrupo2.hlmladorada.Repositories.ClientOnlineRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.ClientStoreRespository;
import com.mindhubgrupo2.hlmladorada.Repositories.EmployeeRepository;
import com.mindhubgrupo2.hlmladorada.models.ClientOnline;
import com.mindhubgrupo2.hlmladorada.models.Employee;
import com.mindhubgrupo2.hlmladorada.models.Role;
import com.mindhubgrupo2.hlmladorada.models.WorkPosition;
import com.mindhubgrupo2.hlmladorada.securityServices.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private ClientOnlineRepository clientOnlineRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientStoreRespository clientStoreRespository;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDTO loginDTO) {
        Employee employee = employeeRepository.findByEmail(loginDTO.email());
        ClientOnline clientOnline = clientOnlineRepository.findByEmail(loginDTO.email());


        if(loginDTO.email().isBlank()) {
            return new ResponseEntity<>("The email field must not be empty " , HttpStatus.FORBIDDEN);
        }
        if(loginDTO.password().isBlank()) {
            return new ResponseEntity<>("The password field must not be empty " , HttpStatus.FORBIDDEN);
        }
        if(employee == null && clientOnline == null) {
            return new ResponseEntity<>("The email entered is not valid", HttpStatus.FORBIDDEN);
        }
        if (employee != null) {
            if(!passwordEncoder.matches(loginDTO.password(), employee.getPassword())) {
                return new ResponseEntity<>("The password entered is not valid", HttpStatus.FORBIDDEN);
            }
        }
        if(!passwordEncoder.matches(loginDTO.password(), clientOnline.getPassword())) {
            return new ResponseEntity<>("The password entered is not valid", HttpStatus.FORBIDDEN);
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.email());
        final String jwt = jwtUtilService.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {

        if(registerDTO.firstName().isBlank()) {
            return new ResponseEntity<>("The name field must not be empty " , HttpStatus.FORBIDDEN);
        }
        if(registerDTO.lastName().isBlank()) {
            return new ResponseEntity<>("The last name field must not be empty " , HttpStatus.FORBIDDEN);
        }
        if(registerDTO.email().isBlank()) {
            return new ResponseEntity<>("The email field must not be empty " , HttpStatus.FORBIDDEN);
        }
        if(registerDTO.password().isBlank()) {
            return new ResponseEntity<>("The password field must not be empty " , HttpStatus.FORBIDDEN);
        }
        if(!registerDTO.email().contains("@")) {
            return new ResponseEntity<>("The email does not have @" , HttpStatus.FORBIDDEN);
        }
        if(registerDTO.password().length() < 5) {
            return new ResponseEntity<>("Your password must be at least 5 characters" , HttpStatus.FORBIDDEN);
        }
        if(clientOnlineRepository.existsByEmail(registerDTO.email())) {
            return new ResponseEntity<>("The email is already registered", HttpStatus.FORBIDDEN);
        }

        ClientOnline newClient = new ClientOnline(
                registerDTO.firstName(),
                registerDTO.lastName(),
                registerDTO.email(),
                passwordEncoder.encode(registerDTO.password())
        );

        clientOnlineRepository.save(newClient);

        return ResponseEntity.status(HttpStatus.CREATED).body("Your account was created successfully");
    }

    @PostMapping("/register/employee")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {

        if(registerDTO.firstName().isBlank()) {
            return new ResponseEntity<>("The name field must not be empty " , HttpStatus.FORBIDDEN);
        }
        if(registerDTO.lastName().isBlank()) {
            return new ResponseEntity<>("The last name field must not be empty " , HttpStatus.FORBIDDEN);
        }
        if(registerDTO.email().isBlank()) {
            return new ResponseEntity<>("The email field must not be empty " , HttpStatus.FORBIDDEN);
        }
        if(registerDTO.password().isBlank()) {
            return new ResponseEntity<>("The password field must not be empty " , HttpStatus.FORBIDDEN);
        }
        if(!registerDTO.email().contains("@")) {
            return new ResponseEntity<>("The email does not have @" , HttpStatus.FORBIDDEN);
        }
        if(registerDTO.password().length() < 5) {
            return new ResponseEntity<>("Your password must be at least 5 characters" , HttpStatus.FORBIDDEN);
        }
        if(employeeRepository.existsByEmail(registerDTO.email())) {
            return new ResponseEntity<>("The email is already registered", HttpStatus.FORBIDDEN);
        }

        Employee newClient = new Employee(
                registerDTO.firstName(),
                registerDTO.lastName(),
                registerDTO.email(),
                passwordEncoder.encode(registerDTO.password()),
                Role.valueOf(registerDTO.role()),
                WorkPosition.valueOf(registerDTO.workPosition())
        );

        employeeRepository.save(newClient);

        return ResponseEntity.status(HttpStatus.CREATED).body("Your account was created successfully");
    }

}
