package android.example.caproject;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

public class ModuleActivity extends AppCompatActivity  {

    public static RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        if (ModuleActivity.queue == null) {
            ModuleActivity.queue = Volley.newRequestQueue(getApplicationContext());
        }

        String url = getResources().getString(R.string.url_api) + "/Module/GetModuleContent";

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            Map apiResponse = ModuleActivity.toMap(new JSONObject(response));
                             if(apiResponse.get("status").toString().equals("success")){
                                 List<Object> modules = (ArrayList)apiResponse.get("topics");
                                 List<Object> subtopics = (ArrayList)apiResponse.get("subTopics");
                                 RecyclerView recyclerView = (RecyclerView) findViewById(R.id.detail_view);
                                 RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                 recyclerView.setLayoutManager(layoutManager);
                                 RecyclerView.Adapter mAdapter = new AdapterModuleContent(modules);
                                 recyclerView.setAdapter(mAdapter);
                             } else {
                                 Log.v("error", apiResponse.get("message").toString());
                             }

                        }
                        catch(Exception e){
                          Log.v("Error", e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              Log.v("Error", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Module_ID","1");
                params.put("ForApp", "true");
                return params;
            }
        };




        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               ModuleActivity.queue.add(stringRequest);
            }
        },200);

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

   }

