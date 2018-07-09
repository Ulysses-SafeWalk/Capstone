package com.codeup.safewalk.models;


import com.github.filosganga.geogson.gson.GeometryAdapterFactory;
import com.github.filosganga.geogson.model.Coordinates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

public class JsonReader {
    public static final String TYPE_COORD = "coordinates";
    public static final String TYPE_NAME = "name";

    private static String readAll(Reader reader) throws IOException{
      StringBuilder stringBuilder = new StringBuilder();
      int cp;
      while ((cp = reader.read()) != -1) {
          stringBuilder.append((char) cp);
      }
      return stringBuilder.toString();
    }

    public static JSONObject readAndConvertJsonFromUrl(String url) throws IOException {
        InputStream inputStream = new URL(url).openStream();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = readAll(reader);
            JSONObject json = new JSONObject(jsonText);
//            JSONObject geoJson = new JSONObject();
//
//            JSONArray jsonArray = new JSONArray(json);
//
//            for(int i=0; i<jsonArray.length(); i++)
//            {
//                JSONObject object = jsonArray.getJSONObject(i);
//                JSONObject target = object.getJSONObject("coordinates");
//                String indicator_name = target.getString("latitude");
//
//                System.out.println(indicator_name);
//            }
            return json;
        } finally {
            inputStream.close();
        }
    }

//    function createGeoJson() {
//        console.log("Starting up");
//
//        restaurantgeojson = {
//                type: "FeatureCollection",
//                features: [],
//        };
//
//        var jsonRequest = $.get('/json/restaurants.json');
//
//        jsonRequest.done(function (response) {
//            // console.log(response);
//            for (var i = 0; i < response.businesses.length; i++) {
//                // console.log(response.businesses[i].name);
//                // console.log(response.businesses[i].coordinates.latitude);
//                // console.log(response.businesses[i].coordinates.longitude);
//
//                restaurantgeojson.features.push({
//                        "type": "Feature",
//                        "geometry": {
//                    "type": "Point",
//                            "coordinates": [response.businesses[i].coordinates.latitude, response.businesses[i].coordinates.longitude]
//                },
//                "properties": {
//                    "name": response.businesses[i].name
//                }
//                });
//            }
//        });


        public static void main(String[] args) throws IOException {
        JSONObject json = readAndConvertJsonFromUrl("file:///Users/emilyrodriguez/IdeaProjects/Ulysses-SafeWalk/Capstone/src/main/resources/static/json/bars.json");
//        System.out.println(json.toString());
    }
}
