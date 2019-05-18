package fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.List;
import activity.R;
import adapter.ChapterAdapter;
import db.DataBaseUtil;
import db.LearningWordDatabaseHelper;
import javabean.Chapter;

public class ChapterFragment extends Fragment {
    private Context mContext;
    private List<Chapter> mList;
    private SQLiteDatabase db;

    private ShowFragment showFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        String part = bundle.getString("Part");

        View view = inflater.inflate(R.layout.fragment_item, container, false);

        mContext = this.getContext();

        db = new LearningWordDatabaseHelper(mContext, "LearningWord.db", null,1).getWritableDatabase();

        showFragment = (ShowFragment) this.getParentFragment();

        showFragment.change(part);

        mList = DataBaseUtil.selectChapterList(db, part);

        ChapterAdapter adapter = new ChapterAdapter(mContext, R.layout.item, mList);

        ListView listView = (ListView) view.findViewById(R.id.listView);

        listView.setAdapter(adapter);

        return view;
    }
}
