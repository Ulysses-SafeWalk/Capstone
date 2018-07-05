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



let restaurantgeojson = "";

    function createGeoJson() {
        console.log("Starting up");

        restaurantgeojson = {
            type: "FeatureCollection",
            features: [],
        };

        var jsonRequest = $.get('/json/restaurants.json');

        jsonRequest.done(function (response) {
            // console.log(response);
            for (var i = 0; i < response.businesses.length; i++) {
                // console.log(response.businesses[i].name);
                // console.log(response.businesses[i].coordinates.latitude);
                // console.log(response.businesses[i].coordinates.longitude);

                restaurantgeojson.features.push({
                    "type": "Feature",
                    "geometry": {
                        "type": "Point",
                        "coordinates": [response.businesses[i].coordinates.longitude, response.businesses[i].coordinates.latitude]
                    },
                    "properties": {
                        "name": response.businesses[i].name
                    }
                });
            }
        });
    }


    createGeoJson();
    console.log(restaurantgeojson);

    // let parsedRestaurantJson = JSON.parse(restaurantgeojson);

    let restaurantsLayer = new google.maps.Data();
    // restaurantsLayer.addGeoJson(restaurantgeojson.toString());

    // restaurantsLayer.setStyle({
    //     icon: "/img/restaurant.png"
    // });
    restaurantsLayer.setMap(map);


}

initialize();

