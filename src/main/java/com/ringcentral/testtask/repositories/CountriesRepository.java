package com.ringcentral.testtask.repositories;

import com.ringcentral.testtask.external.Country;

import java.util.Map;

public interface CountriesRepository {
    Map<Integer, Country> getCountries();
}
