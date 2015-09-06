package com.example.xmlexample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onWriteButton(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void onReadButton(View v) {
        Intent i = new Intent(this, ReadActivity.class);
        startActivity(i);
    }


}
