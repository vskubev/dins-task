package com.ringcentral.testtask.resources;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class CountryResource {
    private Integer id;
    private String title;
    private List<String> brands;
}
