package project.guild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
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

        BtnAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToactivity_add_job_view();
            }
        });

        jobList.add(new Job("Babysitting", "I want you to watch the kids for me, cause I'm so god damn fed up with these little cunts I can't take it anymore","Oulu"));
        jobList.add(new Job("Walk the dogs", "Come walk my dogs bro I'm so tired aaaa aaaaa aaaa aaa aaaaa aaa aaaaaa aaaaaaa aaaa aaaa aa aaaaaa aaaaa aaaaaaa aaaa  aaaaaaa aaaaa aaaaa aa aaaaa aaaaa aa aaa aaaa aaa aaa aaaa aaaa aaa  aa a a","Helsinki"));

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
