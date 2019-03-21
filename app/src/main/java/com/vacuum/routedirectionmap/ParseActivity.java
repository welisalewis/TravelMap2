package com.vacuum.routedirectionmap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class ParseActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private LatLng origin = new LatLng(19.0803263, 72.84999239999999);
    private LatLng dest = new LatLng(19.0496701, 72.8306348);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_info);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 jsonParse();
            }
        });
    }

    private void jsonParse(){

       String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        String mode = "mode_transit";

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters +
                 "&key=" + "AIzaSyB7r-0tHNUGgnWbljMlY5AvRej9E5R4ZIE" ;



        //Parsing the json shizz




        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            //@Override
                    public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("steps");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject steps = jsonArray.getJSONObject(i);

                        String distance = steps.getString("distance");

                        String duration = steps.getString("duration");

                        String instructions = steps.getString("html_instructions");

                        mTextViewResult.append(distance + "," + duration + "," + instructions + "\n\n");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }

}
