package com.example.master.technician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class TechnicianRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TechnicianRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Technician> selectAllTechnician() {
        String sql = "" +
                "SELECT " +
                " id, " +
                " name, " +
                " specialist, " +
                " platform, " +
                " queue " +
                "FROM technician";

        return jdbcTemplate.query(sql, mapTechnician());
    }

    List<Technician> selectAvailableTechnician() {
        String sql = "" +
                "SELECT " +
                " id, " +
                " name, " +
                " specialist, " +
                " platform, " +
                " queue " +
                "FROM technician "+
                "WHERE queue < 3 "+
                "LIMIT 1";
        System.out.println(sql);

        return jdbcTemplate.query(sql, mapTechnician());
    }

    int insertTechnician(Technician technician) {
//        UUID uuid = UUID.randomUUID();
        String sql = "" +
                "INSERT INTO technician (name, specialist, platform, queue) " +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                technician.getName(),
                technician.getSpecialist(),
                technician.getPlatform(),
                0
        );
    }

    private RowMapper<Technician> mapTechnician() {
        return (resultSet, i) -> {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String specialist = resultSet.getString("specialist");
            String platform = resultSet.getString("platform");
            Integer queue = resultSet.getInt("queue");

            return new Technician(
                    id,
                    name,
                    specialist,
                    platform,
                    queue
            );
        };
    }

    int updateQueue(Technician technician) {
        String sql = "" +
                "UPDATE technician " +
                "SET queue = queue + ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, technician.getQueue(), technician.getId());
    }
}
