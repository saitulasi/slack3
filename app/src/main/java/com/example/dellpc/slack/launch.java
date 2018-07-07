package com.example.dellpc.slack;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.appus.splash.Splash;

public class launch extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch2);
        imageView=findViewById(R.id.imageView1);

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
        Splash.Builder splash = new Splash.Builder(this, getSupportActionBar());
        splash.perform();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(launch.this,start.class);
                launch.this.startActivity(mainIntent);
                launch.this.finish();
            }
        }, 3000);

    }

    }

