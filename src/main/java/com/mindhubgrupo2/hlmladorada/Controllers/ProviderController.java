package com.mindhubgrupo2.hlmladorada.Controllers;

import com.mindhubgrupo2.hlmladorada.DTO.ProviderDTO;
import com.mindhubgrupo2.hlmladorada.DTO.ProviderRegisterDTO;
import com.mindhubgrupo2.hlmladorada.Repositories.ProviderRepository;
import com.mindhubgrupo2.hlmladorada.models.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    @Autowired
    ProviderRepository providerRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getListOfProvider(){
        List<Provider> providers = (List<Provider>) providerRepository.findAll();

        if (providers.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No providers added to the system yet");
        }

        return new ResponseEntity<>(providers.stream().map(ProviderDTO::new).collect(Collectors.toList()), HttpStatus.OK);

    }


    @PostMapping("/register")
    public ResponseEntity<?> registerProvider(@RequestBody ProviderRegisterDTO providerDTO){
        try {
            String user = SecurityContextHolder.getContext().getAuthentication().getName();

            if(providerDTO.name().isBlank()){
                return new ResponseEntity<>("Sorry, you need to fill the name input", HttpStatus.BAD_REQUEST);
            }
            if(providerDTO.email().isBlank()){
                return new ResponseEntity<>("Sorry, you need to fill the e-mail input", HttpStatus.BAD_REQUEST);
            }
            if(providerDTO.RUC().isBlank()){
                return new ResponseEntity<>("Sorry, you need to fill the RUC input", HttpStatus.BAD_REQUEST);
            }
            if(providerDTO.phone().isBlank()){
                return new ResponseEntity<>("Sorry, you need to fill the phone input", HttpStatus.BAD_REQUEST);
            }
            if(providerDTO.adress().isBlank()){
                return new ResponseEntity<>("Sorry, you need to fill the adress input", HttpStatus.BAD_REQUEST);
            }

            Provider newProvider = new Provider(providerDTO.name(), providerDTO.phone(),providerDTO.email(),providerDTO.RUC(),providerDTO.adress());

            providerRepository.save(newProvider);

            return new ResponseEntity<>(providerDTO.name()+" was added to your provider list",HttpStatus.CREATED);


        }
        catch (Exception e) {
            return new ResponseEntity<>("Something went wrong: " + e, HttpStatus.BAD_REQUEST);
        }

    }




}
