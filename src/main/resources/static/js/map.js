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
        icon: "codeup.png",
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
}

initialize();
