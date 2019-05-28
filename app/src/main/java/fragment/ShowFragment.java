package fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activity.R;
import adapter.PartAdapter;
import db.DataBaseUtil;
import db.LearningWordDatabaseHelper;
import javabean.Chapter;
import javabean.Part;
import javabean.Word;
import util.Util;

public class ShowFragment extends Fragment {
    private Context mContext;

    private int flag = 0;
    private String part = null;
    private String chapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        if(part != null) Log.d("Debug", "OnCreateView" + part);

        View view = inflater.inflate(R.layout.fragment_show, container, false);

        if(flag == 0) goPartFragment();

        else if(flag == 1) goChapterFragment(part);

        else goWordFragment(part, chapter);

        mContext = this.getContext();

        return view;
    }

    public int getFlag() {return flag;}


    public void goPartFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();

        FragmentTransaction  transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.frame_layout, new PartFragment());

        transaction.commit();

        changePart();

    }

    public void goChapterFragment(String part) {
        if(part == null) part = this.part;

        FragmentManager fragmentManager = getChildFragmentManager();

        FragmentTransaction  transaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle(); bundle.putString("Part", part);

        ChapterFragment chapterFragment = new ChapterFragment(); chapterFragment.setArguments(bundle);

        transaction.replace(R.id.frame_layout, chapterFragment);

        transaction.commit();

        changeChapter(part);
    }

    public void goWordFragment(String part, String chapter) {
        if(part == null) part = this.part;
        if(chapter == null) chapter = this.chapter;

        FragmentManager fragmentManager = getChildFragmentManager();

        FragmentTransaction  transaction = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle(); bundle.putString("Part", part); bundle.putString("Chapter", chapter);

        WordFragment wordFragment = new WordFragment(); wordFragment.setArguments(bundle);

        transaction.replace(R.id.frame_layout, wordFragment);

        transaction.commit();

        changeWord(part, chapter);
    }

    public void changePart() {
        flag = 0;
        this.part = null; this.chapter = null;
    }

    public void changeChapter(String part) {
        flag = 1;
        this.part = part; this.chapter = null;
    }

    public void changeWord(String part, String chapter) {
        flag = 2;
        this.part = part; this.chapter = chapter;
    }


    public void tempOneDispose() {
        SQLiteOpenHelper dbHelper = new LearningWordDatabaseHelper(mContext, "LearningWord.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String element = "";
            List<String> lines = Util.read("PartAndChapter.csv");
            for(int i = 0; i < lines.size(); i++) {
                if(i == 0) continue;
                element = lines.get(i).replaceAll("\r|\n",  "");
                String [] strArr = element.split(",");

                //Part,PartName,Chapter,ChapterName
                String Part = strArr[0]; String PartName = strArr[1];
                String Chapter = strArr[2]; String ChapterName = strArr[3];
                Log.d("Debug", Part);
                Log.d("Debug", PartName);
                Log.d("Debug", Chapter);
                Log.d("Debug", ChapterName);
                if (DataBaseUtil.selectPart(db, Part) == null) DataBaseUtil.insertPart(db, Part, PartName);
                if (DataBaseUtil.selectChapter(db, Part, Chapter) == null) DataBaseUtil.insertChapter(db, Chapter, ChapterName,Part);
            }
        }catch (Exception e) {
            Log.d("Debug", e.getMessage());
        }

    }
    public void tempTwoDispose() {
        SQLiteOpenHelper dbHelper = new LearningWordDatabaseHelper(mContext, "LearningWord.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String element = "";
            List<String> lines = Util.read("test.txt");
            for(int i = 0; i < lines.size(); i++) {
                element = lines.get(i).replaceAll("\r|\n",  "");
                String [] strArr = element.split(",");


                String Part = strArr[0]; String Chapter = strArr[1];
                String Word = strArr[2]; String WordTranslation = strArr[3];
                String Example = strArr[4]; String ExampleTranslation = strArr[5];
                Log.d("Debug", Part);
                Log.d("Debug", Chapter);
                Log.d("Debug", Word);
                Log.d("Debug", WordTranslation);
                Log.d("Debug", Example);
                Log.d("Debug", ExampleTranslation);

                String chapterName = DataBaseUtil.selectChapter(db, Part, Chapter);
                Word word = new Word();
                word.setWord(Word); word.setWordTranslation(WordTranslation);
                word.setChapter(Chapter); word.setChapterName(chapterName);
                word.setExample(Example); word.setExampleTranslation(ExampleTranslation);

                DataBaseUtil.insertWord(db, word);
            }
        }catch (Exception e) {
            Log.d("Debug", e.getMessage());
        }

    }
}
