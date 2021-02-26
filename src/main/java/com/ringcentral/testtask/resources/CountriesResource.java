package com.ringcentral.testtask.resources;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class CountriesResource {
    private List<CountryResource> countryResources = new ArrayList<>();
}
