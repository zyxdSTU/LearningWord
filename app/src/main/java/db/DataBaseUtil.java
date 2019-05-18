package db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javabean.Chapter;
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
        ContentValues values = new ContentValues();
        values.put("Word", word.getWord());
        values.put("WordTranslation", word.getWordTranslation());
        values.put("Chapter", word.getChapter());
        values.put("ChapterName", word.getChapterName());
        values.put("Example",  word.getExample());
        values.put("ExampleTranslation", word.getExampleTranslation());
        db.insert("table_word", null, values);
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
        if(list.size() == 0) return null;
        else return list;
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
        if (list.size() == 0) return null;
        else return list;
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
        if(list.size() == 0) return null;
        else return list;
    }
}
