package project.guild;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class AddJobView extends AppCompatActivity {
    private CheckBox mCheckbox;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_view);




        TextView textView = findViewById(R.id.DisclaimerText);

                String text = "\nDisclaimer\nCreating this ad adds a small one-time fee." +
                        "This means that the business keeps up and the application is used seriously. By checking in you agree and approve these conditions.\n\n";

        SpannableString ss = new SpannableString(text);

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

        ss.setSpan(boldSpan, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);


        final Button mButton=(Button)findViewById( R.id.BtnSubmitJob);
        CheckBox mCheckBox= ( CheckBox ) findViewById( R.id.DisclaimerCB);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    mButton.setEnabled(true);
                    mButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            moveToactivity_add_job_view();
                        }
                    });
                }else{
                    mButton.setEnabled(false);
                }
            }
        });


    }

    private void moveToactivity_add_job_view(){
        Intent intent = new Intent(AddJobView.this, MainActivity.class);
        startActivity(intent);
    }

}
