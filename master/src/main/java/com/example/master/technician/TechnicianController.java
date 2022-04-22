package com.example.master.technician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("api/v1/technician")
@RestController
public class TechnicianController {
    private final TechnicianService technicianService;

    @Autowired
    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @PostMapping
    public void createTechnician(@RequestBody Technician technician){
        technicianService.createTechnician(technician);
    }

    @PostMapping(path = "/update")
    public void updateQueue(@RequestBody Technician technician){
        technicianService.updateQueue(technician);
    }

    @GetMapping(path = "{platform}")
    public List<Technician> getAvailableTechnician(@PathVariable("platform") String platform){
        System.out.println("New request");

        return technicianService.getAvailable(platform);
    }
    @GetMapping
    public List<Technician> getAvailableTechnician(){
        System.out.println("New request");

        return technicianService.getAll();
    }
}
