package com.example.core.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    private final String name;
    private final String email;
    private final String address;
    private final String phone_number;
    private final String phone_brand;
    private final String damage;

    public Order(@JsonProperty("name") String name,
                 @JsonProperty("email") String email,
                 @JsonProperty("address") String address,
                 @JsonProperty("phone_number") String phone_number,
                 @JsonProperty("phone_brand") String phone_brand,
                 @JsonProperty("damage") String damage) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone_number = phone_number;
        this.phone_brand = phone_brand;
        this.damage = damage;
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
}
