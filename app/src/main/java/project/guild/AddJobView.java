package project.guild;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddJobView extends AppCompatActivity {
    private CheckBox mCheckbox;
    private Button mButton;
    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_view);
        SharedPreferences prefs = this.getSharedPreferences(
                "Guild", Context.MODE_PRIVATE);
        idUser = prefs.getString("Guild.idUser", "");

        Button back_button = findViewById(R.id.BtnClose);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
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
                            EditText title = findViewById(R.id.EditTitle);
                            EditText description = findViewById(R.id.JobDescriptionText);
                            EditText location = findViewById(R.id.ContactLocation);
                            EditText salary = findViewById(R.id.SalaryOffer);
                            EditText phone = findViewById(R.id.ContactPhoneNr);

                            RequestQueue queue = Volley.newRequestQueue(getBaseContext());
                            String domain = getResources().getString(R.string.domain);
                            String url = domain+"/api/jobs";
                            JSONObject requestBody = new JSONObject();
                            try {
                                requestBody.put("title", title.getText());
                                requestBody.put("description", description.getText());
                                requestBody.put("location", location.getText());
                                requestBody.put("salary", salary.getText());
                                requestBody.put("phone", phone.getText());
                                requestBody.put("ownerID", idUser);
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                    (Request.Method.POST, url, requestBody, new Response.Listener<JSONObject>() {

                                        @Override
                                        public void onResponse(JSONObject response) {

                                            Toast toast = Toast.makeText(getApplicationContext(), "Create job success", Toast.LENGTH_SHORT);
                                            toast.show();
                                            Intent intent = new Intent(getApplicationContext(), EditJobView.class);
                                            startActivity(intent);
                                            finish();

                                        }
                                    }, new Response.ErrorListener() {

                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            // TODO: Handle error
                                            Toast toast = Toast.makeText(getApplicationContext(), "Create job failed. Error: "+error.toString(), Toast.LENGTH_LONG);
                                            toast.show();
                                        }
                                    }) {
                                /** Passing some request headers* */
                                @Override
                                public Map getHeaders() throws AuthFailureError {
                                    HashMap headers = new HashMap();
                                    headers.put("Content-Type", "application/json");
                                    return headers;
                                }
                            };

                            queue.add(jsonObjectRequest);
                        }
                    });
                }else{
                    mButton.setEnabled(false);
                }
            }
        });


    }
}
