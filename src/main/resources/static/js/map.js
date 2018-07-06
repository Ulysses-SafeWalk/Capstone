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

    var infowindow = new google.maps.InfoWindow();

    facilitiesLayer.addListener('click', function(event) {
        // document.getElementById('info-box').textContent =
        //     event.feature.getProperty('Name');
        let name = event.feature.getProperty('Name');
        infowindow.setContent(name);
        infowindow.setPosition(event.feature.getGeometry().get());
        infowindow.setOptions({pixelOffset: new google.maps.Size(0,-30)});
        infowindow.open(map);
    });


    function createGeoJson(filepath, featureListName) {
        console.log("Starting up");

        let geojson = {
            type: "FeatureCollection",
            name: featureListName,
            crs: {
                type: "name",
                properties: {
                    name: "urn:ogc:def:crs:EPSG::4269"
                }
            },
            features: []

        };

        var jsonRequest = $.get(filepath);

        jsonRequest.done(function (response) {
            for (var i = 0; i < response.businesses.length; i++) {
                geojson.features.push({
                    "type": "Feature",
                    "geometry": {
                        "type": "Point",
                        "coordinates": [response.businesses[i].coordinates.longitude, response.businesses[i].coordinates.latitude]
                    },
                    "properties": {
                        "name": response.businesses[i].name,
                        "id": response.businesses[i].id
                    }
                });
            }
        });
        return geojson;
    }

    let bargeojson = createGeoJson('/json/bars.json', "Bars");
    $.get('/json/publicSafetyFacilities.json').done(function(response){
        console.log(response);
    });
    console.log(bargeojson);




    // let featurelist = bargeojson.features;
    //
    //
    // for (var i=0; i<featurelist.length(); i++){
    // $('#one').append(featurelist[i]);
    // }
    // $('#info-box').append("Here is the json: " + features.forEach(function(feature) {
    //    "<p>" + feature + "</p>"
    // }));

    // let parsedRestaurantJson = JSON.parse(restaurantgeojson);

    // let restaurantsLayer = new google.maps.Data();
    // restaurantsLayer.addGeoJson(JSON.stringify(restaurantgeojson));

    let barLayer = new google.maps.Data();
    barLayer.addGeoJson(bargeojson);
    barLayer.setMap(map);

    // restaurantsLayer.setStyle({
    //     icon: "/img/restaurant.png"
    // });
    // restaurantsLayer.setMap(map);


}

initialize();

