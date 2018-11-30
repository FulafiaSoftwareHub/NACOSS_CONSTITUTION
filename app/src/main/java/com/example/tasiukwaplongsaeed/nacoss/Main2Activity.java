package com.example.tasiukwaplongsaeed.nacoss;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main2Activity extends AppCompatActivity {
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String section = getIntent().getStringExtra("section");

        //getActionBar().setDisplayHomeAsUpEnabled(true);
        AssetManager assetManager = getAssets();
        InputStream input;
        String selectedSection = getIntent().getStringExtra("section");

        try{
            input = assetManager.open("section1.html");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            //byte buffer string
            String text = new String(buffer);
            mTextMessage.setText(text);
        }catch (IOException e){
            mTextMessage.setText("Sorry, an error occured");
            e.printStackTrace();
        }

      //  mTextMessage = (TextView) findViewById(R.id.constitution);
       // mTextMessage.setText(getIntent().getStringExtra("section"));

    }
}
