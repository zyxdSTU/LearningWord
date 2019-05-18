package fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import activity.R;
import adapter.ChapterAdapter;
import adapter.PartAdapter;
import db.DataBaseUtil;
import db.LearningWordDatabaseHelper;
import javabean.Chapter;
import javabean.Part;

public class PartFragment extends Fragment {
    private Context mContext;
    private List<Part> mList;
    private ShowFragment showFragment;

    SQLiteDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item, container, false);

        mContext = this.getContext();


        db = new LearningWordDatabaseHelper(mContext, "LearningWord.db", null,1).getWritableDatabase();

        mList = DataBaseUtil.selectAllPart(db);

        showFragment = (ShowFragment) this.getParentFragment();

        PartAdapter adapter = new PartAdapter(mContext, R.layout.item, mList);

        ListView listView = (ListView) view.findViewById(R.id.listView);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Part part = mList.get(position);

                showFragment.goChapterFragment(part.getPart());
            }
        });
        return view;
    }
}
