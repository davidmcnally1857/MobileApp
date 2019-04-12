package android.example.caproject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (Utils.queue == null) {
          Utils.queue = Volley.newRequestQueue(getApplicationContext());
        }
        String url = getResources().getString(R.string.url_api) + "/Module/GetModulesForUser";

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Map responseApi = Utils.toMap(new JSONObject(response));
                            if (responseApi.get("status").toString().equals("success")) {
                                List<Object> modules = (ArrayList)responseApi.get("modules");
                                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(layoutManager);
                                RecyclerView.Adapter mAdapter = new AdapterModule(modules);
                                recyclerView.setAdapter(mAdapter);
                            } else {
                                Log.v("error", responseApi.get("modules").toString());
                            }
                        } catch (Exception e) {
                            Log.v("error", e.getMessage());

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Response", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("User_ID", "1");
                params.put("ForApp", "true");
                return params;
            }

        };
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.queue.add(stringRequest);
            }
        }, 200);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refreshbutton, menu);
         return true;
    }


    }


