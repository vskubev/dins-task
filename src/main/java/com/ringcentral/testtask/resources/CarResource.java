package com.ringcentral.testtask.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonIgnoreProperties
public class CarResource {
    private Integer id;
    private String segment;
    private String brand;
    private String model;
    private String country;
    private String generation;
    private String modification;
}

