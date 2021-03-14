package com.ringcentral.testtask.mapper;

import com.ringcentral.testtask.external.Car;
import com.ringcentral.testtask.external.CarInfo;
import com.ringcentral.testtask.external.Country;
import com.ringcentral.testtask.resources.CarInfoResource;
import com.ringcentral.testtask.resources.CarResource;
import com.ringcentral.testtask.resources.CountryResource;

public interface Mapper {
    CarResource from(Car car);
    CarInfoResource from(CarInfo carInfo);
    CountryResource from(Country country);
}
