package com.ringcentral.testtask.parsers;

import com.ringcentral.testtask.external.Country;
import com.ringcentral.testtask.services.external.CarsAPIService;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CountriesParserImpl implements CountriesParser {

    private final Map<Integer, Country> countryMap;

    @Inject
    public CountriesParserImpl(CarsAPIService carsAPIService) {
        countryMap = new ConcurrentHashMap<>();
        List<Country> countries = carsAPIService.getCountries();
        countries.forEach(country -> countryMap.putIfAbsent(country.getId(), country));
    }

    @Override
    public Map<Integer, Country> getCountries() {
        return countryMap;
    }

}
