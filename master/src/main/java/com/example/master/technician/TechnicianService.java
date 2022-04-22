package com.example.master.technician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TechnicianService {
    private final TechnicianRepository technicianRepository;

    @Autowired
    public TechnicianService(@Qualifier("postgres") TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public int createTechnician(Technician technician){
        return technicianRepository.insertTechnician(technician);
    }

    public List<Technician> getAll() {
        return technicianRepository.selectAllTechnician();
    }

    public List<Technician> getAvailable() {
        return technicianRepository.selectAvailableTechnician();
    }

    public int updateQueue(Technician technician){
        return technicianRepository.updateQueue(technician);
    }
}
