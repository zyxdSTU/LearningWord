package db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import javabean.Word;

public class DataBaseUtil {
    public static void insertPart(SQLiteDatabase db, String partName) {
        ContentValues values = new ContentValues();
        values.put("partName", partName);
        db.insert("Part", null, values);
    }

    public static void insertChapter(SQLiteDatabase db, String chapterName, String partName) {
        ContentValues values = new ContentValues();
        values.put("chapterName", chapterName);
        values.put("partName", partName);
        db.insert("Chapter", null, values);
    }

    public static void insertScene(SQLiteDatabase db, String sceneName, String chapterName) {
        ContentValues values = new ContentValues();
        values.put("sceneName", sceneName);
        values.put("chapterName", chapterName);
        db.insert("Scene", null, values);
    }

    public static void insertWord(SQLiteDatabase db, Word word) {
        ContentValues values = new ContentValues();
        values.put("wordName", word.getWordName());
        values.put("sceneName", word.getSceneName());

        if(word.getPhaseName() != null) {
            values.put("phaseName", join(";", word.getPhaseName()));
            values.put("phaseChName", join(";", word.getPhaseChName()));
        }

        if(word.getSameWordName() != null)  values.put("sameWordName", join(";", word.getSameWordName()));

        if(word.getSentenceName() != null) {
            values.put("sentenceName", join(";", word.getSentenceName()));
            values.put("sentenceChName", join(";", word.getSentenceChName()));
        }

        db.insert("Word", null, values);
    }

    public static String join(String delimter, List<String> strList) {
        StringBuilder strBuilder = new StringBuilder();
        for(String str : strList) {
            strBuilder.append(str);
            strBuilder.append(delimter);
        }
        return strBuilder.substring(0, strBuilder.length()-1);
    }
}
