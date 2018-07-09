package com.codeup.safewalk.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioTexter {

//          private String fromNumber = "+12106721644";
//          private final String ACCOUNT_SID =
//                "ACd445f7bc727114a509225acb9e3ff199";
//          private final String AUTH_TOKEN =
//                "89f39b6ee2a518a171d32027dfd293c1";

        public void go(String toNumber, String address){

            config config = new config();
            String ACCOUNT_SID = config.getACCOUNT_SID();
            String AUTH_TOKEN = config.getAUTH_TOKEN();
            String fromNumber = config.getFromNumber();

            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message
                    .creator(new PhoneNumber(toNumber), // to
                            new PhoneNumber(fromNumber), // from
                            "this  is a test message")
                    .create();

        }
}
