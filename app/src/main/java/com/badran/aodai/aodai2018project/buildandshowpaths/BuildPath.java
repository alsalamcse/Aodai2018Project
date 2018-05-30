package com.badran.aodai.aodai2018project.buildandshowpaths;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.badran.aodai.aodai2018project.R;

public class BuildPath extends AppCompatActivity  {
    private ImageView mNamePath;
    private ImageView mCamera;
    private ImageView mMapView;
    private TextView mCountryname;
    private TextView mCamera1;
    private EditText mPathName;
    private Button BTNsavepath;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_path);
        mNamePath = (ImageView) findViewById(R.id.ic_namepath);
        mCamera = (ImageView) findViewById(R.id.ic_camera);
        mMapView = (ImageView) findViewById(R.id.map_view);
        mCountryname = (TextView) findViewById(R.id.TVcountryname);
        mCamera1 = (TextView) findViewById(R.id.TVcamera);
        mPathName = (EditText) findViewById(R.id.etpathname);
        BTNsavepath = (Button) findViewById(R.id.BTNsavepath);
        imageView = (ImageView) findViewById(R.id.imageView);


        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,0);
            }
        });

        hideSoftKeyboard();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }

    public void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
