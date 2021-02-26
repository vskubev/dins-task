package com.ringcentral.testtask.resources;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
//@EqualsAndHashCode(callSuper = true)
//public class CarInfoResource extends CarResource {
public class CarInfoResource {
    private Integer id;
    private String segment;
    private String brand;
    private String model;
    private String generation;
    private String modification;
    private String yearRange;
    private String fuelType;
    private String engineType;
    private Integer engineDisplacement;
    private Integer hp;
    private String gearboxType;
    private String wheelDriveType;
    private Integer bodyLength;
    private Integer bodyWidth;
    private Integer bodyHeight;
    private String bodyStyle;
    private Double acceleration;
    private Integer maxSpeed;
}
