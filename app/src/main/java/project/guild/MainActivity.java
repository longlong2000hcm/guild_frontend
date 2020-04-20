package project.guild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button BtnAddView;
    private Button BtnEditJob;

    List<Job> jobList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);
        String domain = getResources().getString(R.string.domain);
        String url = domain + "/api/jobs";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jobs = response.getJSONArray("jobs");

                    for (int i = 0; i < jobs.length(); i++) {
                        JSONObject job = jobs.getJSONObject(i);

                        String title = job.getString("title");
                        String description = job.getString("description");

                        JSONArray locationArr = job.getJSONArray("location");
                        String location = locationArr.getString(0);

                        jobList.add(new Job(title, description, location));
                    }
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

        BtnEditJob = findViewById(R.id.BtnEditJob);
        BtnEditJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToactivity_edit_job_view();
            }
        });

        BtnAddView = findViewById(R.id.BtnAddJob);

        jobList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);

        jobList.add(new Job("Babysitting", "I want you to watch the kids for me, cause I'm so very tired.","Oulu"));
        jobList.add(new Job("Walk the dogs", "Come walk my dogs bro I'm so tired.","Helsinki"));

        BtnAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToactivity_add_job_view();
            }
        });

        JobListAdapter adapter = new JobListAdapter(this, R.layout.jobs, jobList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent jobIntent = new Intent(getApplicationContext(), DetailedJobView.class);
                jobIntent.putExtra("JOB", (Serializable) jobList.get(position));
                startActivity(jobIntent);
            }
        });
    }

    private void moveToactivity_add_job_view(){
        Intent intent = new Intent(MainActivity.this, AddJobView.class);
        startActivity(intent);
    }

    private void moveToactivity_edit_job_view(){
        Intent intent = new Intent(MainActivity.this, EditJobView.class);
        startActivity(intent);
    }
}
