package com.driver.coordinate.sender.controller;

import com.driver.coordinate.sender.dto.DriverInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sender")
public class SenderController {

    private final RabbitTemplate rabbitTemplate;

    @Value("${events.ex}")
    private String eventsExName;

    public SenderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping(path = "/send")
    public ResponseEntity<String> sendMessageTest(@RequestBody DriverInfo driverInfo) {
        rabbitTemplate.convertAndSend(eventsExName,"", driverInfo);
        return ResponseEntity.ok("Message send!");
    }
}
