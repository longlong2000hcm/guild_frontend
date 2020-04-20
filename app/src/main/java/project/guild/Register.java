package project.guild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerButton =  findViewById(R.id.registerButton);
        Button registerBackButton =  findViewById(R.id.registerBackButton);
        registerButton.setOnClickListener(this);
        registerBackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                EditText registerUsername = findViewById(R.id.registerUsername);
                EditText registerPassword = findViewById(R.id.registerPassword);
                EditText registerEmail = findViewById(R.id.registerEmail);
                RequestQueue queue = Volley.newRequestQueue(this);
                String domain = getResources().getString(R.string.domain);
                String url = domain+"/users/register";
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("username", registerUsername.getText());
                    requestBody.put("password", registerPassword.getText());
                    requestBody.put("email", registerEmail.getText());

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url, requestBody, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Register success", Toast.LENGTH_SHORT);
                                    toast.show();
                                    finish();
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
            case R.id.registerBackButton:
                finish();
                break;
        }
    }
}
