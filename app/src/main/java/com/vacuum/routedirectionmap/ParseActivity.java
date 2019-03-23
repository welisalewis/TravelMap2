package com.vacuum.routedirectionmap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;



public class ParseActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private TextView mTextViewResult2;
    private RequestQueue mQueue;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse);

        mTextViewResult = findViewById(R.id.text_view_result);
        //mTextViewResult2 = findViewById(R.id.text_view_result2);
        Button buttonParse = findViewById(R.id.button_info);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }


    public void jsonParse() {

        Intent intent = getIntent();
        //getIntent().getSerializableExtra("StringUrl");
        String url = (String)intent.getSerializableExtra("StringUrl");            //RReal string from other activity

        //String url = "https://maps.googleapis.com/maps/api/directions/json?origin=Vakola&destination=DBIT+Kurla&sensor=false&key=AIzaSyB7r-0tHNUGgnWbljMlY5A%20vRej9E5R4ZIE&mode=transit";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    //@Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("routes");
                            //JSONArray jsonArray1 = response.getJSONArray("legs");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject routes = jsonArray.getJSONObject(i);

                                String fare = routes.getString("fare");
                               // JSONArray jsonArray1 = response.getJSONArray("legs");

                                /*for(int j =0; j<jsonArray1.length(); j++){
                                    JSONObject legs = jsonArray1.getJSONObject(j);

                                    String steps = legs.getString("steps");


                                    mTextViewResult2.append(steps + "");
                                }
*/
                                String legs = routes.getString("legs");
                               /* JSONArray jsonArray1 = routes.getJSONArray("legs");

                                    for(int j =0; j<jsonArray1.length(); j++){
                                        JSONObject legs = jsonArray1.getJSONObject(j);

                                        String steps = legs.getString("steps");

                                        Toast.makeText(this,"Got",Toast.LENGTH_LONG).show();

                                    }*/

                                mTextViewResult.setMovementMethod(new ScrollingMovementMethod());
                                mTextViewResult.append(  fare + "\n\n" +legs + "\n \n\n \n" );
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}