package project.guild;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditingView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_view);

        Button back_button = findViewById(R.id.backBtn);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Intent intent = getIntent();
        final Job job = (Job) intent.getSerializableExtra("JOB");

        String title = job.title;
        String description = job.description;
        String location = job.location;
        String phone = job.phone;
        String salary = job.salary;
        String id = job.id;

        EditText editTitle = findViewById(R.id.EditTitle);
        EditText editDescription = findViewById(R.id.EditJobDescriptionText);
        EditText editLocation = findViewById(R.id.EditContactLocation);
        EditText editPhone = findViewById(R.id.EditContactPhoneNr);
        EditText editSalary = findViewById(R.id.EditSalaryOffer);

        editTitle.setText(title);
        editDescription.setText(description);
        editLocation.setText(location);
        editPhone.setText(phone);
        editSalary.setText(salary);
        Log.i("mylog", id);
    }
}
