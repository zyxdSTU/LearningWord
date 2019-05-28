package fragment;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import activity.R;
import activity.WordActivity;
import adapter.WordAdapter;
import db.DataBaseUtil;
import db.LearningWordDatabaseHelper;
import javabean.Word;

public class WordFragment extends Fragment {
    private Context mContext;
    private List<Word> mList;
    private SQLiteDatabase db;

    private ShowFragment showFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        String part = bundle.getString("Part");

        String chapter = bundle.getString("Chapter");

        View view = inflater.inflate(R.layout.fragment_item, container, false);

        mContext = this.getContext();

        db = new LearningWordDatabaseHelper(mContext, "LearningWord.db", null,1).getWritableDatabase();

        showFragment = (ShowFragment) this.getParentFragment();

        //showFragment.changeChapter(part, chapter);

        mList = DataBaseUtil.selectPartChapterWord(db, part, chapter);

        WordAdapter adapter = new WordAdapter(mContext, R.layout.item, mList);

        ListView listView = (ListView) view.findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word = mList.get(position);

                Intent intent  = new Intent(getActivity(), WordActivity.class);

                startActivity(intent);
            }
        });

        return view;
    }
}
