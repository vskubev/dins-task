package com.ringcentral.testtask.parsers;

import com.ringcentral.testtask.external.Car;
import com.ringcentral.testtask.services.external.CarsAPIService;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CarsParserImpl implements CarsParser {

    private final Map<Integer, Car> carMap;

    @Inject
    public CarsParserImpl(CarsAPIService carsAPIService) {
        carMap = new ConcurrentHashMap<>();
        List<Car> cars = carsAPIService.getCars();
        cars.forEach(car -> carMap.putIfAbsent(car.getId(), car));
    }

    @Override
    public Map<Integer, Car> getCarMap() {
        return carMap;
    }

    @Override
    public List<Integer> getAllCarsId() {
        return new ArrayList<>(carMap.keySet());
    }
}
