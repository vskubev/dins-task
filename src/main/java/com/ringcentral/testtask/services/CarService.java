package com.ringcentral.testtask.services;

import com.ringcentral.testtask.external.Car;

import java.util.Map;

public interface CarService {
    void checkModelIsExist(String model);
    void checkBrandIsExist(String brand);
    Map<Integer, Car> getCars();
}
