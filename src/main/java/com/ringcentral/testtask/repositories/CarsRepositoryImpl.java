package com.ringcentral.testtask.repositories;

import com.ringcentral.testtask.external.Car;
import com.ringcentral.testtask.parsers.CarsParser;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.util.Map;

@Service
public class CarsRepositoryImpl implements CarsRepository {

    private final Map<Integer, Car> cars;

    @Inject
    public CarsRepositoryImpl(CarsParser carsParser) {
        this.cars = carsParser.getCarMap();
    }

    @Override
    public Map<Integer, Car> getCars() {
        return cars;
    }
}
