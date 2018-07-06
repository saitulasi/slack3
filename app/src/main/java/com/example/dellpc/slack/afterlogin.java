package com.example.dellpc.slack;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class afterlogin extends AppCompatActivity {

    private TextView ptitle1,pverify1,pname1,pmailid1,pbldgrp1,pcontact1;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterverify);
        ptitle1=findViewById(R.id.ptitle);
        pverify1=findViewById(R.id.pverify);
        pname1=findViewById(R.id.pname);
        pmailid1=findViewById(R.id.pmailid);
        pbldgrp1=findViewById(R.id.pbldgrp);
        pcontact1=findViewById(R.id.pcontact);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
                pname1.setText(userProfile.getUser_name());
                pbldgrp1.setText(userProfile.getUser_bldgrp());
                pcontact1.setText(userProfile.getUser_con());
                pmailid1.setText(userProfile.getUser_email());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(afterlogin.this,databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
