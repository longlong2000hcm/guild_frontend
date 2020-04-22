package project.guild;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditJobView extends AppCompatActivity {

    String idUser;
    List<Job> jobList;
    ListView listViewCJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job_view);
        SharedPreferences prefs = this.getSharedPreferences(
                "Guild", Context.MODE_PRIVATE);
        idUser = prefs.getString("Guild.idUser", "");

        jobList = new ArrayList<>();
        listViewCJ = (ListView) findViewById(R.id.listViewCJ);

        RequestQueue queue = Volley.newRequestQueue(this);
        String domain = getResources().getString(R.string.domain);
        String url = domain + "/api/jobs/ownerID/"+idUser;
        Log.i("mylog", url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jobs = response.getJSONArray("jobs");

                    for (int i = 0; i < jobs.length(); i++) {
                        JSONObject job = jobs.getJSONObject(i);
                        String title = job.getString("title");
                        String description = job.getString("description");
                        String phone = job.getString("phone");
                        String id = job.getString("_id");

                        JSONObject salaryObj = job.getJSONObject("salary");
                        String salary = salaryObj.getString("$numberDecimal");

                        JSONArray locationArr = job.getJSONArray("location");
                        String location = locationArr.getString(0);

                        jobList.add(new Job(title, description, location, phone, salary, id));
                    }
                    Log.i("mylog","request completed");
                    JobListAdapter adapter = new JobListAdapter(EditJobView.this, R.layout.jobs, jobList);
                    listViewCJ.setAdapter(adapter);
                    Log.i("mylog","set adapter");
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
        });

        queue.add(jsonObjectRequest);

        Button back_button = findViewById(R.id.backBtn);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        listViewCJ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent jobIntent = new Intent(getApplicationContext(), EditingView.class);
                jobIntent.putExtra("JOB", (Serializable) jobList.get(position));
                startActivity(jobIntent);
                finish();
            }
        });

    }
}
