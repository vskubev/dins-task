package com.ringcentral.testtask.resources;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CarResource {
    private Integer id;
    private String segment;
    private String brand;
    private String model;
    private String generation;
    private String modification;
}

