package com.ringcentral.testtask.parsers;

import com.ringcentral.testtask.external.Car;

import java.util.List;
import java.util.Map;

public interface CarsParser {
    Map<Integer, Car> getCarMap();
    List<Integer> getAllCarsId();
}
