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
                " specialist, " +
                " platform, " +
                " queue " +
                "FROM customer";

        return jdbcTemplate.query(sql, mapTechnician());
    }

    List<Customer> selectAvailableCustomer() {
        String sql = "SELECT " +
                " id, name, specialist, platform, queue" +
                "FROM customer" +
                "WHERE queue < 3";

        return jdbcTemplate.query(sql, mapTechnician());
    }

    int insertTechnician(Customer customer) {
//        UUID uuid = UUID.randomUUID();
        String sql = "" +
                "INSERT INTO customer (name, specialist, platform, queue) " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getSpecialist(),
                customer.getPlatform(),
                0
        );
    }

    private RowMapper<Customer> mapTechnician() {
        return (resultSet, i) -> {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String specialist = resultSet.getString("specialist");
            String platform = resultSet.getString("platform");
            Integer queue = resultSet.getInt("queue");

            return new Customer(
                    id,
                    name,
                    specialist,
                    platform,
                    queue
            );
        };
    }

    int updateQueue(UUID uuid, Integer queue) {
        String sql = "" +
                "UPDATE technician " +
                "SET queue = ? " +
                "WHERE uuid = ?";
        return jdbcTemplate.update(sql, queue, uuid);
    }
}
