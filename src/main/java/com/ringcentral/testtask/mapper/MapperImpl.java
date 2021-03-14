package com.ringcentral.testtask.mapper;

import com.ringcentral.testtask.external.Car;
import com.ringcentral.testtask.external.CarInfo;
import com.ringcentral.testtask.external.Country;
import com.ringcentral.testtask.resources.CarInfoResource;
import com.ringcentral.testtask.resources.CarResource;
import com.ringcentral.testtask.resources.CountryResource;
import com.ringcentral.testtask.services.CountryService;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class MapperImpl implements Mapper {

    private final CountryService countryService;

    @Inject
    public MapperImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public CarResource from(Car car) {
        CarResource carResource = new CarResource();
        carResource.setId(car.getId());
        carResource.setSegment(car.getSegment());
        carResource.setBrand(car.getBrand());
        carResource.setModel(car.getModel());
        carResource.setCountry(countryService.findCountryByBrand(car.getBrand()));
        carResource.setGeneration(car.getGeneration());
        carResource.setModification(car.getModification());

        return carResource;
    }

    @Override
    public CarInfoResource from(CarInfo carInfo) {
        CarInfoResource carInfoResource = new CarInfoResource();
        carInfoResource.setId(carInfo.getId());
        carInfoResource.setSegment(carInfo.getSegment());
        carInfoResource.setBrand(carInfo.getBrand());
        carInfoResource.setModel(carInfo.getModel());
        carInfoResource.setGeneration(carInfo.getGeneration());
        carInfoResource.setModification(carInfo.getModification());
        carInfoResource.setYearRange(carInfo.getYearRange());
        carInfoResource.setFuelType(carInfo.getFuelType());
        carInfoResource.setEngineType(carInfo.getEngineType());
        carInfoResource.setEngineDisplacement(carInfo.getEngineDisplacement());
        carInfoResource.setHp(carInfo.getHp());
        carInfoResource.setGearboxType(carInfo.getGearboxType());
        carInfoResource.setWheelDriveType(carInfo.getWheelDriveType());
        carInfoResource.setBodyLength(carInfo.getBodyLength());
        carInfoResource.setBodyWidth(carInfo.getBodyWidth());
        carInfoResource.setBodyHeight(carInfo.getBodyHeight());
        carInfoResource.setBodyStyle(carInfo.getBodyStyle());
        carInfoResource.setAcceleration(carInfo.getAcceleration());
        carInfoResource.setMaxSpeed(carInfo.getMaxSpeed());

        return carInfoResource;
    }

    @Override
    public CountryResource from(Country country) {
        CountryResource countryResource = new CountryResource();
        countryResource.setId(country.getId());
        countryResource.setTitle(country.getTitle());
        countryResource.setBrands(country.getBrands());

        return countryResource;
    }
}
