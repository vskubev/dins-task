package com.ringcentral.testtask.services;

import com.ringcentral.testtask.exceptions.CustomException;
import com.ringcentral.testtask.external.Car;
import com.ringcentral.testtask.repositories.CarsRepository;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    private final CarsRepository carsRepository;

    @Inject
    public CarServiceImpl(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public void checkModelIsExist(String model) {
        if (carsRepository.getCars().values().stream().map(Car::getModel).noneMatch(input -> input.equalsIgnoreCase(model))) {
            throw new CustomException();
        }
    }

    @Override
    public void checkBrandIsExist(String brand) {
        if (carsRepository.getCars().values().stream().map(Car::getBrand).noneMatch(input -> input.equalsIgnoreCase(brand))) {
            throw new CustomException();
        }
    }

    @Override
    public Map<Integer, Car> getCars() {
        return carsRepository.getCars();
    }
}
