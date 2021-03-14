package com.ringcentral.testtask.validators;

import com.ringcentral.testtask.exceptions.CustomException;
import org.jvnet.hk2.annotations.Service;

@Service
public class ValidatorImpl implements Validator {

    @Override
    public void checkForIncorrectInputValues(String minEngineDisplacement, String minEngineHorsepower, String minMaxSpeed, String year) {
        try {
            if (minEngineDisplacement != null) {
                Integer.parseInt(minEngineDisplacement);
            }
            if (minEngineHorsepower != null) {
                Integer.parseInt(minEngineHorsepower);
            }
            if (minMaxSpeed != null) {
                Integer.parseInt(minMaxSpeed);
            }
            if (year != null) {
                Integer.parseInt(year);
            }
        } catch (Exception e) {
            throw new CustomException();
        }
    }

    @Override
    public void checkThatBothParamsAreNotPassedOrBothIsNotExist(String brand, String model) {
        if (((brand != null && !brand.isEmpty()) && (model != null && !model.isEmpty())) ||
                ((brand == null || brand.isEmpty()) && (model == null || model.isEmpty()))) {
            throw new CustomException();
        }
    }
}
