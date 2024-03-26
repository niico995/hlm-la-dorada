package com.mindhubgrupo2.hlmladorada.Repositories;

import com.mindhubgrupo2.hlmladorada.models.ClientDoubuts;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity
public interface ClientDoubutsRepository extends JpaRepository<ClientDoubuts, Long> {

    Boolean existsByEmail(String email);

    ClientDoubuts findByEmail(String email);
}
