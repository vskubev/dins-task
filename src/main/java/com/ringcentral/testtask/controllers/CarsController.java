package com.ringcentral.testtask.controllers;

import com.ringcentral.testtask.external.Car;
import com.ringcentral.testtask.external.CarInfo;
import com.ringcentral.testtask.external.Country;
import com.ringcentral.testtask.resources.CarInfoResource;
import com.ringcentral.testtask.resources.CarResource;
import com.ringcentral.testtask.resources.CarsResource;
import com.ringcentral.testtask.resources.CountriesResource;
import com.ringcentral.testtask.resources.CountryResource;
import com.ringcentral.testtask.services.external.CarsAPIService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("api")
public class CarsController {

    // echo of source api
    @Path("echo/cars")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public CarsResource getEchoCars() {
        CarsAPIService carsAPI = new CarsAPIService();
        List<Car> carsList = carsAPI.getCars();
        CarsResource result = new CarsResource();
        carsList.forEach(car -> result.getCarResources().add(from(car)));

        return result;
    }

    // echo of source api
    @Path("echo/cars/{carId}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public CarInfoResource getEchoCar(@PathParam("carId") int carId) {
        CarsAPIService carsAPI = new CarsAPIService();
        CarInfo carInfo = carsAPI.getCarById(carId);

        return from(carInfo);
    }

    // echo of source api with small extra example
    @Path("echo/countries")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public CountriesResource getEchoCountries(@QueryParam("title") String title) {
        CarsAPIService carsAPI = new CarsAPIService();
        List<Country> countriesList = carsAPI.getCountries();
        CountriesResource result = new CountriesResource();

        result.getCountryResources().addAll(Optional.ofNullable(countriesList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(title == null ? country -> true : country -> title.equals(country.getTitle()))
                .map(CarsController::from)
                .collect(Collectors.toList()));

        return result;
    }

    @Path("cars")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String getCars() {
        return "TODO";
    }

    @Path("fuel-types")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String getFuelTypes() {
        return "TODO";
    }

    @Path("body-styles")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String getBodyStyles() {
        return "TODO";
    }

    @Path("engine-types")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String getEngineTypes() {
        return "TODO";
    }

    @Path("wheel-drives")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String getWheelDrives() {
        return "TODO";
    }

    @Path("gearboxes")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String getGearboxes() {
        return "TODO";
    }

    @Path("max-speed")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public String getMaxSpeed() {
        return "TODO";
    }

    private static CarResource from(Car car) {
        CarResource carResource = new CarResource();
        carResource.setId(car.getId());
        carResource.setSegment(car.getSegment());
        carResource.setBrand(car.getBrand());
        carResource.setModel(car.getModel());
        carResource.setGeneration(car.getGeneration());
        carResource.setModification(car.getModification());

        return carResource;
    }

    private static CarInfoResource from(CarInfo carInfo) {
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

    private static CountryResource from(Country country) {
        CountryResource countryResource = new CountryResource();
        countryResource.setId(country.getId());
        countryResource.setTitle(country.getTitle());
        countryResource.setBrands(country.getBrands());

        return countryResource;
    }

}
