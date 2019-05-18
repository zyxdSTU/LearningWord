package adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import activity.R;
import javabean.Part;

public class PartAdapter extends ArrayAdapter<Part> {

    private int resourceId;

    public PartAdapter(Context context, int textViewResourceId, List<Part> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Part part = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("  " +part.getPart() + "     " + part.getPartName());
        return view;
    }
}