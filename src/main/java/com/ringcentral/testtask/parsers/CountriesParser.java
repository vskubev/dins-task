package com.ringcentral.testtask.parsers;

import com.ringcentral.testtask.external.Country;

import java.util.Map;

public interface CountriesParser {
    Map<Integer, Country> getCountries();
}
