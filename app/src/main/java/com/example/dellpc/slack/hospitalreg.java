package com.example.dellpc.slack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hospitalreg extends AppCompatActivity {

    private EditText username, password, emailid, contact, addr, bloodgroup,pname;
    private Button signinbtn, signupbtn;
    String name1,pword,bldgrp,mailid,phone1;
    String MobilePattern = "[0-9]{10}";
    String grppattern="(A|B|AB|O)[-+]";
    private FirebaseAuth firebaseAuth;
     ProgressDialog progressDialog=new ProgressDialog(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        views();
        firebaseAuth=FirebaseAuth.getInstance();
        signupbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    if(password.getText().toString().length()!=6)
                    {
                        Toast.makeText(getApplicationContext(), "Should be 6 digits", Toast.LENGTH_LONG).show();
                        password.setError("Should be 6 digits");
                        return;
                    }

                    else if(!bloodgroup.getText().toString().matches(grppattern)) {
                        Toast.makeText(getApplicationContext(), "Please enter valid group", Toast.LENGTH_SHORT).show();
                        bloodgroup.setError("Incorrect Blood Group");
                        return;
                    }

                    else if(!contact.getText().toString().matches(MobilePattern)) {
                        Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
                        contact.setError("Incorrect Contact");
                        return;
                    }
                    else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailid.getText().toString()).matches()) {
                        // Validation for Invalid Email Address
                        Toast.makeText(getApplicationContext(),"Invalid Email", Toast.LENGTH_LONG).show();
                        emailid.setError("Invalid Email");
                        return;
                    }
                    else{

                    String user_email=emailid.getText().toString().trim();
                    String user_pass=password.getText().toString().trim();
                        progressDialog.setMessage("Please wait");
                        progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_pass).addOnCompleteListener(
                            new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    if (task.isSuccessful()) {
                                        uploaddata();
                                        Toast.makeText(hospitalreg.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        sendEmail();
                                        startActivity(new Intent(hospitalreg.this,login.class));
                                    }
                                    else
                                    {
                                        Toast.makeText(hospitalreg.this, "Account already exists", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(hospitalreg.this,login.class));
                                    }
                                }
                            });

                    /*else
                    {
                        Toast.makeText(hospitalreg.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(hospitalreg.this, login.class));
                    }*/}
                }
            }});

        signinbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(hospitalreg.this, login.class));
            }
        });

    }
    private void views() {
        username = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.pass);
        emailid = (EditText) findViewById(R.id.email);
        contact = (EditText) findViewById(R.id.inchargecontact);
        signinbtn = (Button) findViewById(R.id.signin);
        signupbtn = (Button) findViewById(R.id.signup);
        addr = (EditText) findViewById(R.id.address);
        bloodgroup = (EditText) findViewById(R.id.avgblood);
        pname = (EditText) findViewById(R.id.person);
    }


    private boolean validate() {
        Boolean value = false;
         name1 = username.getText().toString();
         pword = password.getText().toString();
         mailid = emailid.getText().toString();
         phone1 = contact.getText().toString();
        String addre1 = addr.getText().toString();
         bldgrp = bloodgroup.getText().toString();
        String per = pname.getText().toString();

        if (name1.isEmpty() || pword.isEmpty() || mailid.isEmpty() || phone1.isEmpty() || addre1.isEmpty() || bldgrp.isEmpty() || per.isEmpty())
        {
            Toast.makeText(this, "Enter all values", Toast.LENGTH_SHORT).show();
        }
        else
        {
            value = true;
        }
        return value;
    }
    private void sendEmail()
    {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(hospitalreg.this, "Verification mail sent", Toast.LENGTH_SHORT).show();
                        finish();
                       // startActivity(new Intent(hospitalreg.this,login.class));
                    }
                    else
                    {
                        Toast.makeText(hospitalreg.this, "Verification mail not sent", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    private void uploaddata()
    {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myref=firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile=new UserProfile(name1,mailid,bldgrp,phone1);
        myref.setValue(userProfile);
    }
}
