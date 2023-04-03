package com.capstone.collector.capstonedatacollector;

import com.capstone.collector.capstonedatacollector.clients.AviationStackClientCaller;
import com.capstone.collector.capstonedatacollector.common.bindings.FlightInfo;
import com.capstone.collector.capstonedatacollector.common.bindings.Greeting;
import com.capstone.collector.capstonedatacollector.common.bindings.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.util.HtmlUtils;

import java.util.Random;
import java.util.stream.Collectors;

@org.springframework.stereotype.Controller
public class Controller {
    private ServiceHandler serviceHandler;

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    public Controller(ServiceHandler serviceHandler) {this.serviceHandler = serviceHandler;}

    private int count = 0;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting flighData(HelloMessage message) {
        //return serviceHandler.handle(message).stream().map(FlightInfo::toString).collect(Collectors.joining("\n\n"));
        //return new Greeting(serviceHandler.handle(message.getName()).stream().map(FlightInfo::toString).collect(Collectors.joining("\n\n")));
        return new Greeting("IT_WORKED: " + message.getName());
    }

    @Scheduled(initialDelay = 10000, fixedRate = 15000)
    public void keepSending() {
        Random random = new Random();

        LOGGER.info("Running scheduled task: {}", count);
        messagingTemplate.convertAndSend("/topic/greetings", new Greeting(String.format("%.2f,%.2f,%.2f", random.nextInt(360) + random.nextDouble() - 180, random.nextInt(180) + random.nextDouble() - 90, random.nextInt(5000) + random.nextDouble() + 10000)));
        count++;
    }
}
