"use strict";

function initialize() {

    let codeup = new google.maps.LatLng(29.426709, -98.489604);

    let mapOptions = {
        zoom: 14,
        center: codeup,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    let map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions);

    let codeupMarker = new google.maps.Marker({
        position: codeup,
        icon: "/img/codeup_map_icon.png",
        map: map
    });

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            window.SafeWalkGeo = {
                haveDate: true, position: position.coords
            };

            let myLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            map.setCenter(myLocation);

            let marker = new google.maps.Marker({
                position: myLocation,
                map: map,
                animation: google.maps.Animation.DROP,
            })
        }, function(error){
            alert("Something went wrong");
            console.log(error);
        });

    }

    //Emergencyfacilities layer
    let Icons = {
        POLICE: {
            icon: "/img/police.png"
        },
        FIRE: {
            icon: "/img/firemen.png"
        },
        restaurant: {
            icon: "/img/restaurant.png"
        },
        bar: {
            icon: "/img/bar_cocktail.png"
        }
    };
    let facilitiesLayer = new google.maps.Data();
    facilitiesLayer.loadGeoJson('/json/publicSafetyFacilities.json');

    let facilitiesStyling = function(feature) {
        let StyleOptions = {
            icon : Icons[feature.getProperty('AgencyType')].icon
        };
        return StyleOptions;
    };
    facilitiesLayer.setStyle(facilitiesStyling);
    facilitiesLayer.setMap(map);

    $('#facilitiesLayer').change(function(){
        if($(this).is(':checked')){
            facilitiesLayer.setStyle(facilitiesStyling)
        } else {
            facilitiesLayer.setStyle({visible: false})
        }
    });

    // function generateLocationMarkers() {
    //     //Restaurant layer
    //     let restaurantRequest = {
    //         location: myLocation,
    //         radius: '10000',
    //         type: ['restaurant']
    //     };
    //
    //     let barRequest = {
    //         location: myLocation,
    //         radius: '10000',
    //         type: ['bar']
    //     };
    //
    //
    //     let service = new google.maps.places.PlacesService(map);
    //     service.nearbySearch(restaurantRequest, function (results, status) {
    //         callback(results, status, restaurantRequest.type)
    //     });
    //     service.nearbySearch(barRequest, function (results, status) {
    //         callback(results, status, barRequest.type)
    //     });
    //
    //     function callback(results, status, category) {
    //         if (status == google.maps.places.PlacesServiceStatus.OK) {
    //             for (var i = 0; i < results.length; i++) {
    //                 var place = results[i];
    //                 createMarker(place, category);
    //             }
    //             ;
    //         }
    //     }
    //
    //     function createMarker(place, category) {
    //         new google.maps.Marker({
    //             position: place.geometry.location,
    //             map: map,
    //             icon: Icons[category].icon
    //         });
    //     }
    // }
    // generateLocationMarkers();

    $.ajax({
        url: "https://api.yelp.com/v3/businesses/search?term=restaurants&location=San+Antonio",
        headers: {
            "Authorization": "Bearer bc_irZxNZ-Ep0rZHDClW6t_Zts0IKH5_ZYf5_3UWs7pt9VXo6H0Sx8iX96AgtcYoOYPjCvYBDhVTiiMMjRPMw1rHq2kxrpGH5SxXpXJA4aoEAxtnd6QOIZLSKrU7W3Yx",
            "Content-Type":"application/json"
        },
        method: "GET",
        dataType: "json",
    });



}

initialize();

