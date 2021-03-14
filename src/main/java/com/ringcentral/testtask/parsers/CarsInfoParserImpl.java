package com.ringcentral.testtask.parsers;

import com.ringcentral.testtask.external.CarInfo;
import com.ringcentral.testtask.services.external.CarsAPIService;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CarsInfoParserImpl implements CarsInfoParser {

    private final Map<Integer, CarInfo> carsInfoMap;

    @Inject
    public CarsInfoParserImpl(CarsAPIService carsAPIService,
                              CarsParser carsParser) {
        carsInfoMap = new ConcurrentHashMap<>();
        List<Integer> carIdList = carsParser.getAllCarsId();
        carIdList.stream().parallel().forEach(carId -> carsInfoMap.putIfAbsent(carId, carsAPIService.getCarById(carId)));
    }

    @Override
    public Map<Integer, CarInfo> getCarsInfoMap() {
        return carsInfoMap;
    }

}
