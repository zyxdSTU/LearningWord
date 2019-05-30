package db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javabean.Chapter;
import javabean.Instance;
import javabean.Part;
import javabean.Word;

public class DataBaseUtil {
    public static void insertPart(SQLiteDatabase db, String Part, String PartName) {
        ContentValues values = new ContentValues();
        values.put("Part", Part); values.put("PartName", PartName);
        db.insert("table_part", null, values);
    }

    public static void insertChapter(SQLiteDatabase db, String Chapter, String ChapterName, String Part) {
        ContentValues values = new ContentValues();
        values.put("Chapter", Chapter);
        values.put("ChapterName", ChapterName);
        values.put("Part", Part);
        db.insert("table_chapter", null, values);
    }


    public static void insertWord(SQLiteDatabase db, Word word) {
        String insertSentence = "insert or ignore into table_word values(?,?,?,?)";
        db.execSQL(insertSentence,new String[]{word.getWord(), word.getWordTranslation(),
        word.getChapter(), word.getChapterName()});
    }

    public static void insertInstance(SQLiteDatabase db, Instance instance) {
        String insertSentence = "insert or ignore into table_instance values(?,?,?,?,?,?)";
        db.execSQL(insertSentence,new String[]{instance.getExample(), instance.getExampleTranslation(),
                instance.getChapter(), instance.getChapterName(), instance.getWord(), instance.getWordTranslation()});
    }

    public static String join(String delimit, List<String> strList) {
        StringBuilder strBuilder = new StringBuilder();
        for(String str : strList) {
            strBuilder.append(str);
            strBuilder.append(delimit);
        }
        return strBuilder.substring(0, strBuilder.length()-1);
    }

    public static String selectPart(SQLiteDatabase db, String Part) {
        String PartName = "";
        Cursor cursor = db.query("table_part",null,"Part = ?",new String[]{Part},null,null,null);
        if(cursor.moveToFirst()) {
            do{
                PartName = cursor.getString(cursor.getColumnIndex("PartName"));
            }while(cursor.moveToNext());
        }
        if (PartName == "") return null;
        else return PartName;
    }

    public static List<Part> selectAllPart(SQLiteDatabase db) {
        Cursor cursor = db.query("table_part",null,null,null,null,null,null);
        List<Part> list = new ArrayList<Part>();

        if(cursor.moveToFirst()) {
            do{
                String Part = cursor.getString(cursor.getColumnIndex("Part"));
                String PartName = cursor.getString(cursor.getColumnIndex("PartName"));
                list.add(new Part(Part, PartName));
            }while(cursor.moveToNext());
        }
        return list;
    }

    public static String selectChapter(SQLiteDatabase db, String Part, String Chapter) {
        String ChapterName = "";
        Cursor cursor = db.query("table_chapter",null,"Part = ? and Chapter = ?",new String[]{Part, Chapter},null,null,null);
        if(cursor.moveToFirst()) {
            do{
                ChapterName = cursor.getString(cursor.getColumnIndex("ChapterName"));
            }while(cursor.moveToNext());
        }
        if (ChapterName == "") return null;
        else return ChapterName;
    }

    public static List<Chapter> selectChapterList(SQLiteDatabase db, String Part) {
        List<Chapter> list = new ArrayList<Chapter>();
        Cursor cursor = db.query("table_chapter",null,"Part = ?",new String[]{Part},null,null,null);
        if(cursor.moveToFirst()) {
            do{
                String Chapter = cursor.getString(cursor.getColumnIndex("Chapter"));
                String ChapterName = cursor.getString(cursor.getColumnIndex("ChapterName"));
                list.add(new Chapter(Chapter, ChapterName, Part));
            }while(cursor.moveToNext());
        }
        return list;
    }

    public static List<Chapter> selectAllChapter(SQLiteDatabase db) {
        Cursor cursor = db.query("table_chapter",null,null,null,null,null,null);
        List<Chapter> list = new ArrayList<Chapter>();

        if(cursor.moveToFirst()) {
            do{
                String Chapter = cursor.getString(cursor.getColumnIndex("Chapter"));
                String ChapterName = cursor.getString(cursor.getColumnIndex("ChapterName"));
                String Part = cursor.getString(cursor.getColumnIndex("Part"));
                list.add(new Chapter(Chapter, ChapterName, Part));
            }while(cursor.moveToNext());
        }
        Log.d("Debug", String.valueOf(list.size()));
        return list;
    }

    public static List<Word> selectPartChapterWord(SQLiteDatabase db, String Part, String Chapter) {
        String ChapterName = DataBaseUtil.selectChapter(db, Part, Chapter);
        Cursor cursor = db.query("table_word", null,"Chapter = ? and ChapterName = ?",new String[]{Chapter, ChapterName},null,null,null);
        List<Word> list = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do{
                String Word = cursor.getString(cursor.getColumnIndex("Word"));
                String WordTranslation = cursor.getString(cursor.getColumnIndex("WordTranslation"));
                Word word = new Word();
                word.setChapter(Chapter); word.setChapterName(ChapterName);
                word.setWord(Word); word.setWordTranslation(WordTranslation);
                list.add(word);
            }while(cursor.moveToNext());
        }
         return list;
    }

    public static List<Instance> selectInstance(SQLiteDatabase db, Word word) {
        String chapter = word.getChapter(); String chapterName = word.getChapterName();
        String wordS = word.getWord();   String wordTranslation = word.getWordTranslation();

        Cursor cursor = db.query("table_instance", null,"Chapter = ? and ChapterName = ? and Word = ? and WordTranslation = ?",
                new String[]{chapter, chapterName, wordS, wordTranslation},null,null,null);
        List<Instance> list = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do{
                String example = cursor.getString(cursor.getColumnIndex("Example"));
                String exampleTranslation = cursor.getString(cursor.getColumnIndex("ExampleTranslation"));
                Instance instance = new Instance();
                instance.setExample(example); instance.setExampleTranslation(exampleTranslation);
                instance.setChapter(chapter); instance.setChapterName(chapterName);
                instance.setWord(wordS); instance.setWordTranslation(wordTranslation);
                list.add(instance);
            }while(cursor.moveToNext());
        }
         return list;
    }
}
