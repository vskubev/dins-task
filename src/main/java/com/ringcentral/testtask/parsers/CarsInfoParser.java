package com.ringcentral.testtask.parsers;

import com.ringcentral.testtask.external.CarInfo;

import java.util.Map;

public interface CarsInfoParser {
    Map<Integer, CarInfo> getCarsInfoMap();
}
