package com.codeup.safewalk.services;


import com.codeup.safewalk.models.Contact;
import com.codeup.safewalk.models.User;
import com.codeup.safewalk.repositories.ContactRepository;
import com.codeup.safewalk.repositories.UserRepository;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TwilioTexter {
    private static  UserRepository userRepository;
    private final ContactRepository contactRepository;
    private UserRepository users;

    public TwilioTexter(UserRepository userRepository, ContactRepository contactRepository) {
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    @Value("${twilio.account-sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.account-auth-token}")
    private String AUTH_TOKEN;

    @Value("${twilio.account-fromnumber}")
    private String fromNumber;

    private static String reverseGeocode(String lat, String lng) {

       double dLat = Double.parseDouble(lat);
       double dLng = Double.parseDouble(lng);

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCQI06r-N-DESGnAzN278EMt3yoiBc5r5A")
                .build();
        try {
            GeocodingResult[] result = GeocodingApi.newRequest(context).latlng(new LatLng(dLat, dLng)).await();
            return result[0].formattedAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Address not found";
    }

    private List<Contact> getToNumber(){

        //User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //userRepository.findById(sessionUser.getId());

        //todo: change this to find by id, and set it up to pass an id
        User user = userRepository.first();

        return contactRepository.findByUser(user);
    }

    public void go(String lat, String lng, User user, String messageType){
//        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = users.findById(sessionUser.getId());


        String userName = user.getFirst_name() + " " + user.getLast_name();

        List<Contact> numberList = getToNumber();

        String address = reverseGeocode(lat, lng);

        String text = "";

        if (messageType.equalsIgnoreCase("1")){
            text = "Hello this is a message from Safewalk " + userName + " is now safe. good job";
        }else if (messageType.equalsIgnoreCase("0")){
            text =
                    "Hello this is a SafeWalk emergency message " + userName +
                            " has sent an alert. Their last definable location is " + address +
                            " you might want to see to that";
        }

        for (Contact number : numberList) {
            String toNumber = number.getPhoneNumber();

            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);



            Message message = Message
                    .creator(new PhoneNumber(toNumber), // to
                            new PhoneNumber(fromNumber), // from
                            text)
                    .create();

        }
    }
}
