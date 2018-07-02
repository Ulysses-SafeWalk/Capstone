"use strict";

// function test(){
//     console.log("this is working!");
// }
//
// test();


function initialize() {
    var myLatlng = new google.maps.LatLng(29.426709, -98.489604);

    var mapOptions = {
        zoom: 18,
        center: myLatlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions);

    var codeupMarker = new google.maps.Marker({
        position: myLatlng,
        icon: "/img/codeup_map_icon.png",
        map: map
    });

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var initialLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            map.setCenter(initialLocation);

            var marker = new google.maps.Marker({
                position: initialLocation,
                map: map,
                animation: google.maps.Animation.DROP
            })
        });
    }

    var facilityIcons = {
        POLICE: {
            icon: "/img/police.png"
        },
        FIRE: {
            icon: "/img/firemen.png"
        }
    };

    var request = $.get("/json/publicSafetyFacilities.json");
    request.done(function(response) {
        var locations = response.features;
            locations.forEach(function(location){
                var lat = location.properties.LAT;
                var lng = location.properties.LON;
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(lat,lng),
                    icon: facilityIcons[location.properties.AgencyType].icon,
                    map: map
                });
            })
        });
}

initialize();
