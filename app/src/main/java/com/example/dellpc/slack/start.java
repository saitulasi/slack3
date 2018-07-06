package com.example.dellpc.slack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class start extends AppCompatActivity {

    private Button donorbtn, hospbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        views();
        hospbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(start.this, "Hospital Registration", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(start.this, hospitalreg.class));
            }
        });

        donorbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Toast.makeText(start.this, "Donor Registration", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(start.this, MainActivity.class));
            }
        });
    }
    private void views()
    {
        donorbtn = (Button) findViewById(R.id.donor);
        hospbtn = (Button) findViewById(R.id.hospital);
    }
}
