"use strict";

(function () {

    $('#moovit').click(function(event) {
        let crd = window.SafeWalkGeo.position;
        let latitude = crd.latitude;
        let longitude = crd.longitude;
        let METRO_ID = latitude + "_" + longitude;
        console.log(METRO_ID);
        window.location = `www.moovitapp.com/tripplan?customerId=<ID>&metroId=${METRO_ID}`;
    });


    $('#uber').click(function(event) {
        let geocoder = new google.maps.Geocoder;
        let crd = window.SafeWalkGeo.position;
        let latlng = {lat: parseFloat(crd.latitude), lng: parseFloat(crd.longitude)};

        geocoder.geocode({'location': latlng}), function (results, status) {
            if (status === 'OK') {
                if (results[0]) {
                    let formatted_address = results[0].formatted_address;
                    window.location = `https://m.uber.com/?action=setPickup&client_id=oQg4FB9S9bs-0xxQ7VcrlCp0raIrWxTd&pickup[latitude]=${latitude}&pickup[longitude]=${longitude}&pickup[formatted_address]=${formatted_address}&dropoff[formatted_address]=Codeup%2C%20Navarro%20Street%2C%20San%20Antonio%2C%20TX%2C%20USA&dropoff[latitude]=29.426786&dropoff[longitude]=-98.489576`;
                } else {
                    window.alert('Could not access your current location');
                }
            }
        }
    });

    // https://www..moovitapp.com/tripplan?customerId=<ID>&metroId=<METRO_ID>&lang=<LANG_CODE>&to=<DISPLAY ADDRESS>&tll=<LAT_LON>
})();

