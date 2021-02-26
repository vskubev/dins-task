package com.ringcentral.testtask.resources;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class CarsResource {
    private List<CarResource> carResources = new ArrayList<>();
}
