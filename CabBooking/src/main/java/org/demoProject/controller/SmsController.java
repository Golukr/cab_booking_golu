package org.demoProject.controller;

import org.demoProject.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SmsController {

    @Autowired
    private TwilioService twilioService;

    @GetMapping("/sms")
    public ResponseEntity<String> sendSms(@RequestParam("to") String to) {
        twilioService.sendSms(to, "hii");
        return ResponseEntity.ok("SMS sent successfully!");
    }
}
