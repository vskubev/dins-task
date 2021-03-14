package com.ringcentral.testtask.parsers;

import com.ringcentral.testtask.external.CarInfo;
import com.ringcentral.testtask.services.external.CarsAPIService;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CarsInfoParserImpl implements CarsInfoParser {

    private final Map<Integer, CarInfo> carsInfoMap;
    private final CarsAPIService carsAPIService;
    private final CarsParser carsParser;
//    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Inject
    public CarsInfoParserImpl(CarsAPIService carsAPIService,
                              CarsParser carsParser) {
        this.carsParser = carsParser;
        this.carsAPIService = carsAPIService;
        carsInfoMap = new ConcurrentHashMap<>();
        List<Integer> carIdList = carsParser.getAllCarsId();
        carIdList.stream().parallel().forEach(carId -> carsInfoMap.putIfAbsent(carId, carsAPIService.getCarById(carId)));
    }

    @Override
    public Map<Integer, CarInfo> getCarsInfoMap() {
        return carsInfoMap;
    }

//    List<CarInfo> getCarInfos(List<Integer> carIdList) {
//        CountDownLatch countDownLatch = new CountDownLatch(carIdList.size());
//        List<CarInfo> carInfoList = new CopyOnWriteArrayList<>();
//
//        for (int i = 0; i < carIdList.size(); ++i) {
//            final int finalI = i;
//            executorService.submit(() -> {
//                carInfoList.add(carsAPIService.getCarById(carIdList.get(finalI)));
//                countDownLatch.countDown();
//            });
//        }
//        try {
//            countDownLatch.await();
//        } catch (final InterruptedException e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//
//        return carInfoList;
//    }


}
