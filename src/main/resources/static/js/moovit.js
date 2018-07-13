"use strict";

(function () {

    $('#moovit', '#moovit2').click(function(event) {
        let crd = window.SafeWalkGeo.position;
        let latitude = crd.latitude;
        let longitude = crd.longitude;
        let METRO_ID = latitude + "_" + longitude;
        console.log(METRO_ID);
        window.location = `www.moovitapp.com/tripplan?customerId=<ID>&metroId=${METRO_ID}`;
    });

    // https://www..moovitapp.com/tripplan?customerId=<ID>&metroId=<METRO_ID>&lang=<LANG_CODE>&to=<DISPLAY ADDRESS>&tll=<LAT_LON>

    $('#uber').click(function(event) {
        let geocoder = new google.maps.Geocoder;
        let crd = window.SafeWalkGeo.position;
        let latitude = parseFloat(crd.latitude);
        let longitude = parseFloat(crd.longitude);
        let latlng = {lat: latitude, lng: longitude};

        geocoder.geocode({'location': latlng}, function (results, status) {
            console.log(status);
            if (status === 'OK') {
                if (results[0]) {
                    let currentAddress = results[0].formatted_address;
                    window.location = `https://m.uber.com/?action=setPickup&client_id=oQg4FB9S9bs-0xxQ7VcrlCp0raIrWxTd&pickup[latitude]=${latitude}&pickup[longitude]=${longitude}&pickup[formatted_address]=${currentAddress}&dropoff[formatted_address]=Codeup%2C%20Navarro%20Street%2C%20San%20Antonio%2C%20TX%2C%20USA&dropoff[latitude]=29.426786&dropoff[longitude]=-98.489576`;
                }
            } else {
                window.alert('Unable to map your current location');
            }
        });
    });


    $('#lyft').click(function (event) {
        let crd = window.SafeWalkGeo.position;
        let latitude = parseFloat(crd.latitude);
        let longitude = parseFloat(crd.longitude);
        window.location = `https://lyft.com/ride?id=lyft&pickup[latitude]=${latitude}&pickup[longitude]=${longitude}&partner=qCSej-crZHV3`;
    });

    // https://lyft.com/ride?id=lyft&pickup[latitude]=37.764728&pickup[longitude]=-122.422999&partner=YOUR_CLIENT_ID

})();



