package project.guild;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditJobView extends AppCompatActivity {

    List<Job> jobList;
    ListView listViewCJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job_view);

        Button back_button = findViewById(R.id.backBtn);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        jobList = new ArrayList<>();
        listViewCJ = (ListView) findViewById(R.id.listViewCJ);

        jobList.add(new Job("Babysitting", "I want you to watch the kids for me, cause I'm so god damn fed up with these little cunts I can't take it anymore","Oulu"));
        jobList.add(new Job("Walk the dogs", "Come walk my dogs bro I'm so tired aaaa aaaaa aaaa aaa aaaaa aaa aaaaaa aaaaaaa aaaa aaaa aa aaaaaa aaaaa aaaaaaa aaaa  aaaaaaa aaaaa aaaaa aa aaaaa aaaaa aa aaa aaaa aaa aaa aaaa aaaa aaa  aa a a","Helsinki"));

        JobListAdapter adapter = new JobListAdapter(this, R.layout.jobs, jobList);

        listViewCJ.setAdapter(adapter);

        listViewCJ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent jobIntent = new Intent(getApplicationContext(), DetailedJobView.class);
                jobIntent.putExtra("JOB", (Serializable) jobList.get(position));
                startActivity(jobIntent);
            }
        });

    }
}
