package com.mindhubgrupo2.hlmladorada.Repositories;

import com.mindhubgrupo2.hlmladorada.models.ClientOnline;
import com.mindhubgrupo2.hlmladorada.models.ClientStore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientStoreRespository extends JpaRepository<ClientStore, Long> {
   /* Boolean existsByEmail(String email);

    ClientOnline findByEmail(String email);*/
}
