package project.guild;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        switch (v.getId()) {
            case R.id.loginButton:
                //log.setText(username.getText()+"/"+password.getText());
                RequestQueue queue = Volley.newRequestQueue(this);
                String url = "http://10.0.2.2:4000/users/login";
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("username", username.getText());
                    requestBody.put("password", password.getText());

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url, requestBody, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String idUser = response.getString("idUser");
                                    String username = response.getString("username");
                                    String token = response.getString("token");
                                    Toast toast = Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT);
                                    toast.show();
                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                    intent.putExtra("idUser", idUser);
                                    intent.putExtra("EXTRA_SESSION_ID", username);
                                    intent.putExtra("EXTRA_SESSION_ID", token);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                Toast toast = Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }) {
                    /** Passing some request headers* */
                    @Override
                    public Map getHeaders() throws AuthFailureError {
                        HashMap headers = new HashMap();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };

                queue.add(jsonObjectRequest);
                break;

            case R.id.registerButton:
                startActivity(new Intent(getBaseContext(), Register.class));
                break;
        }
    }


}

