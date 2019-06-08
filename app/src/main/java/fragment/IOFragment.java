package fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;

import java.io.File;
import java.util.List;

import activity.R;
import db.DataBaseUtil;
import db.LearningWordDatabaseHelper;
import javabean.Instance;
import javabean.Word;
import util.Util;

import static android.app.Activity.RESULT_OK;

public class IOFragment extends Fragment {
    private ImageButton imageButton;
    private SQLiteDatabase db;
    int REQUESTCODE_FROM_ACTIVITY = 1000;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_io, container, false);

        imageButton = view.findViewById(R.id.input_image_button);

        SQLiteOpenHelper dbHelper = new LearningWordDatabaseHelper(getContext(), "LearningWord.db", null, 1);
        db = dbHelper.getWritableDatabase();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogProperties properties = new DialogProperties();

                properties.selection_mode = DialogConfigs.SINGLE_MODE;
                properties.selection_type = DialogConfigs.FILE_SELECT;
                properties.root = new File(DialogConfigs.DEFAULT_DIR);
                properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
                properties.offset = new File(DialogConfigs.DEFAULT_DIR);
                properties.extensions = null;

                FilePickerDialog dialog = new FilePickerDialog(getActivity(),properties);
                dialog.setTitle("Select a File");
                dialog.show();

                Log.d("Debug", Environment.getExternalStorageDirectory().getAbsolutePath());

                dialog.setDialogSelectionListener(new DialogSelectionListener() {

                    @Override
                    public void onSelectedFilePaths(String[] files) {
                        try {
                            String element = "";
                            for (String fileName : files) {
                                Log.d("Debug", fileName);
                                List<String> lines = Util.read(fileName);
                                for(int i = 0; i < lines.size(); i++) {
                                    element = lines.get(i).replaceAll("\r|\n", "");
                                    String[] strArr = element.split("\t");
                                    String Part = strArr[0];
                                    String Chapter = strArr[1];
                                    String Word = strArr[2];
                                    String WordTranslation = strArr[3];
                                    String Example = strArr[4];
                                    String ExampleTranslation = strArr[5];

                                    //Log.d("Debug", ExampleTranslation);

                                    String chapterName = DataBaseUtil.selectChapter(db, Part, Chapter);
                                    javabean.Word word = new Word();
                                    word.setWord(Word);
                                    word.setWordTranslation(WordTranslation);
                                    word.setChapter(Chapter);
                                    word.setChapterName(chapterName);
                                    DataBaseUtil.insertWord(db, word);

                                    Instance instance = new Instance(word);
                                    instance.setExample(Example);
                                    instance.setExampleTranslation(ExampleTranslation);

                                    DataBaseUtil.insertInstance(db, instance);
                                }
                            }

                        }catch(Exception e) {
                            Log.d("Debug", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        return view;
    }
}
