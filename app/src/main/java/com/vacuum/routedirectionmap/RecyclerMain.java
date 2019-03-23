package com.vacuum.routedirectionmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vacuum.routedirectionmap.models.Routes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerMain extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<Routes> routesList;

    RequestQueue rq;

    String request_url = "https://maps.googleapis.com/maps/api/directions/json?origin=Vakola&destination=Dbit+kurla&sensor=false&key=AIzaSyB7r-0tHNUGgnWbljMlY5A%20vRej9E5R4ZIE&mode=transit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


        rq = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CustomRecyclerAdapter(RecyclerMain.this, routesList);

        recyclerView.setAdapter(mAdapter);

        routesList = new ArrayList<>();

        sendRequest();

    }


    public void sendRequest(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){

                    Routes routes = new Routes();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        routes.setFare(jsonObject.getString("fare"));
                        routes.setLegs(jsonObject.getString("legs"));
                        //personUtils.setJobProfile(jsonObject.getString("jobprofile"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    routesList.add(routes);

                }

                /*mAdapter = new CustomRecyclerAdapter(RecyclerMain.this, routesList);

                recyclerView.setAdapter(mAdapter);*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        rq.add(jsonArrayRequest);

    }

}