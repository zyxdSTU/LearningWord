package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class LearningWordDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_PART = "create table table_part (Part text, PartName text, primary key(Part))";

    private static final String CREATE_CHAPTER = "create table table_chapter(Chapter text, ChapterName text," +
            "Part text, foreign key(Part) references table_part(Part) on delete cascade on update cascade," +
            "primary key(Chapter, ChapterName))";

    private static final String CREATE_WORD = "create table table_word(Word text, WordTranslation text," +
            "Chapter text, ChapterName text," +
            "foreign key(Chapter, ChapterName) references table_chapter(Chapter, ChapterName) on delete cascade on update cascade," +
            "primary key(Word, WordTranslation, Chapter, ChapterName))";

    private static final String CREATE_INSTANCE = "create table table_instance(Example text, ExampleTranslation text," +
            "Chapter text, ChapterName text, Word text, WordTranslation text," +
            "foreign key(Chapter, ChapterName, Word, WordTranslation) references table_word(Chapter, ChapterName, Word, WordTranslation) on delete cascade on update cascade," +
            "primary key(Example, ExampleTranslation, Chapter, ChapterName, Word, WordTranslation))";

    private Context mContext;

    public LearningWordDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PART);
        db.execSQL(CREATE_CHAPTER);
        db.execSQL(CREATE_WORD);
        db.execSQL(CREATE_INSTANCE);
        Toast.makeText(mContext, "数据库执行创建步骤", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
