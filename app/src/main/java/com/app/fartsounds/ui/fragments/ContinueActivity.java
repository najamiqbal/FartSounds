package com.app.fartsounds.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fartsounds.MainActivity;
import com.app.fartsounds.R;

public class ContinueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue);


        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.loadNativeAd);



        frameLayout = findViewById(R.id.loadNativeAd);


    }

    public void openHome(View view) {

        startActivity(new Intent(ContinueActivity.this, MainActivity.class));
        finish();

    }
}