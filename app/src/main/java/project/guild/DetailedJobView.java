package project.guild;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailedJobView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_job_view);

        // get data from previous activity
        final Intent intent = getIntent();
        final Job job = (Job) intent.getSerializableExtra("JOB");

        // set up variables for each item
        String title = job.title;
        String description = job.description;
        String location = job.location;

        // set title
        TextView title_text = findViewById(R.id.title);
        title_text.setText(title);

        // set onclick for back button
        Button back_button = findViewById(R.id.back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
