package com.mindhubgrupo2.hlmladorada;


import com.mindhubgrupo2.hlmladorada.Repositories.CartDetalsRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.CartRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.ProductRepository;
import com.mindhubgrupo2.hlmladorada.Repositories.PromoRepository;
import com.mindhubgrupo2.hlmladorada.models.Cart;
import com.mindhubgrupo2.hlmladorada.models.CartDetails;
import com.mindhubgrupo2.hlmladorada.models.Product;
import com.mindhubgrupo2.hlmladorada.models.Promo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Set;


@SpringBootApplication
public class HlmladoradaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HlmladoradaApplication.class, args);
	


	@Bean
	public CommandLineRunner initData(ProductRepository productRepository, PromoRepository promoRepository, CartRepository cartRepository, CartDetalsRepository cartDetalsRepository) {
		return args -> {
			Promo promos = new Promo(Set.of(0,10,15,20));

			Product product1 = new Product("Monitor",15,150,250,20,"15' Pulgadas","Samsung","Tecnologia");

			product1.addPromo(promos);

			productRepository.save(product1);

			System.out.println(product1);

			int quantity = 2;
			double amount = product1.getFinalPrice() * quantity;
			CartDetails cart1 = new CartDetails(quantity,amount);

			cart1.addProducts(product1);

			Cart cartFinal = new Cart();

			cartFinal.addCartDetails(cart1);

			cartRepository.save(cartFinal);

			cartDetalsRepository.save(cart1);

			System.out.println(cart1);
			System.out.println("*******");
			System.out.println(cartFinal);


		};
	}

}
