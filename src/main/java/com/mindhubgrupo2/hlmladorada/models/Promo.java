package com.mindhubgrupo2.hlmladorada.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promoID;

    @ElementCollection
    private Set<Integer> promos = new HashSet<>();

    //@ManyToOne(fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Product product;

    public Promo() {
    }

    public Promo(Set<Integer> promos) {
        this.promos = promos;
    }

    public Set<Integer> getPromos() {
        return promos;
    }

    public void setPromos(Set<Integer> promos) {
        this.promos = promos;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Promo{" +
                "id=" + promoID +
                ", promos=" + promos +

                '}';
    }
}
