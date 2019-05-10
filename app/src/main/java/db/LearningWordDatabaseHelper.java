package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class LearningWordDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_PART = "create table Part (partName text, primary key(partName))";
    private static final String CREATE_CHAPTER = "create table Chapter(chapterName text, partName text," +
            "primary key(chapterName),"+
            "foreign key(partName) references Part(partName) on delete cascade on update cascade)";

    private static final String CREATE_SCENE = "create table Scene (sceneName text, chapterName text," +
            "primary key(sceneName)," +
            "foreign key(chapterName) references Chapter(chapterName) on delete cascade on update cascade)";

    private static final String CREATE_WORD = "create table Word(wordName text, sceneName text," +
            "phaseName text, phaseChName text," +
            "sameWordName text," +
            "sentenceName text, sentenceCHName text," +
            "primary key(wordName, sceneName)," +
            "foreign key(sceneName) references Scene(sceneName) on delete cascade on update cascade)";

    private Context mContext;

    public LearningWordDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PART);
        db.execSQL(CREATE_CHAPTER);
        db.execSQL(CREATE_SCENE);
        db.execSQL(CREATE_WORD);
        Toast.makeText(mContext, "数据库执行创建步骤", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
