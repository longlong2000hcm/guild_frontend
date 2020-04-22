package project.guild;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class EditingView extends AppCompatActivity implements View.OnClickListener {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_view);

        Button back_button = findViewById(R.id.backBtn);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditJobView.class);
                startActivity(intent);
                finish();
            }
        });

        final Intent intent = getIntent();
        final Job job = (Job) intent.getSerializableExtra("JOB");


        EditText editTitle = findViewById(R.id.EditTitle);
        EditText editDescription = findViewById(R.id.EditJobDescriptionText);
        EditText editLocation = findViewById(R.id.EditContactLocation);
        EditText editPhone = findViewById(R.id.EditContactPhoneNr);
        EditText editSalary = findViewById(R.id.EditSalaryOffer);

        editTitle.setText(job.title);
        editDescription.setText(job.description);
        editLocation.setText(job.location);
        editPhone.setText(job.phone);
        editSalary.setText(job.salary);
        id = job.id;

        Button saveBtn = findViewById(R.id.BtnSaveJob);
        Button deleteBtn = findViewById(R.id.BtnDelJob);
        saveBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        String domain = getResources().getString(R.string.domain);
        String url = domain + "/api/jobs/" + id;
        ;
        JSONObject requestBody = new JSONObject();
        JsonObjectRequest jsonObjectRequest;

        switch (v.getId()) {

            case R.id.BtnSaveJob:
                EditText editTitle = findViewById(R.id.EditTitle);
                EditText editDescription = findViewById(R.id.EditJobDescriptionText);
                EditText editLocation = findViewById(R.id.EditContactLocation);
                EditText editPhone = findViewById(R.id.EditContactPhoneNr);
                EditText editSalary = findViewById(R.id.EditSalaryOffer);

                try {
                    requestBody.put("title", editTitle.getText());
                    requestBody.put("description", editDescription.getText());
                    requestBody.put("location", editLocation.getText());
                    requestBody.put("salary", editSalary.getText());
                    requestBody.put("phone", editPhone.getText());
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.PUT, url, requestBody, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                Toast toast = Toast.makeText(getApplicationContext(), "Edit job success", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent intent = new Intent(getApplicationContext(), EditJobView.class);
                                startActivity(intent);
                                finish();

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                Toast toast = Toast.makeText(getApplicationContext(), "Edit job failed. Error: " + error.toString(), Toast.LENGTH_LONG);
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
            case R.id.BtnDelJob:
                jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                Toast toast = Toast.makeText(getApplicationContext(), "Delete job success", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent intent = new Intent(getApplicationContext(), EditJobView.class);
                                startActivity(intent);
                                finish();

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                Toast toast = Toast.makeText(getApplicationContext(), "Delete job failed. Error: " + error.toString(), Toast.LENGTH_LONG);
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
        }
    }
}
