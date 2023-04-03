package com.capstone.collector.capstonedatacollector;

import com.capstone.collector.capstonedatacollector.clients.AviationStackClientCaller;
import com.capstone.collector.capstonedatacollector.common.bindings.FlightInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ServiceHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceHandler.class);

    @Autowired
    private AviationStackClientCaller aviationStackClientCaller;

    private List<String> flightIcaoList;

    public ServiceHandler() {
        flightIcaoList = new ArrayList<>();
    }

    public List<FlightInfo> handle(String flightIcaos) {
        List<FlightInfo> flightInfos = new ArrayList<>();
        this.flightIcaoList = Arrays.asList(flightIcaos.split(","));

        flightInfos.add(aviationStackClientCaller.getFlightByIcao(flightIcaoList.get(0)));

        return flightInfos;
    }

    /*@Scheduled(initialDelay = 5000, fixedRate = 300000)
    public void getFlightDataScheduled() {
        if (!flightIcaoList.isEmpty()) {
            System.out.println(aviationStackClientCaller.getFlightByIcao(flightIcaoList.get(0)).toString());
        }
    }*/
}
