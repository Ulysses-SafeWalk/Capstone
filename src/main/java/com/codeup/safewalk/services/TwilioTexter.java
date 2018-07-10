package com.codeup.safewalk.services;

import com.codeup.safewalk.models.Location;
import com.codeup.safewalk.repositories.LocationRepository;
import com.codeup.safewalk.repositories.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwilioTexter {



    @Value("${twilio.account-sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.account-auth-token}")
    private String AUTH_TOKEN;

    @Value("${twilio.account-fromnumber}")
    private String fromNumber;

        public void go(String toNumber,String username, String address){

            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message
                    .creator(new PhoneNumber(toNumber), // to
                            new PhoneNumber(fromNumber), // from
                                    "Hello this is a SafeWalk emergency message " + username +
                                    " has sent an alert. Their nearest definable location is " + address +
                                    "they might want some help" )
                    .create();
        }
}
