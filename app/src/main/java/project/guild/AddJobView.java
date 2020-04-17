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
    private Button mCloseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_view);

        Button back_button = findViewById(R.id.BtnClose);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        

        TextView textView = findViewById(R.id.DisclaimerText);

                String text = "\nDisclaimer\nSubmitting your job offer adds a small one-time fee. To keep this service clean and serious. (For now its free) You can only proceed by checking in the box before you can submit.\n";

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
