package com.arcadia.DataQualityDashboard.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class CheckDataQualityProgressController {

    @MessageMapping("/progress")
    public void handleMessage(@Payload String message) {
        System.out.println(message);
    }
}
