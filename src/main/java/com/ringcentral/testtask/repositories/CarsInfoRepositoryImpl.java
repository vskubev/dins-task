package com.ringcentral.testtask.repositories;

import com.ringcentral.testtask.external.CarInfo;
import com.ringcentral.testtask.parsers.CarsInfoParser;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.util.Map;

@Service
public class CarsInfoRepositoryImpl implements CarsInfoRepository {

    private final Map<Integer, CarInfo> carsInfo;

    @Inject
    public CarsInfoRepositoryImpl(CarsInfoParser carsInfoParser) {
        this.carsInfo = carsInfoParser.getCarsInfoMap();
    }

    @Override
    public Map<Integer, CarInfo> getCarsInfo() {
        return carsInfo;
    }
}
