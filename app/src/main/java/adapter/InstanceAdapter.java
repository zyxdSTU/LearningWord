package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import activity.R;
import javabean.Instance;

public class InstanceAdapter extends ArrayAdapter<Instance> {
    private int resourceId;

    public InstanceAdapter(Context context, int textViewResourceId, List<Instance> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        //Log.d("Debug", String.valueOf(objects.size()));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.d("Debug", "getView: ");
        Instance instance = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        TextView exampleTextView = (TextView) view.findViewById(R.id.example_text_view);
        TextView exampleTranslationTextView = (TextView) view.findViewById(R.id.example_translation_text_view);
        exampleTextView.setText(instance.getExample());
        exampleTranslationTextView.setText(instance.getExampleTranslation());
        return view;
    }

    @Override
    public int getCount() {
        //Log.d("Debug", "getCount: " + super.getCount());
        return super.getCount();
    }
}