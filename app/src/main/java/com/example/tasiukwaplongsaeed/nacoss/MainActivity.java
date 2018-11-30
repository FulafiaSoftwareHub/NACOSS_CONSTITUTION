package com.example.tasiukwaplongsaeed.nacoss;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            LinearLayout sectionsBtn = (LinearLayout) findViewById(R.id.sections);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText("");
                    //Makes the sections buttons visible
                    sectionsBtn.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    //handles event of about buton being clicked
                    //hides sections buttons first
                    sectionsBtn.setVisibility(View.GONE);
                    mTextMessage.setText("");
                    InputStream inputStream = getResources().openRawResource(R.raw.about);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                    int i;
                    try {
                        i = inputStream.read();
                        while(i != -1){
                            //provided the file get read
                            byteArrayOutputStream.write(i);
                            i = inputStream.read();
                        }
                        inputStream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        mTextMessage.setText(Html.fromHtml(byteArrayOutputStream.toString(), Html.FROM_HTML_MODE_COMPACT));
                    }else{
                        mTextMessage.setText(Html.fromHtml(byteArrayOutputStream.toString()));
                    }
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText("");
                    mTextMessage.setText(R.string.title_notifications);
                    //hides sections buttons first
                    sectionsBtn.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void viewSection(View view) {
       // Toast.makeText(this, "Hello you clicked section"+view.getResources().getResourceEntryName(view.getId()), Toast.LENGTH_SHORT).show();
        String section = view.getResources().getResourceEntryName(view.getId());
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        intent.putExtra("section", section);
        startActivity(intent);
    }
}
