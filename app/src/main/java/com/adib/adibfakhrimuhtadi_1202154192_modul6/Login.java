package com.adib.adibfakhrimuhtadi_1202154192_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText emailLogin, passwordLogin;
    private FirebaseAuth mAuth;  //variable untuk FirebaseAuth
    String email, password; //variable untuk String
    Button btnLogin; //variable untuk Button
    Boolean EditTextEmptyCheck; //variable untuk checker
    ProgressDialog progressDialog; //variable untuk progress dialog


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAuth = FirebaseAuth.getInstance(); //instance firebase untuk RecyclerViewLayout
        emailLogin = (EditText)findViewById(R.id.email);  //inisiasi inputan Email
        passwordLogin = (EditText)findViewById(R.id.pass); //inisiasi inputan Password
        btnLogin = (Button)findViewById(R.id.login) ; //inisiasi button Login


        progressDialog =  new ProgressDialog(Login.this);  //menambahkan progressdialog untuk activity ini

        if(mAuth.getCurrentUser() != null){  //memeriksa apakah sudah ada user yang login pada perangkat ini
            finish();
            Intent intent = new Intent(Login.this, tabLayout.class);
            startActivity(intent);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calling method CheckEditTextIsEmptyOrNot().
                CheckEditTextIsEmptyOrNot();

                // If  EditTextEmptyCheck == true
                if(EditTextEmptyCheck)
                {

                    // If  EditTextEmptyCheck == true then login function called.
                    methodLogin();

                }
                else {

                    // If  EditTextEmptyCheck == false then toast display on screen.
                    Toast.makeText(Login.this, "Please Fill All the Fields", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    public void CheckEditTextIsEmptyOrNot(){

        // Getting value form Email's EditText and fill into EmailHolder string variable.
        email = emailLogin.getText().toString().trim();

        // Getting value form Password's EditText and fill into PasswordHolder string variable.
        password = passwordLogin.getText().toString().trim();

        // Checking Both EditText is empty or not.
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {

            // If any of EditText is empty then set value as false.
            EditTextEmptyCheck = false;

        }
        else {

            // If any of EditText is empty then set value as true.
            EditTextEmptyCheck = true ;

        }

    }

    public void methodLogin(){
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            finish();
                            Intent intent = new Intent(Login.this, tabLayout.class);
                            startActivity(intent);
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Email or Password Not found, Please Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public void login(View view) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("as", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("as", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }

    public void signup(View view) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }
}
