package com.example.dellpc.slack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
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
        if(!isConnected(start.this))
        {
            buildDialog(start.this).show();
            return;
        }

        Toast.makeText(start.this,"Welcome to Slack", Toast.LENGTH_SHORT).show();
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
    }//}
    private void views()
    {
        donorbtn = (Button) findViewById(R.id.donor);
        hospbtn = (Button) findViewById(R.id.hospital);
    }

    private boolean isConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
        android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if( (mobile != null &&  mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
            {
                return true;
            }
        else {
            return false;
            }
    } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or Wifi to access this.Press OK to Exit");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;

    }
}

