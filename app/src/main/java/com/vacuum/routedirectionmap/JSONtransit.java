package com.vacuum.routedirectionmap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONtransit extends Activity {
    private LatLng origin = new LatLng(19.0803263, 72.84999239999999);
    private LatLng destination = new LatLng(19.0496701, 72.8306348);
    private Object ListView;


    //getting JSON files
/*String json_ String = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters
                + "&key=" + "AIzaSyB7r-0tHNUGgnWbljMlY5AvRej9E5R4ZIE" ;*/


    private String getUrl(LatLng origin, LatLng dest) {


        //  String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters + "&key=" + MY_API_KEY
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // String mode = "travel_mode";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters
                + "&key=" + "AIzaSyB7r-0tHNUGgnWbljMlY5AvRej9E5R4ZIE" ;


        return url;
    }

// actually parsing the data
    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_json);
    try {

        ListView listView = (ListView) findViewById(R.id.list_view);

        List<String> items = new ArrayList<>();
        JSONObject root = new JSONObject(getUrl(origin,destination));

        JSONArray array= root.getJSONArray("array");

        //this.setTitle(root.getString(""));

        for(int i=0;i<array.length();i++)
        {
            JSONObject object= array.getJSONObject(i);
            items.add(object.getString("fare"));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);

        if (listView != null) {
            listView.setAdapter(adapter);
        }

        JSONObject distance= root.getJSONObject("distance");
        Log.d("TAG","text value "+distance.getString("text"));

    } catch (JSONException e) {
        e.printStackTrace();
    }


}

}

