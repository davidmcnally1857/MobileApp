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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterModule.ItemClickListener {


    private String userId;
    TextView name;
    public static RequestQueue queue;
    Button select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextView)findViewById(R.id.name);
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("Name"));
        userId = intent.getStringExtra("UserID");
        select = (Button) findViewById(R.id.buttonSelect);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModuleActivity.class);
                startActivity(intent);
            }
        });


          if (MainActivity.queue == null) {
          MainActivity.queue = Volley.newRequestQueue(getApplicationContext());
        }
        String url = getResources().getString(R.string.url_api) + "/Module/GetModulesForUser";

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Map responseApi = MainActivity.toMap(new JSONObject(response));
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
                params.put("User_ID", userId);
                params.put("ForApp", "true");
                return params;
            }

        };
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.queue.add(stringRequest);
            }
        }, 200);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.refreshbutton, menu);
         return true;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {

        Map<String, Object> map = new HashMap<String, Object>();
        Iterator<String> keysIterator = object.keys();
        while (keysIterator.hasNext()) {
            String key = keysIterator.next();
            Object objectValue = object.get(key);
            if (objectValue instanceof JSONArray) {
                objectValue = toList((JSONArray) objectValue);

            } else if (objectValue instanceof JSONObject) {
                objectValue = toMap((JSONObject) objectValue);
            }
            map.put(key, objectValue);
        }

        return map;
    }

    public static List<Object> toList(JSONArray array) {
        List<Object> list = new ArrayList<Object>();
        try {
            for (int i = 0; i < array.length(); i++) {
                Object objectValue = array.get(i);
                if (objectValue instanceof JSONArray) {
                    objectValue = toList((JSONArray) objectValue);
                } else if (objectValue instanceof JSONObject) {
                    objectValue = toMap((JSONObject) objectValue);
                }
                list.add(objectValue);
            }
        } catch (Exception ex) {
            Log.e("Exception", ex.getMessage());
        }
        return list;
    }




    @Override
    public void onListClick(int position) {

        Intent intent = new Intent(MainActivity.this, ModuleActivity.class);
        startActivity(intent);

    }
}


