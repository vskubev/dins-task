package com.ringcentral.testtask.services;

import com.ringcentral.testtask.repositories.CountriesRepository;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

import static java.lang.String.format;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountriesRepository countriesRepository;

    @Inject
    public CountryServiceImpl(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public String findCountryByBrand(String brand) {
        return countriesRepository.getCountries().entrySet().stream()
                .filter(entry -> entry.getValue().getBrands().contains(brand))
                .findFirst().orElseThrow(() -> new RuntimeException(format("Country of brand %s not found", brand)))
                .getValue().getTitle();
    }
}
