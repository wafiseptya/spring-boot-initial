package com.example.master.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {
    private final Integer id;
    private final String name;
    private final String email;
    private final String address;
    private final String phone_number;
    private final String phone_brand;
    private final String damage;

    private final Integer price;
    private final String status;


    public Customer(@JsonProperty("id") Integer id,
                    @JsonProperty("name") String name,
                    @JsonProperty("email") String email,
                    @JsonProperty("address") String address,
                    @JsonProperty("phone_number") String phone_number,
                    @JsonProperty("phone_brand") String phone_brand,
                    @JsonProperty("damage") String damage,
                    @JsonProperty("price") Integer price,
                    @JsonProperty("status") String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone_number = phone_number;
        this.phone_brand = phone_brand;
        this.damage = damage;
        this.price = price;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPhone_brand() {
        return phone_brand;
    }

    public String getDamage() {
        return damage;
    }
    public Integer getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }
}
