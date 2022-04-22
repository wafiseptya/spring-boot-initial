package com.example.master.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("postgrescustomer")
public class CustomerRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Customer> selectAllCustomer() {
        String sql = "" +
                "SELECT " +
                " id, " +
                " name, " +
                " email, " +
                " address, " +
                " phone_number, " +
                " phone_brand, " +
                " damage, " +
                " price, " +
                " status " +
                "FROM customer";

        return jdbcTemplate.query(sql, mapCustomer());
    }

    int insertCustomer(Customer customer) {
//        UUID uuid = UUID.randomUUID();
        String sql = "" +
                "INSERT INTO customer (name, email, address, phone_number, phone_brand, damage, price, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhone_number(),
                customer.getPhone_brand(),
                customer.getDamage(),
                customer.getPrice(),
                customer.getStatus()
        );
    }

    private RowMapper<Customer> mapCustomer() {
        return (resultSet, i) -> {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            String phone_number = resultSet.getString("phone_number");
            String phone_brand = resultSet.getString("phone_brand");
            String damage = resultSet.getString("damage");
            Integer price = resultSet.getInt("price");
            String status = resultSet.getString("status");

            return new Customer(
                    id,
                    name,
                    email,
                    address,
                    phone_number,
                    phone_brand,
                    damage,
                    price,
                    status
            );
        };
    }

    int updateQueue(Customer customer) {
        String sql = "" +
                "UPDATE customer " +
                "SET status = ? " +
                "WHERE status = 'on progress' and email = ?";
        return jdbcTemplate.update(sql, customer.getStatus(), customer.getEmail());
    }
}
