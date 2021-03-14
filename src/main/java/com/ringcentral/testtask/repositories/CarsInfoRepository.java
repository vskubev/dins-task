package com.ringcentral.testtask.repositories;

import com.ringcentral.testtask.external.CarInfo;

import java.util.Map;

public interface CarsInfoRepository {
    Map<Integer, CarInfo> getCarsInfo();
}
