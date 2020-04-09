package project.guild;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class JobListAdapter extends ArrayAdapter<Job> {

    List<Job> jobList;
    Context context;
    int resource;

    public JobListAdapter(Context context, int resource, List<Job> jobList) {
        super(context, resource, jobList);
        this.context = context;
        this.resource = resource;
        this.jobList = jobList;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        TextView textViewText = view.findViewById(R.id.textViewText);
        TextView textViewLocation = view.findViewById(R.id.textViewLocation);



        Job job = jobList.get(position);

        //adding values to the list item
        textViewTitle.setText(job.getTitle());
        textViewText.setText(job.getDescription());
        textViewLocation.setText(job.getLocation());


        //finally returning the view
        return view;
    }
}

