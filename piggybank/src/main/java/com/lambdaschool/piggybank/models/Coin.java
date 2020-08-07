package com.lambdaschool.piggybank.models;

import javax.persistence.*;

@Entity
@Table(name = "coins")
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long coinid; //primary key ^ above nonsense lets spring know that and generates id's
    private String name; //stored name
    private String nameplural; //stored name in plural
    private double value; //face value of coin
    private long quantity; //number of coins

    // create constructor so we can add more coins if necessary
    public Coin(
        String name,
        String nameplural,
        double value,
        long quantity) {

        this.name = name;
        this.nameplural = nameplural;
        this.value = value;
        this.quantity = quantity;
    }

    //create default constructor so Spring Data JPA is happy
    public Coin() {
    }

    //Getters and Setters

    //coinid
    public long getCoinid() {
        return coinid;
    }

    //name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //nameplural
    public String getNameplural() {
        return nameplural;
    }

    public void setNameplural(String nameplural) {
        this.nameplural = nameplural;
    }

    //value
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    //quantity
    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    //toString method so when we print our code it gives us useful info
    @Override
    public String toString() {
        return "Coin{" +
            "coinid=" + coinid +
            ", name='" + name + '\'' +
            ", nameplural='" + nameplural + '\'' +
            ", value=" + value +
            ", quantity=" + quantity +
            '}';
    }
}
