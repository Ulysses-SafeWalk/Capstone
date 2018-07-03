"use strict";

// function test(){
//     console.log("this is working!");
// }
//
// test();


function initialize() {
    let myLatlng = new google.maps.LatLng(29.426709, -98.489604);

    let mapOptions = {
        zoom: 14,
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
            let initialLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            map.setCenter(initialLocation);

            let marker = new google.maps.Marker({
                position: initialLocation,
                map: map,
                animation: google.maps.Animation.DROP
            })
        });
    }

    let facilityIcons = {
        POLICE: {
            icon: "/img/police.png"
        },
        FIRE: {
            icon: "/img/firemen.png"
        }
    };

    let facilitiesLayer = new google.maps.Data();
    facilitiesLayer.loadGeoJson('/json/publicSafetyFacilities.json');


    facilitiesLayer.setStyle(function(feature) {
        console.log(feature.getProperty('AgencyType'));
        let icon = null;
        if (feature.getProperty('AgencyType')){
            icon = facilityIcons[feature.getProperty('AgencyType')].icon;
        }
        return /** @type {google.maps.Data.StyleOptions} */({
            icon: icon
        });
    });

    facilitiesLayer.setMap(map);

    $('#facilities').on('click', function(){
        facilitiesLayer.setStyle({visible: false})
    })

    //to hide layer:
    //facilitiesLayer.setStyle({visible: false});

}

initialize();

