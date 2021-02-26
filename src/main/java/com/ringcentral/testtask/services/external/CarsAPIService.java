package com.ringcentral.testtask.services.external;

import com.ringcentral.testtask.external.Car;
import com.ringcentral.testtask.external.CarInfo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.ringcentral.testtask.external.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

public class CarsAPIService {
    private static final Logger LOGGER = LogManager.getLogger(CarsAPIService.class);

    private static final String CAR_LIST_URI = "http://localhost:8084/api/v2/cars";
    private static final String COUNTRY_LIST_URI = "http://localhost:8084/api/v2/countries";


    private Client client = ClientBuilder.newClient();

    public List<Car> getCars() {
        try {
            Response response = client
                    .target(CAR_LIST_URI)
                    .request()
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .get(Response.class);

            if (response.getStatusInfo() != Response.Status.OK) {
                return Collections.emptyList();
            }

            return response.readEntity(new GenericType<List<Car>>() {});
        } catch (Exception e) {
            LOGGER.error("Error when trying to load all cars", e.getMessage());
            return Collections.emptyList();
        }
    }

    public CarInfo getCarById(int id) {
        try {
            Response response = client
                    .target(CAR_LIST_URI)
                    .path(String.valueOf(id))
                    .request()
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .get(Response.class);

            if (response.getStatusInfo() != Response.Status.OK) {
                return null;
            }
            return response.readEntity(CarInfo.class);
        } catch (Exception e) {
            LOGGER.error("Error when trying to load car with id {}", id, e.getMessage());
            return null;
        }
    }

    public List<Country> getCountries() {
        Response response =  client
                .target(COUNTRY_LIST_URI)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);

        if (response.getStatusInfo() != Response.Status.OK) {
            return Collections.emptyList();
        }

        return response.readEntity(new GenericType<List<Country>>() {});
    }

}
