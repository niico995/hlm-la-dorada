package com.mindhubgrupo2.hlmladorada.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartID;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientStCart")
    private ClientStore clientStore;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientOnCart")
    private ClientOnline clientOnline;

    @ManyToMany(mappedBy = "carts", fetch = FetchType.EAGER)
    private Set<CartDetails> cartDetails = new HashSet<>();

    public void addCartDetails(CartDetails cartDetail){
        cartDetail.getCart().add(this);
        cartDetails.add(cartDetail);
    }

    @OneToMany(mappedBy = "cartHolder")
    private Set<Sales> sales = new HashSet<>();

    public void addSales(Sales sale){
        sale.setCart(this);
        sales.add(sale);
    }

    public Cart() {
    }




    public int getCartID() {
        return cartID;
    }

    public ClientStore getClientStore() {
        return clientStore;
    }

    public void setClientStore(ClientStore clientStore) {
        this.clientStore = clientStore;
    }

    public ClientOnline getClientOnline() {
        return clientOnline;
    }

    public void setClientOnline(ClientOnline clientOnline) {
        this.clientOnline = clientOnline;
    }

    public Set<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(Set<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                ", cartDetails=" + cartDetails +
                '}';
    }
}
