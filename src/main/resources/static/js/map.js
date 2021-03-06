"use strict";

function addToFavorites(event){
    console.log(event);
}

function initialize() {

    let codeup = new google.maps.LatLng(29.426709, -98.489604);

    let mapOptions = {
        zoom: 17,
        center: codeup,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        disableDefaultUI: true
    };
    let map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions);

    let codeupMarker = new google.maps.Marker({
        position: codeup,
        icon: "/img/codeup_map_icon.png",
        map: map
    });

    map.setOptions({minZoom: 12, maxZoom: 20});



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
                icon: "/img/user-marker.png"
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
            icon: "/img/both.png"
        },
        family: {
            icon: "/img/playground.png"
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
            let htmlContent = "<div class='flex-container'><p class='text-center infowin-title'>" + name + "</p>"  +
                "<form name='reviews' action='/reviews/" + locationID + "' method='get'>" +
                "<button class='mx-auto d-block btn btn-sm review-btn infowin-btn' >User Safety Reviews</button>" + "</form>" +
                "<button class='mx-auto d-block btn btn-sm infowin-btn btn-outline-danger' id='" + locationID + "'>" +
                "<i class='fa fa-heart'></i>" +
                "<span class='fav-text'>Add to favorites</span></button>" +
                "<div class='zoom'><a target='_blank' href='https://yelp.com/biz/" + locationID + "'>" +
                "<img class='mx-auto d-block' src='../img/yelp.png' alt='yelp'/></a></div></div>";
            infowindow.setContent(htmlContent);

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
                    $(this).toggleClass('liked');
                    $('.fav-text').text('Added to favorites');
                    // $('.button-like').prop('disabled', true);
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
    crimeLayer.loadGeoJson('/json/crimeGeo18Jul.json');
    let crimeStyling = function(feature) {
        let StyleOptions = {
            icon : Icons[feature.getProperty('crime')].icon
        };
        return StyleOptions;
    };
    crimeLayer.setStyle({visible:false});
    crimeLayer.setMap(map);
    crimeLayer.addListener('click', function (event) {
        let crime = event.feature.getProperty('crimeCode');
        let description;
        let dateTime = event.feature.getProperty('dateTime');
        switch(crime){
            case 0: description = "THEFT OF SERVICE $100 TO < $750";
                break;
            case 1: description = "THEFT $100 TO < $750";
                break;
            case 2: description = "BURG BLDG W-INTENT COMMIT THEFT";
                break;
            case 3: description = "THEFT UNDER $100";
                break;
            case 4: description = "ROBBERY INDIVIDUAL";
                break;
            case 5: description = "THEFT $750 TO < $2,500";
                break;
            case 6: description = "THEFT OF SERVICE UNDER $100";
                break;
            case 7: description = "AGG ROBBERY INDIVIDUAL";
                break;
            case 8: description = "IDENTITY THEFT BY ELECTRONIC DEVICE";
                break;
            case 9: description = "BURG HAB INTENT THEFT/FELONY";
                break;
            case 10: description = "THEFT $2,500 TO < $30,000";
                break;
            case 11: description = "THEFT $2,500 TO < $30,000 VEHICLE";
                break;
            case 12: description = "ROBBERY BUSINESS";
                break;
            case 13: description = "ATT BURG COIN-OP MACHINE";
                break;
            case 14: description = "BURGLARY BUILDING-INTENT THEFT/FELONY";
                break;
            case 15: description = "THEFT OF SERVICE $2,500 TO < $30,000";
                break;
            case 16: description = "BURGLARY BUILDING-NO FORCE";
                break;
            case 17: description = "THEFT FIREARM";
                break;
            case 18: description = "BURGLARY HABITATION-NO FORCE";
                break;
            case 19: description = "BURG HAB-INTENT COMMIT ASSAULT";
                break;
            case 20: description = "SEXUAL ASSAULT";
                break;
            case 21: description = "THEFT $2,500 TO < $30,000 (ATT)";
                break;
            case 22: description = "THEFT PERSON";
                break;
            case 23: description = "MURDER/19.02 PC/F1";
                break;
            case 24: description = "THEFT UNDER $2,500 ENHANCED";
                break;
            case 25: description = "THEFT $30,000 TO < $150,000";
                break;
            case 26: description = "AGG ROBBERY INDIVIDUAL-VEHICLE";
                break;
            case 27: description = "BURG COIN-OP MACHINE";
                break;
            case 28: description = "THEFT $1,500 TO <$10,000 (CARGO)";
                break;
            case 29: description = "BURG HAB-INT COM FEL-NO FORCE";
                break;
            case 30: description = "THEFT $30,000 TO < $150,000 VEHICLE";
                break;
            case 31: description = "THEFT ALUM/BRONZE/COPPER <$20,000";
                break;
            case 32: description = "AGG ROBBERY BUSINESS";
                break;
            case 33: description = "KIDNAPPING";
                break;
            case 34: description = "THEFT $100 TO <750-ELDERLY";
                break;
            case 35: description = "BURG HAB-INTENT COMMIT FEL-FORCE";
                break;
            case 36: description = "SEXUAL ASSAULT - CHILD";
                break;
        }
        let address = event.feature.getProperty('address');
        let crimeWindow = "<p>" + description + "</p>" +
            "<p>" + dateTime +  "</p>" +
            "<p>" + address + "</p>";
        infowindow.setContent(crimeWindow);
        infowindow.setPosition(event.feature.getGeometry().get());
        infowindow.setOptions({pixelOffset: new google.maps.Size(0, -30)});
        infowindow.open(map);
    });



    //location layers
    let bothLayer = new google.maps.Data();
    let barLayer = new google.maps.Data();
    let restaurantLayer = new google.maps.Data();


    //family layer
    let familyLayer = new google.maps.Data();
    familyLayer.loadGeoJson('/json/familyGeo.json');
    let familyStyling = function(feature) {
        let familyStyleOptions = {
            icon: Icons["family"].icon
        };
        return familyStyleOptions;
    };
    familyLayer.setStyle(familyStyling);
    familyLayer.setMap(map);
    createInfoWindows(familyLayer);

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
    $('#familyLayer').change(function(){
        if($(this).is(':checked')){
            familyLayer.setStyle({icon: Icons["family"].icon})
        } else {
            familyLayer.setStyle({visible: false})
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
    // setLocationLayers(familyLayer, '/json/familyGeo.json');



//function to convert json to heatmapData
    let heatMapData = [];

    var jsonRequest = $.get('/json/crimeGeo18Jul.json');
    jsonRequest.done(function(response){
        // console.log(response.features);
        let crimes = response.features;
        for (var i = 0; i < crimes.length; i++){
            let mylat = parseFloat(crimes[i].geometry.coordinates[1]);
            let mylong = parseFloat(crimes[i].geometry.coordinates[0]);
            // console.log(parseFloat(lat), parseFloat(long));
            heatMapData.push({location: new google.maps.LatLng(mylat, mylong), weight: parseInt(crimes[i].properties.weight)})
        }
        return heatMapData;
    });

    var heatmap = new google.maps.visualization.HeatmapLayer({
        data: heatMapData,
        radius: 10
    });
$('#heatmapLayer').change(function(){
    if($(this).is(':checked')){
        heatmap.setMap(map);
    } else {
        heatmap.setMap(null);
    }
})

//searchBar

// Create the search box and link it to the UI element.
    // Create the search box and link it to the UI element.
    var input = document.getElementById('pac-input');
    var searchBox = new google.maps.places.SearchBox(input);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    // Bias the SearchBox results towards current map's viewport.
    map.addListener('bounds_changed', function() {
        searchBox.setBounds(map.getBounds());
    });

    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener('places_changed', function() {
        var places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        // For each place, get the icon, name and location.
        var bounds = new google.maps.LatLngBounds();
        places.forEach(function(place) {
            if (!place.geometry) {
                console.log("Returned place contains no geometry");
                return;
            }

            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });

    $('.dropdown-menu').on({
        "click": function(e){
            e.stopPropagation();
        }
    });

}

initialize();

