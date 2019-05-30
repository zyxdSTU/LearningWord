package activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

import db.LearningWordDatabaseHelper;
import javabean.Word;

public class WordActivity extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Word word = (Word)getIntent().getSerializableExtra("word");

        db = new LearningWordDatabaseHelper(getApplicationContext(), "LearningWord.db", null,1).getWritableDatabase();


    }

    @Override
    public void onClick(View v) {

    }
}
