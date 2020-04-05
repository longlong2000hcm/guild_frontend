package project.guild;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Job> jobList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Job> jobList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);


        jobList.add(new Job("Babysitting", "description","Oulu"));
        jobList.add(new Job("Walk the dogs", "description","Helsinki"));

        JobListAdapter adapter = new JobListAdapter(this, R.layout.jobs, jobList);

        listView.setAdapter(adapter);}}
