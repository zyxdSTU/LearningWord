package com.example.administrator.learningword.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.learningword.R;

import java.util.ArrayList;

import adapter.FragmentAdapter;
import fragment.IOFragment;
import fragment.SearchFragment;
import fragment.ShowFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton showButton;
    private ImageButton searchButton;
    private ImageButton ioButton;

    private TextView showTextView;
    private TextView searchTextView;
    private TextView ioTextView;

    private ViewPager viewPager;

    private ArrayList<Fragment> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showButton = (ImageButton) findViewById(R.id.show_button);
        searchButton = (ImageButton) findViewById(R.id.search_button);
        ioButton = (ImageButton) findViewById(R.id.io_button);

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        showTextView = (TextView) findViewById(R.id.show_textView);
        searchTextView = (TextView) findViewById(R.id.search_textView);
        ioTextView = (TextView) findViewById(R.id.io_textView);

        mList = new ArrayList<>();
        mList.add(new ShowFragment());
        mList.add(new SearchFragment());
        mList.add(new IOFragment());

        showButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        ioButton.setOnClickListener(this);

        showButton.setSelected(true);
        showTextView.setTextColor(Color.parseColor("#4CAF50"));

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mList);
        viewPager.setAdapter(fragmentAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id) {
            case R.id.show_button:
                changeTab(0); break;
            case R.id.search_button:
                changeTab(1); break;
            case R.id.io_button:
                changeTab(2); break;
            default: break;
        }
    }

    private void changeTab(int position) {
        showButton.setSelected(false);
        showTextView.setTextColor(Color.parseColor("#757575"));
        searchButton.setSelected(false);
        searchTextView.setTextColor(Color.parseColor("#757575"));
        ioButton.setSelected(false);
        ioTextView.setTextColor(Color.parseColor("#757575"));

        switch(position) {
            case 0:
                showButton.setSelected(true);
                showTextView.setTextColor(Color.parseColor("#4CAF50"));
                break;
            case 1:
                searchButton.setSelected(true);
                searchTextView.setTextColor(Color.parseColor("#4CAF50"));
                break;
            case 2:
                ioButton.setSelected(true);
                ioTextView.setTextColor(Color.parseColor("#4CAF50"));
                break;
            default:
                break;
        }
        viewPager.setCurrentItem(position);
    }
}
