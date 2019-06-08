package activity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
//import android.util.Base64;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.util.Base64;
import java.util.List;

import adapter.InstanceAdapter;
import db.DataBaseUtil;
import db.LearningWordDatabaseHelper;
import javabean.Instance;
import javabean.Word;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import util.PreferenceManager;
import util.Util;

public class WordActivity extends AppCompatActivity  {

    private SQLiteDatabase db;

    private ListView  instanceListView;

    private InstanceAdapter instanceAdapter;

    private TextView wordTextVew;
    private TextView wordTranslationTextView;
    private ImageButton wordSoundImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_word);

        final Word word = (Word)getIntent().getSerializableExtra("word");

        db = new LearningWordDatabaseHelper(getApplicationContext(), "LearningWord.db", null,1).getWritableDatabase();

        wordTextVew = findViewById(R.id.word_textView);
        wordTranslationTextView = findViewById(R.id.word_translation_textView);
        wordSoundImageButton = findViewById(R.id.word_sound_imageButton);

        wordTextVew.setText(word.getWord());
        wordTranslationTextView.setText(word.getWordTranslation());

        final List<Instance> instanceList = DataBaseUtil.selectInstance(db, word);

        instanceListView = findViewById(R.id.instance_list_view);

        instanceAdapter = new InstanceAdapter(this,R.layout.item_sentence, instanceList);

        instanceListView.setAdapter(instanceAdapter);

        wordSoundImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound(word.getWord());
            }
        });

        instanceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Instance instance = instanceList.get(position);
                String url = instance.getExample();
                sound(url);
            }
        });
    }


    public void sound(final String word) {
        try {
            if (PreferenceManager.getInstance().preferenceManagerGet(word) != "") {
                Log.d("Debug", "存储发音");
                String value = PreferenceManager.getInstance().preferenceManagerGet(word);
                byte[] mp3Byte = Base64.decode(value, Base64.DEFAULT);
                File tempMp3 = File.createTempFile("temp", "mp3", Environment.getExternalStorageDirectory());
                tempMp3.deleteOnExit();
                FileOutputStream fos = new FileOutputStream(tempMp3); fos.write(mp3Byte); fos.close();
                MediaPlayer mediaPlayer = new MediaPlayer();
                FileInputStream fis = new FileInputStream(tempMp3);
                mediaPlayer.setDataSource(fis.getFD());
                mediaPlayer.prepare();
                mediaPlayer.start();
            }else {
                String address = "http://dict.youdao.com/dictvoice?audio=" + word;
                Log.d("Debug", "网络获取发音");
                Util.sendOkHttpRequest(address, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Log.d("Debug", "网络获取发音失败");
                                Toast.makeText(WordActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        byte[] mp3Byte = response.body().bytes();
                        String value = Base64.encodeToString(mp3Byte, Base64.DEFAULT);
                        PreferenceManager.getInstance().preferenceManagerSave(word, value);
                        File tempMp3 = File.createTempFile("temp", "mp3", Environment.getExternalStorageDirectory());
                        tempMp3.deleteOnExit();
                        FileOutputStream fos = new FileOutputStream(tempMp3); fos.write(mp3Byte); fos.close();
                        MediaPlayer mediaPlayer = new MediaPlayer();
                        FileInputStream fis = new FileInputStream(tempMp3);
                        mediaPlayer.setDataSource(fis.getFD());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    }
                });
            }
        }catch(Exception e) {
            Log.d("Debug", e.getMessage());
        }
    }
}
