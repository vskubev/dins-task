package com.ringcentral.testtask.services;

import com.ringcentral.testtask.resources.CarInfoResource;
import com.ringcentral.testtask.resources.CarResource;

import java.util.List;

public interface CarInfoService {
    List<CarResource> findCarResourcesByParams(String brand, String segment, String minEngineDisplacement, String minEngineHorsepower, String minMaxSpeed, String search, String year, String bodyStyle);
    int findEngineDisplacementByCar(CarResource carResource);
    int findEngineHorsepowerByCar(CarResource carResource);
    int findMaxSpeedByCar(CarResource carResource);
    boolean isYearInRangeOfCarRealized(CarResource carResource, Integer year);
    boolean isSearchByTextSuccess(CarResource carResource, String searchText);
    boolean isCarHaveBodyStyle(CarResource carResource, String bodyStyle);
    List<CarInfoResource> getCarInfoListByCarResourceList(List<CarResource> carResourceList);
    List<Integer> getMaxSpeedListByBrand(String brand);
    List<Integer> getMaxSpeedListByModel(String model);
    int getAverageSpeed(List<Integer> maxSpeedList);
    List<String> getFuelTypes();
    List<String> getBodyStyles();
    List<String> getEngineTypes();
    List<String> getWheelDrives();
    List<String> getGearboxes();
}
