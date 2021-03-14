package com.ringcentral.testtask.services;

import com.ringcentral.testtask.external.CarInfo;
import com.ringcentral.testtask.mapper.Mapper;
import com.ringcentral.testtask.repositories.CarsInfoRepository;
import com.ringcentral.testtask.resources.CarInfoResource;
import com.ringcentral.testtask.resources.CarResource;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarInfoServiceImpl implements CarInfoService {

    private final CarsInfoRepository carsInfoRepository;
    private final Mapper mapper;
    private final CarService carService;

    @Inject
    public CarInfoServiceImpl(CarsInfoRepository carsInfoRepository,
                              Mapper mapper,
                              CarService carService) {
        this.carsInfoRepository = carsInfoRepository;
        this.mapper = mapper;
        this.carService = carService;
    }

    @Override
    public List<CarResource> findCarResourcesByParams(String brand, String segment, String minEngineDisplacement, String minEngineHorsepower, String minMaxSpeed, String search, String year, String bodyStyle) {
        return carService.getCars().values().stream()
                .map(mapper::from)
                .filter(brand == null ? car -> true : car -> car.getBrand().equalsIgnoreCase(brand))
                .filter(segment == null ? car -> true : car -> car.getSegment().equalsIgnoreCase(segment))
                .filter(minEngineDisplacement == null ? car -> true : car -> findEngineDisplacementByCar(car) >= Integer.parseInt(minEngineDisplacement))
                .filter(minEngineHorsepower == null ? car -> true : car -> findEngineHorsepowerByCar(car) >= Integer.parseInt(minEngineHorsepower))
                .filter(minMaxSpeed == null ? car -> true : car -> findMaxSpeedByCar(car) >= Integer.parseInt(minMaxSpeed))
                .filter(search == null ? car -> true : car -> isSearchByTextSuccess(car, search))
                .filter(year == null ? car -> true : car -> isYearInRangeOfCarRealized(car, Integer.parseInt(year)))
                .filter(bodyStyle == null ? car -> true : car -> isCarHaveBodyStyle(car, bodyStyle))
                .collect(Collectors.toList());
    }

    @Override
    public int findEngineDisplacementByCar(CarResource carResource) {
        return findCarInfoByCarResource(carResource).stream()
                .map(CarInfo::getEngineDisplacement)
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Engine displacement by carId %s not found", carResource.getId())));
    }

    @Override
    public int findEngineHorsepowerByCar(CarResource carResource) {
        return findCarInfoByCarResource(carResource).stream()
                .map(CarInfo::getHp)
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Engine horsepower by carId %s not found", carResource.getId())));
    }

    @Override
    public int findMaxSpeedByCar(CarResource carResource) {
        return findCarInfoByCarResource(carResource).stream()
                .map(CarInfo::getMaxSpeed)
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Max power by carId %s not found", carResource.getId())));
    }

    @Override
    public boolean isYearInRangeOfCarRealized(CarResource carResource, Integer year) {
        final String yearRange = findCarInfoByCarResource(carResource).stream()
                .map(CarInfo::getYearRange)
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Year range by carId %s not found", carResource.getId())));
        List<String> range = Arrays.asList(yearRange.split("-"));
        if ("present".equalsIgnoreCase(range.get(1))) {
            range.set(1, String.valueOf(LocalDate.now().getYear()));
        }
        return year >= Integer.parseInt(range.get(0)) && year <= Integer.parseInt(range.get(1));
    }

    @Override
    public boolean isSearchByTextSuccess(CarResource carResource, String searchText) {
        final String foundBodyStyle = findCarInfoByCarResource(carResource).stream()
                .map(carInfo -> carInfo.getModel().concat(carInfo.getGeneration()).concat(carInfo.getModification()))
                .findFirst().orElse("");
        return foundBodyStyle.toLowerCase().contains(searchText.toLowerCase());
    }

    @Override
    public boolean isCarHaveBodyStyle(CarResource carResource, String bodyStyle) {
        final String foundBodyStyle = findCarInfoByCarResource(carResource).stream()
                .map(CarInfo::getBodyStyle)
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Max power by carId %s not found", carResource.getId())))
                .toLowerCase();
        return foundBodyStyle.contains(bodyStyle.toLowerCase());
    }

    private List<CarInfo> findCarInfoByCarResource(CarResource carResource) {
        return carsInfoRepository.getCarsInfo().values().stream()
                .filter(carInfo -> carInfo.getId().equals(carResource.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarInfoResource> getCarInfoListByCarResourceList(List<CarResource> carResourceList) {
        final List<CarInfoResource> carInfoResourceList = new ArrayList<>();
        carResourceList.forEach(carResource -> {
            CarInfoResource carInfoResource = mapper.from(carsInfoRepository.getCarsInfo().values().stream()
                    .filter(carInfo -> carInfo.getId().equals(carResource.getId()))
                    .findFirst().orElseThrow(() -> new RuntimeException(String.format("CarInfo by carId %s not found", carResource.getId()))));
            carInfoResourceList.add(carInfoResource);
        });
        return carInfoResourceList;
    }

    @Override
    public List<Integer> getMaxSpeedListByBrand(String brand) {
        return carsInfoRepository.getCarsInfo().values().stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .map(CarInfo::getMaxSpeed)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getMaxSpeedListByModel(String model) {
        return carsInfoRepository.getCarsInfo().values().stream()
                .filter(car -> car.getModel().equalsIgnoreCase(model))
                .map(CarInfo::getMaxSpeed)
                .collect(Collectors.toList());
    }

    @Override
    public int getAverageSpeed(List<Integer> maxSpeedList) {
        return maxSpeedList.stream().mapToInt(Integer::intValue).sum() / maxSpeedList.size();
    }

    @Override
    public List<String> getFuelTypes() {
        return carsInfoRepository.getCarsInfo().values().stream()
                .map(CarInfo::getFuelType)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBodyStyles() {
        return carsInfoRepository.getCarsInfo().values().stream()
                .map(CarInfo::getBodyStyle)
                .flatMap(input -> Arrays.stream(input.split( "," )))
                .map(String::trim)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getEngineTypes() {
        return carsInfoRepository.getCarsInfo().values().stream()
                .map(CarInfo::getEngineType)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getWheelDrives() {
        return carsInfoRepository.getCarsInfo().values().stream()
                .map(CarInfo::getWheelDriveType)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getGearboxes() {
        return carsInfoRepository.getCarsInfo().values().stream()
                .map(CarInfo::getGearboxType)
                .distinct()
                .collect(Collectors.toList());
    }

}
