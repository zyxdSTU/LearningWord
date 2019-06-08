package fragment;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import activity.R;
import activity.WordActivity;
import adapter.WordAdapter;
import db.DataBaseUtil;
import db.LearningWordDatabaseHelper;
import javabean.Instance;
import javabean.Word;
import util.Util;


public class SearchFragment extends Fragment {
    private ListView listView;
    private EditText editText;
    private ImageButton imageButton;
    private ImageButton outputButton;
    private WordAdapter adapter;
    private Context mContext;
    private ArrayList<Word> mList;
    private SQLiteDatabase db;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        mContext = getContext();
        mList = new ArrayList<>();
        listView = view.findViewById(R.id.result_list_View);
        editText = view.findViewById(R.id.edit_text);
        imageButton = view.findViewById(R.id.search_button);
        outputButton = view.findViewById(R.id.output_image_button);
        db = new LearningWordDatabaseHelper(mContext, "LearningWord.db", null,1).getWritableDatabase();
        adapter = new WordAdapter(mContext, R.layout.item, mList);
        listView.setAdapter(adapter);

        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Debug", "search click");
                String input = editText.getText().toString();
                Log.d("Debug", input);
                if(input.equals("")) Toast.makeText(mContext, "输入不能为空", Toast.LENGTH_SHORT);
                List<Word> word = DataBaseUtil.selectWordByWord(db, input);
                List<Word> chapter = DataBaseUtil.selectWordByChapter(db, input);
                List<Word> wordTranslation = DataBaseUtil.selectWordByWordTranslation(db, input);

                mList.clear(); mList.addAll(word); mList.addAll(chapter); mList.addAll(wordTranslation);

                Log.d("Debug", String.valueOf(mList.size()));

                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word = mList.get(position);

                Intent intent  = new Intent(getActivity(), WordActivity.class);

                intent.putExtra("word", word);

                startActivity(intent);
            }
        });


        outputButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mList.size() != 0) {
                    String path = Environment.getExternalStorageDirectory().getPath() + "/" + editText.getText().toString() + ".txt";
                    List<Instance> instances = new ArrayList<>();
                    List<String> lines = new ArrayList<>();
                    for(Word word : mList) {
                        instances.addAll(DataBaseUtil.selectInstance(db, word));
                    }

                    for(Instance instance : instances) {
                        String ChapterName = instance.getChapterName(); String Chapter = instance.getChapter();
                        String Part = DataBaseUtil.selectPartByChapter(db, Chapter, ChapterName);
                        List<String> temp = new ArrayList<>();
                        temp.add(Part); temp.add(Chapter); temp.add(instance.getWord());
                        temp.add(instance.getWordTranslation()); temp.add(instance.getExample());
                        temp.add(instance.getExampleTranslation());
                        String line = TextUtils.join("\t", temp);
                        Log.d("Debug", line);
                        lines.add(line);
                    }
                    try {
                        Util.write(editText.getText().toString() + ".txt", lines);
                    }catch (Exception e) {
                        Log.d("Debug", e.getLocalizedMessage());
                        e.printStackTrace();
                    }
                    Toast.makeText(getContext(), "查询结果已存入" + path, Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getContext(), "查询结果为空", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
