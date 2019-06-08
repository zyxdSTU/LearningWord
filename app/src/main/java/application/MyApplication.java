package application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

import activity.R;
import db.DataBaseUtil;
import db.LearningWordDatabaseHelper;
import javabean.Instance;
import javabean.Word;
import util.PreferenceManager;
import util.Util;

public class MyApplication extends Application {
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        tempOneDispose();
        tempTwoDispose();
        PreferenceManager.init(mContext);
    }

    public void tempOneDispose() {
        SQLiteOpenHelper dbHelper = new LearningWordDatabaseHelper(mContext, "LearningWord.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            String element = "";
            List<String> lines = Util.readInternal(getResources().openRawResource(R.raw.partchapter));
            for(int i = 0; i < lines.size(); i++) {
                if(i == 0) continue;
                element = lines.get(i).replaceAll("\r|\n",  "");
                String [] strArr = element.split("\t");

                //Part,PartName,Chapter,ChapterName
                String Part = strArr[0]; String PartName = strArr[1];
                String Chapter = strArr[2]; String ChapterName = strArr[3];
                //Log.d("Debug", Part);
                //Log.d("Debug", PartName);
                //Log.d("Debug", Chapter);
                //Log.d("Debug", ChapterName);
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
            List<String> lines = Util.readInternal(getResources().openRawResource(R.raw.word));
            for(int i = 0; i < lines.size(); i++) {
                element = lines.get(i).replaceAll("\r|\n",  "");
                String [] strArr = element.split("\t");


                String Part = strArr[0]; String Chapter = strArr[1];
                String Word = strArr[2]; String WordTranslation = strArr[3];
                String Example = strArr[4]; String ExampleTranslation = strArr[5];
                //Log.d("Debug", Part);
                //Log.d("Debug", Chapter);
                //Log.d("Debug", Word);
                //Log.d("Debug", WordTranslation);
                //Log.d("Debug", Example);
                //Log.d("Debug", ExampleTranslation);

                String chapterName = DataBaseUtil.selectChapter(db, Part, Chapter);
                javabean.Word word = new Word();
                word.setWord(Word); word.setWordTranslation(WordTranslation);
                word.setChapter(Chapter); word.setChapterName(chapterName);
                DataBaseUtil.insertWord(db, word);

                Instance instance = new Instance(word);
                instance.setExample(Example); instance.setExampleTranslation(ExampleTranslation);

                DataBaseUtil.insertInstance(db, instance);
            }
        }catch (Exception e) {
            Log.d("Debug", e.getMessage());
        }
    }
}
