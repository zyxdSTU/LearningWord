package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import activity.R;
import javabean.Word;

public class WordAdapter extends ArrayAdapter<Word> {

    private int resourceId;

    public WordAdapter(Context context, int textViewResourceId, List<Word> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        //Log.d("Debug", String.valueOf(objects.size()));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.d("Debug", "getView: ");
        Word word = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("     " + word.getWord());
        return view;
    }

    @Override
    public int getCount() {
        //Log.d("Debug", "getCount: " + super.getCount());
        return super.getCount();
    }
}