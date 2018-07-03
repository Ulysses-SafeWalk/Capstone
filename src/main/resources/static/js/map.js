"use strict";

function initialize() {
    let myLatlng = new google.maps.LatLng(29.426709, -98.489604);


    let mapOptions = {
        zoom: 18,
        center: myLatlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    let map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions);

    let codeupMarker = new google.maps.Marker({
        position: myLatlng,
        icon: "/img/codeup_map_icon.png",
        map: map
    });

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            window.SafeWalkGeo = {
                haveDate: true, position: position.coords
            };
            let initialLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            map.setCenter(initialLocation);

            let marker = new google.maps.Marker({
                position: initialLocation,
                map: map,
                animation: google.maps.Animation.DROP
            })
        }, function(error){
            alert("Something went wrong");
            console.log(error);
        });

    }

     map.data.loadGeoJson('/json/publicSafetyFacilities.json');
}

initialize();
