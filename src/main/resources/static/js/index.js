function createGeoJson() {
    console.log("Starting up");

    var restaurantgeojson = {
        type: "FeatureCollection",
        features: [],
    };

    var jsonRequest = $.get('/json/restaurants.json');

    jsonRequest.done(function (response) {
        console.log(response);
        for (var i = 0; i < response.businesses.length; i++) {
            console.log(response.businesses[i].name);
            console.log(response.businesses[i].coordinates.latitude);
            console.log(response.businesses[i].coordinates.longitude);

            restaurantgeojson.features.push({
                "type": "Feature",
                "geometry": {
                    "type": "Point",
                    "coordinates": [response.businesses[i].coordinates.latitude, response.businesses[i].coordinates.longitude]
                },
                "properties": {
                    "name": response.businesses[i].name
                }
            });
        }
        console.log(restaurantgeojson);
    });

    var jsonfile = require('jsonfile');
    var file = '/json/restaurantsGeo.json';
    var obj = restaurantgeojson;

    jsonfile.writeFile(file, obj, function(err){
        console.log(err);
    });

    console.log("finished");

}

createGeoJson();

