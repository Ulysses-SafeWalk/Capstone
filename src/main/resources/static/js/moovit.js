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

    // https://www..moovitapp.com/tripplan?customerId=<ID>&metroId=<METRO_ID>&lang=<LANG_CODE>&to=<DISPLAY ADDRESS>&tll=<LAT_LON>
})();

