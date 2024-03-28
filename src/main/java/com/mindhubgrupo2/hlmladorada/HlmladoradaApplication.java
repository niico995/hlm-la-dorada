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
		public CommandLineRunner initData (ProductRepository productRepository, PromoRepository
		promoRepository, CartRepository cartRepository, CartDetalsRepository cartDetalsRepository, ClientStoreRespository clientStoreRespository, ClientDoubutsRepository clientDoubutsRepository, EmployeeRepository employeeRepository,
										   SalesRepository salesRepository){
			return args -> {
				Promo promos = new Promo(Set.of(0, 10, 15, 20));

				Product product1 = new Product("Monitor", 15, 150, 250, 20, "15' Pulgadas", "Samsung", "Tecnologia",Set.of(0, 10, 15, 20));

				//product1.setPromos(promos);

				productRepository.save(product1);

				System.out.println(product1);

				int quantity = 2;
				double amount = product1.getFinalPrice() * quantity;
				CartDetails cart1 = new CartDetails(quantity, amount);
				CartDetails cart2 = new CartDetails(quantity,amount);
				CartDetails cart3 = new CartDetails(quantity,amount);

				cart1.addProducts(product1);

				Cart cartFinal = new Cart();

				cartDetalsRepository.save(cart1);
				cartDetalsRepository.save(cart2);
				cartDetalsRepository.save(cart3);

				cartFinal.addCartDetails(cart1);
				cartFinal.addCartDetails(cart2);
				cartFinal.addCartDetails(cart3);


				cartFinal.setFinalAmount(cart1.getAmount()+cart2.getAmount()+cart3.getAmount());

				//cartRepository.save(cartFinal);


				System.out.println(cart1);
				System.out.println("*******");
				System.out.println(cartFinal);

				//Promo promos = new Promo(Set.of(0, 10, 15, 20));
				ClientDoubuts dobouts1 = new ClientDoubuts(150.00, LocalDateTime.now(),"Probando deudas");

				ClientStore clientStore1 = new ClientStore("Cosme","Fulanito","+5401169993331","15123987",150.00);
				ClientOnline clientOnline1 = new ClientOnline("Orlando","Contreras","mail@prueba","prueba123");

				clientStore1.setDoubut(dobouts1);


				clientDoubutsRepository.save(dobouts1);
				clientStoreRespository.save(clientStore1);
				dobouts1.setClientStoreHolder(clientStore1);
				clientDoubutsRepository.save(dobouts1);
				Employee tiendita = new Employee("Tienda","Online","nuestro@correo","tienda123",Role.EMPLOYEE,WorkPosition.ECOMMMERCE);



				double finalWithTaxes = cartFinal.getFinalAmount() * 1.105 ;

				Sales ventaTest = new Sales("Our first sale",finalWithTaxes, List.of("credit card"),List.of(10.5),tiendita,cartFinal);
				tiendita.addSale(ventaTest);
				employeeRepository.save(tiendita);
				cartFinal.addSales(ventaTest);
				cartRepository.save(cartFinal);
				salesRepository.save(ventaTest);



				System.out.println(clientStore1);
				System.out.println(ventaTest);

			};
		}

}
