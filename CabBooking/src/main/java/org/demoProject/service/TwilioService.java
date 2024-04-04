package org.demoProject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    public Message sendSms(String to, String message) {
        Twilio.init(accountSid, authToken);
        Message msg = null;
        try {
        	msg = Message.creator(new PhoneNumber(to), new PhoneNumber("++16184861593"), message).create();
		} catch (Exception e) {
			System.out.println("Message sent failed");
		}
        return msg;
    }
}