package android.example.caproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    String name;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.enterEmail);
        password = (EditText)findViewById(R.id.enterPassword);

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.queue == null) {
                    Utils.queue = Volley.newRequestQueue(getApplicationContext());
                }
            }
        });

        String url = getResources().getString(R.string.url_api)+"/User/Login";

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Map loginResponse = Utils.toMap(new JSONObject(response));
                            if (loginResponse.get("status").toString().equals("success")) {
                                Log.v("user", loginResponse.get("user").toString());
                            } else {
                                Log.v("error", loginResponse.get("message").toString());
                            }
                        } catch (Exception e) {
                            Log.v("Error", "Could not create JSON Object");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Response is", " Didnt work");
            }
        }) {


   };
    }
}
