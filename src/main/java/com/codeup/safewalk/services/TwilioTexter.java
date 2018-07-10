package com.codeup.safewalk.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;

public class TwilioTexter {

    @Value("${twilio.account-sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.account-auth-token}")
    private String AUTH_TOKEN;

    @Value("${twilio.account-fromnumber}")
    private String fromNumber;


        public void go(String toNumber, String address){



            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message
                    .creator(new PhoneNumber(toNumber), // to
                            new PhoneNumber(fromNumber), // from
                            "this  is a test message")
                    .create();

        }
}
