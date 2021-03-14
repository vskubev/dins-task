package com.ringcentral.testtask.services;

import com.ringcentral.testtask.mapper.Mapper;
import com.ringcentral.testtask.mapper.MapperImpl;
import com.ringcentral.testtask.parsers.*;
import com.ringcentral.testtask.repositories.*;
import com.ringcentral.testtask.services.external.CarsAPIService;
import com.ringcentral.testtask.validators.Validator;
import com.ringcentral.testtask.validators.ValidatorImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.ext.Provider;

@Provider
public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(CountryServiceImpl.class).to(CountryService.class);
        bind(CountriesRepositoryImpl.class).to(CountriesRepository.class);
        bind(CountriesParserImpl.class).to(CountriesParser.class);

        bind(CarServiceImpl.class).to(CarService.class);
        bind(CarsRepositoryImpl.class).to(CarsRepository.class);
        bind(CarsParserImpl.class).to(CarsParser.class);

        bind(CarInfoServiceImpl.class).to(CarInfoService.class);
        bind(CarsInfoRepositoryImpl.class).to(CarsInfoRepository.class);
        bind(CarsInfoParserImpl.class).to(CarsInfoParser.class);

        bind(MapperImpl.class).to(Mapper.class);
        bind(ValidatorImpl.class).to(Validator.class);
        bind(CarsAPIService.class).to(CarsAPIService.class);
    }
}