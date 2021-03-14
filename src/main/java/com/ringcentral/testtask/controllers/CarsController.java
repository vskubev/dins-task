package com.ringcentral.testtask.controllers;

import com.ringcentral.testtask.exceptions.CustomException;
import com.ringcentral.testtask.external.Car;
import com.ringcentral.testtask.external.CarInfo;
import com.ringcentral.testtask.external.Country;
import com.ringcentral.testtask.mapper.Mapper;
import com.ringcentral.testtask.resources.CarInfoResource;
import com.ringcentral.testtask.resources.CarResource;
import com.ringcentral.testtask.resources.CarsResource;
import com.ringcentral.testtask.resources.CountriesResource;
import com.ringcentral.testtask.services.CarInfoService;
import com.ringcentral.testtask.services.CarService;
import com.ringcentral.testtask.services.external.CarsAPIService;
import com.ringcentral.testtask.validators.Validator;
import org.glassfish.hk2.api.Immediate;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("api")
@Immediate
public class CarsController {

    private final CarService carService;
    private final CarInfoService carInfoService;
    private final Mapper mapper;
    private final Validator validator;

    @Inject
    public CarsController(CarService carService,
                          CarInfoService carInfoService,
                          Mapper mapper,
                          Validator validator) {
        this.carService = carService;
        this.carInfoService = carInfoService;
        this.mapper = mapper;
        this.validator = validator;
    }

    // echo of source api
    @Path("echo/cars")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public CarsResource getEchoCars() {
        CarsAPIService carsAPI = new CarsAPIService();
        List<Car> carsList = carsAPI.getCars();
        CarsResource result = new CarsResource();
        carsList.forEach(car -> result.getCarResources().add(mapper.from(car)));

        return result;
    }

    // echo of source api
    @Path("echo/cars/{carId}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public CarInfoResource getEchoCar(@PathParam("carId") int carId) {
        CarsAPIService carsAPI = new CarsAPIService();
        CarInfo carInfo = carsAPI.getCarById(carId);

        return mapper.from(carInfo);
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
                .map(mapper::from)
                .collect(Collectors.toList()));

        return result;
    }

    @Path("cars")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getCars(@QueryParam("brand") String brand,
                            @QueryParam("segment") String segment,
                            @QueryParam("minEngineDisplacement") String minEngineDisplacement,
                            @QueryParam("minEngineHorsepower") String minEngineHorsepower,
                            @QueryParam("minMaxSpeed") String minMaxSpeed,
                            @QueryParam("search") String search,
                            @QueryParam("isFull") @DefaultValue("false") Boolean isFull,
                            @QueryParam("year") String year,
                            @QueryParam("bodyStyle") String bodyStyle) {
        try {
            validator.checkForIncorrectInputValues(minEngineDisplacement, minEngineHorsepower, minMaxSpeed, year);
            final List<CarResource> carResourceList = carInfoService.findCarResourcesByParams(brand, segment, minEngineDisplacement, minEngineHorsepower, minMaxSpeed, search, year, bodyStyle);
            if (carResourceList.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return isFull ? Response.status(Response.Status.OK).entity(carInfoService.getCarInfoListByCarResourceList(carResourceList)).build() :
                    Response.status(Response.Status.OK).entity(new CarsResource(carResourceList)).build();
        } catch (CustomException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("fuel-types")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<String> getFuelTypes() {
        return carInfoService.getFuelTypes();
    }

    @Path("body-styles")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<String> getBodyStyles() {
        return carInfoService.getBodyStyles();
    }

    @Path("engine-types")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<String> getEngineTypes() {
        return carInfoService.getEngineTypes();
    }

    @Path("wheel-drives")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<String> getWheelDrives() {
        return carInfoService.getWheelDrives();
    }

    @Path("gearboxes")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<String> getGearboxes() {
        return carInfoService.getGearboxes();
    }

    @Path("max-speed")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getMaxSpeed(@QueryParam("brand") String brand,
                                @QueryParam("model") String model) {
        final List<Integer> speedList = new ArrayList<>();
        try {
            validator.checkThatBothParamsAreNotPassedOrBothIsNotExist(brand, model);
            if (brand != null) {
                speedList.addAll(carInfoService.getMaxSpeedListByBrand(brand));
            } else {
                speedList.addAll(carInfoService.getMaxSpeedListByModel(model));
            }
            return speedList.isEmpty() ? Response.status(Response.Status.NOT_FOUND).build() :
                    Response.status(Response.Status.OK).entity(carInfoService.getAverageSpeed(speedList)).build();
        } catch (CustomException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
