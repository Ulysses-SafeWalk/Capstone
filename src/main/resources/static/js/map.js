"use strict";

function addToFavorites(event){
    console.log(event);
}

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


    //get users current position
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

    //establish icon library
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
        },
        both: {
            icon: "/img/terrace.png"
        },
        BURGLARY: {
            icon: "/img/burglary.png"
        },
        KIDNAPPING: {
            icon: "/img/kidnap.png"
        },
        MURDER: {
            icon: "/img/murder.png"
        },
        ROBBERY: {
            icon: "/img/robbery.png"
        },
        SEXASSAULT: {
            icon: "/img/assault.png"
        },
        THEFT: {
            icon: "/img/theft.png"
        }
    };

    var infowindow = new google.maps.InfoWindow();

    function createInfoWindows(layerName) {
        layerName.addListener('click', function (event) {
            let name = event.feature.getProperty('name');
            let locationID = event.feature.getProperty('yelpID');
            console.log(locationID);
            let htmlContent = "<p>" + name + "</p>" +
                "<form name='reviews' action='/reviews/" + locationID + "' method='get'>" +
                "<button>User Safety Reviews</button>" + "</form>" +
                "<button id='"+ locationID +"'>Add to Favorites</button>" +
                "<form name='yelp' target='_blank' action='https://yelp.com/biz/" + locationID + "' method='get'>" +
                "<button>Yelp Reviews</button>" + "</form>";
            infowindow.setContent(htmlContent);
            console.log(infowindow);

            setTimeout(function(){
                enableFavoritesButton(locationID, name);
            }, 100);
            infowindow.setPosition(event.feature.getGeometry().get());
            infowindow.setOptions({pixelOffset: new google.maps.Size(0, -30)});
            infowindow.open(map);
        });

    }
        function enableFavoritesButton(locationID) {
            // google.maps.event.addListener(infowindow, 'domready', function () {
                document.getElementById(locationID).addEventListener("click", function (e) {
                    e.preventDefault();
                    console.log("clicked!");
                    console.log(locationID);
                    $.get("/favorites/" + locationID)
                        .done(function (data) {
                            console.log(data)
                        })
                        .fail(function () {
                            console.log("Fail!")
                        })
                })
            // });
        }


    //facilities layer
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

    //crime layer
    let crimeLayer = new google.maps.Data();
    crimeLayer.loadGeoJson('/json/crimeGeo.json');
    let crimeStyling = function(feature) {
        let StyleOptions = {
            icon : Icons[feature.getProperty('crime')].icon
        };
        return StyleOptions;
    };
    crimeLayer.setStyle({visible:false});
    crimeLayer.setMap(map);



    //location layers
    let bothLayer = new google.maps.Data();
    let barLayer = new google.maps.Data();
    let restaurantLayer = new google.maps.Data();


    //create layer toggle
    $('#facilitiesLayer').change(function(){
        if($(this).is(':checked')){
            facilitiesLayer.setStyle(facilitiesStyling)
        } else {
            facilitiesLayer.setStyle({visible: false})
        }
    });
    $('#crimeLayer').change(function(){
        if($(this).is(':checked')){
            crimeLayer.setStyle(crimeStyling)
        } else {
            crimeLayer.setStyle({visible: false})
        }
    });
    $('#bothLayer').change(function(){
        if($(this).is(':checked')){
            bothLayer.setStyle({icon: Icons["both"].icon})
        } else {
            bothLayer.setStyle({visible: false})
        }
    });
    $('#barLayer').change(function(){
        if($(this).is(':checked')){
            barLayer.setStyle({icon: Icons["bar"].icon})
        } else {
            barLayer.setStyle({visible: false})
        }
    });
    $('#restaurantLayer').change(function(){
        if($(this).is(':checked')){
            restaurantLayer.setStyle({icon: Icons["restaurant"].icon})
        } else {
            restaurantLayer.setStyle({visible: false})
        }
    });

    function setLocationLayers(layerName, filepath) {
        layerName.loadGeoJson(filepath);
        layerName.setStyle({visible: false});
        layerName.setMap(map);

        createInfoWindows(layerName);
    }

    setLocationLayers(bothLayer, '/json/bothGeo.json');
    setLocationLayers(barLayer, '/json/barsGeo.json');
    setLocationLayers(restaurantLayer, '/json/restaurantsGeo.json');

//function to convert json to heatmapData

    let heatMapData = [];

    var jsonRequest = $.get('/json/crimeGeo.json');
    jsonRequest.done(function(response){
        console.log(response.features);
        let crimes = response.features;
        for (var i = 0; i < crimes.length; i++){
            let mylat = parseFloat(crimes[i].geometry.coordinates[1]);
            let mylong = parseFloat(crimes[i].geometry.coordinates[0]);
            // console.log(parseFloat(lat), parseFloat(long));
            heatMapData.push({location: new google.maps.LatLng(mylat, mylong), weight: parseInt(crimes[i].properties.weight)})
        }
        return heatMapData;
    });
        console.log(heatMapData);

    // var heatMapData = [
    //     {location: new google.maps.LatLng(29.4361416,-98.5352314), weight: 1},
    //     {location: new google.maps.LatLng(29.4327845,-98.5103019), weight: 500},
    //     {location: new google.maps.LatLng(29.423757,-98.4717689), weight: 1000}
    // ];

    var heatmap = new google.maps.visualization.HeatmapLayer({
        data: heatMapData,
        radius: 10
    });
    heatmap.setMap(map);
//function to convert json to geojson
//     function createGeoJson(filepath, featureListName) {
//         console.log("Starting up");
//
//         let geojson = {
//             type: "FeatureCollection",
//             name: featureListName,
//             crs: {
//                 type: "name",
//                 properties: {
//                     name: "urn:ogc:def:crs:EPSG::4269"
//                 }
//             },
//             features: []
//
//         };
//
//         var jsonRequest = $.get(filepath);
//
//         jsonRequest.done(function (response) {
//             for (var i = 0; i < response.businesses.length; i++) {
//                 geojson.features.push({
//                     "type": "Feature",
//                     "geometry": {
//                         "type": "Point",
//                         "coordinates": [response.businesses[i].coordinates.longitude, response.businesses[i].coordinates.latitude]
//                     },
//                     "properties": {
//                         "name": response.businesses[i].name,
//                         "id": response.businesses[i].id
//                     }
//                 });
//             }
//         });
//         return geojson;
//     }
//
//     let bargeojson = createGeoJson('/json/bars.json', "Bars");
//     $.get('/json/publicSafetyFacilities.json').done(function(response){
//         console.log(response);
//     });
//     console.log(bargeojson);




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

    // let barLayer = new google.maps.Data();
    // barLayer.addGeoJson(JSON.parse(bargeojson));
    // barLayer.setMap(map);

    // restaurantsLayer.setStyle({
    //     icon: "/img/restaurant.png"
    // });
    // restaurantsLayer.setMap(map);


}

initialize();

