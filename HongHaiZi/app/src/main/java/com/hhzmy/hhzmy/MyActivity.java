package com.hhzmy.hhzmy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ehhzmy.hhzmy.R;
import com.nostra13.universalimageloader.core.ImageLoader;


public class MyActivity extends AppCompatActivity {

    private ImageView touxi;
    private TextView shouji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Intent intent = getIntent();
        String tu = intent.getStringExtra("tu");
        String name = intent.getStringExtra("name");
        touxi = (ImageView) findViewById(R.id.touxiang);
        shouji = (TextView) findViewById(R.id.shoujihao);
        ImageLoader.getInstance().displayImage(tu,touxi);
        shouji.setText(name);
    }
}
