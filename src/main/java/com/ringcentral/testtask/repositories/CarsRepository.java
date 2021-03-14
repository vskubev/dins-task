package com.ringcentral.testtask.repositories;

import com.ringcentral.testtask.external.Car;

import java.util.Map;

public interface CarsRepository {
    Map<Integer, Car> getCars();
}
