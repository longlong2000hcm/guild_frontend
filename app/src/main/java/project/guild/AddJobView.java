package project.guild;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.widget.TextView;

public class AddJobView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = findViewById(R.id.DisclaimerText);

                String text = "\nDisclaimer\nCreating this ad adds a small one-time fee." +
                        "This means that the business keeps up and the application is used seriously. By checking in you agree and approve these conditions.\n\n";

        SpannableString ss = new SpannableString(text);

        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);

        ss.setSpan(boldSpan, 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);

    }
}
