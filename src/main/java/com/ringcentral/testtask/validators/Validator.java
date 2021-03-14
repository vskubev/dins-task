package com.ringcentral.testtask.validators;

public interface Validator {
    void checkForIncorrectInputValues(String minEngineDisplacement, String minEngineHorsepower, String minMaxSpeed, String year);
    void checkThatBothParamsAreNotPassedOrBothIsNotExist(String brand, String model);
}
