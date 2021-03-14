package com.ringcentral.testtask.repositories;

import com.ringcentral.testtask.external.Country;
import com.ringcentral.testtask.parsers.CountriesParser;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.util.Map;

@Service
public class CountriesRepositoryImpl implements CountriesRepository {

    private final Map<Integer, Country> countries;

    @Inject
    public CountriesRepositoryImpl(CountriesParser countriesParser) {
        this.countries = countriesParser.getCountries();
    }

    @Override
    public Map<Integer, Country> getCountries() {
        return countries;
    }
}
