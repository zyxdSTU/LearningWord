package fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import activity.R;
import db.DataBaseUtil;
import db.LearningWordDatabaseHelper;
import javabean.Word;

public class ShowFragment extends Fragment {
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);

        mContext = this.getContext();

        Button button = view.findViewById(R.id.test_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SQLiteOpenHelper dbHelper = new LearningWordDatabaseHelper(mContext, "LearningWord.db", null, 1);
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                //DataBaseUtil.insertPart(db, "PartI 生活常识与社交篇");
//                DataBaseUtil.insertChapter(db, "Chapter1 居家生活", "PartI 生活常识与社交篇");
//                DataBaseUtil.insertScene(db, "洗漱打扮", "Chapter1 居家生活");
//
//                Word word = new Word();
//                word.setWordName("brush");
//                word.setSceneName("洗漱打扮");
//                List<String> phaseName = new ArrayList<String>(); phaseName.add("brush one's teeth");
//                List<String> phaseChName = new ArrayList<String>(); phaseChName.add("刷牙");
//                word.setPhaseName(phaseName); word.setPhaseChName(phaseChName);
//
//                List<String> sentenceName = new ArrayList<String>();
//                sentenceName.add("Dentists recommend that people (should) brush their teeth after every meal");
//                List<String> sentenceChName = new ArrayList<String>();
//                sentenceChName.add("牙医建议民众每餐饭后必须刷牙");
//                word.setSentenceName(sentenceName); word.setSentenceChName(sentenceChName);
//
//                DataBaseUtil.insertWord(db, word);
            }
        });
        return view;
    }
}
