package com.badran.aodai.aodai2018project.buildandshowpaths;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;

import com.badran.aodai.aodai2018project.R;

public class ListofPaths extends AppCompatActivity {

    private ListView Lstv1;
    private AutoCompleteTextView mSearchPaths;
    private ImageView mPhotoSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_paths);

        Lstv1 = (ListView) findViewById(R.id.Lstv1);
        mSearchPaths = (AutoCompleteTextView) findViewById(R.id.searchpaths);
        mPhotoSearch = (ImageView) findViewById(R.id.searchimgview);

        hideSoftKeyboard();

    }

    public void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}