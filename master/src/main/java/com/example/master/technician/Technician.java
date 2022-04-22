package com.example.master.technician;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Technician {
    private final Integer id;
    private final String name;
    private final String specialist;
    private final String platform;
    private final Integer queue;


    public Technician(@JsonProperty("id") Integer id,
                      @JsonProperty("name") String name,
                      @JsonProperty("specialist") String specialist,
                      @JsonProperty("platform") String platform,
                      @JsonProperty("queue") Integer queue) {
        this.id = id;
        this.name = name;
        this.specialist = specialist;
        this.platform = platform;
        this.queue = queue;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialist() {
        return specialist;
    }

    public String getPlatform() {
        return platform;
    }

    public Integer getQueue() {
        return queue;
    }
}
