package com.mindhubgrupo2.hlmladorada;


import com.mindhubgrupo2.hlmladorada.Repositories.*;
import com.mindhubgrupo2.hlmladorada.models.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class HlmladoradaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HlmladoradaApplication.class, args);}

		@Bean
		public CommandLineRunner initData(ProductRepository productRepository, PromoRepository promoRepository, CartRepository cartRepository, CartDetalsRepository cartDetalsRepository, ClientStoreRespository clientStoreRespository, ClientDoubutsRepository clientDoubutsRepository, EmployeeRepository employeeRepository, SalesRepository salesRepository) {
			return args -> {
				Promo promos = new Promo(Set.of(0, 10, 15, 20));

				Product product1 = new Product("Monitor", 15, 150, 250, 20, "15' Pulgadas", "Samsung", "Tecnologia", Set.of(0, 10, 15, 20));
				Product product2 = new Product("Monitor", 15, 150, 250, 20, "15' Pulgadas", "Samsung", "Tecnologia", Set.of(0, 10, 15, 20));
				Product product3 = new Product("Monitor", 15, 150, 250, 20, "15' Pulgadas", "Samsung", "Tecnologia", Set.of(0, 10, 15, 20));
				Product product4 = new Product("Monitor", 15, 150, 250, 20, "15' Pulgadas", "Samsung", "Tecnologia", Set.of(0, 10, 15, 20));
				Product product5 = new Product("Monitor", 15, 150, 250, 20, "15' Pulgadas", "Samsung", "Tecnologia", Set.of(0, 10, 15, 20));



				productRepository.save(product1);
				productRepository.save(product2);
				productRepository.save(product3);
				productRepository.save(product4);
				productRepository.save(product5);


				System.out.println(product1);

				int quantity = 2;
				double amount = product1.getFinalPrice() * quantity;
				CartDetails cart1 = new CartDetails(quantity, amount);
				Cart cartFinal = new Cart();

				cart1.addProducts(product1);

				cartDetalsRepository.save(cart1);

				cartFinal.addCartDetails(cart1);

				cartRepository.save(cartFinal);

				System.out.println(cart1);
				System.out.println(cartFinal);

				ClientDoubuts dobouts1 = new ClientDoubuts(150.00, LocalDateTime.now(), "Probando deudas");

				ClientStore clientStore1 = new ClientStore("Cosme", "Fulanito", "+5401169993331", "15123987");

				clientStore1.setDoubutHolder(dobouts1);

				clientDoubutsRepository.save(dobouts1);
				clientStoreRespository.save(clientStore1);
				dobouts1.setClientStoreHolder(clientStore1);
				clientDoubutsRepository.save(dobouts1);

				Employee tiendita = new Employee("Tienda", "Online", "nuestro@correo", "tienda123", Role.EMPLOYEE, WorkPosition.ECOMMMERCE);

				double finalWithTaxes = cart1.getAmount() + cart1.getAmount() + cart1.getAmount() * 1.105;

				Sales ventaTest = new Sales("Probando venta", finalWithTaxes, List.of("Credit"), List.of(10.5));

				tiendita.addSale(ventaTest);

				employeeRepository.save(tiendita);

				ventaTest.setCartHolder(cartFinal);

				salesRepository.save(ventaTest);

				clientStore1.addCart(cartFinal);

				clientStoreRespository.save(clientStore1);

				cartRepository.save(cartFinal);


				System.out.println(clientStore1);
				System.out.println(ventaTest);
			};
		}


}
