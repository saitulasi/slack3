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

public class login extends AppCompatActivity {


    private EditText password, emailid;
    private Button signinbtn, signupbtn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        views();
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        FirebaseUser fuser=firebaseAuth.getCurrentUser();
      /*  if(fuser!=null)
        {
            finish();
            startActivity(new Intent(login.this,start.class));
        }*/

        views();
        signinbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (validate())
                {
                    if(password.getText().toString().length()!=6) {
                        Toast.makeText(getApplicationContext(), "Should be 6 digits", Toast.LENGTH_LONG).show();
                        password.setError("Should be 6 digits");
                        return;

                    }
                    else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailid.getText().toString()).matches()) {
                        // Validation for Invalid Email Address
                        Toast.makeText(getApplicationContext(),"Invalid Email", Toast.LENGTH_LONG).show();
                        emailid.setError("Invalid Email");
                        return;
                    }
                    progressDialog.setMessage("Please wait");
                    progressDialog.show();
                    firebaseAuth.signInWithEmailAndPassword(emailid.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                              //  startActivity(new Intent(login.this, login.class));
                                verification();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(login.this, "Login Failed.Invalid Email or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, start.class));

            }});
    }

    private void views() {
        password = (EditText) findViewById(R.id.pass);
        emailid = (EditText) findViewById(R.id.email);
        signinbtn = (Button) findViewById(R.id.signin);
        signupbtn = (Button) findViewById(R.id.signup);

    }


    private boolean validate() {
        Boolean value = false;
        String pword = password.getText().toString();
        String mailid = emailid.getText().toString();

        if ( pword.isEmpty() || mailid.isEmpty())
        {
            Toast.makeText(this, "Enter all values", Toast.LENGTH_SHORT).show();
        }
        else
        {
            value=true;
        }

        return value;
    }

    private void verification()
    {
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag=firebaseUser.isEmailVerified();
        if(emailflag)
        {
            Toast.makeText(login.this, "Email verified", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(login.this,afterverify.class));
        }
        else
        {
            Toast.makeText(login.this, "Verify your Email", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(login.this,afterlogin.class));
        }
    }
}
