package com.ringcentral.testtask.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarInfo {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("segment")
    private String segment;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("model")
    private String model;

    @JsonProperty("generation")
    private String generation;

    @JsonProperty("modification")
    private String modification;

    @JsonProperty("year_range")
    private String yearRange;

    @JsonProperty("engine_type")
    private String fuelType;

    @JsonProperty("engine_cylinders")
    private String engineType;

    @JsonProperty("engine_displacement")
    private Integer engineDisplacement;

    @JsonProperty("engine_horsepower")
    private Integer hp;

    @JsonProperty("gearbox")
    private String gearboxType;

    @JsonProperty("wheel_drive")
    private String wheelDriveType;

    @JsonProperty("body_length")
    private Integer bodyLength;

    @JsonProperty("body_width")
    private Integer bodyWidth;

    @JsonProperty("body_height")
    private Integer bodyHeight;

    @JsonProperty("body_style")
    private String bodyStyle;

    @JsonProperty("acceleration")
    private Double acceleration;

    @JsonProperty("max_speed")
    private Integer maxSpeed;
}
