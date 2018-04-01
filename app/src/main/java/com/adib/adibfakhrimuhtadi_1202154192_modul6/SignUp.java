package com.adib.adibfakhrimuhtadi_1202154192_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText emailDaftar, passwordDaftar; //Menambahkan variable EditText
    private FirebaseAuth mAuth; //Menambahkan variable FirebaseAuth
    String email, password; //Menambahkan variable string
    Button btnSignUp; //Menambahkan variable button
    Boolean EditTextEmptyCheck; //Menambahkan variable Checker
    ProgressDialog progressDialog; //Menambahkan variable progressdialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();  //instance firebase untuk SignUp
        emailDaftar = (EditText)findViewById(R.id.emailDaftar);  //inisiasi EditText
        passwordDaftar = (EditText)findViewById(R.id.passwordDaftar); //inisiasi EditText
        btnSignUp = (Button)findViewById(R.id.btnSignUp) ; //inisiasi Button
        progressDialog =  new ProgressDialog(SignUp.this); //inisiasi progressdialog


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckEditTextIsEmptyOrNot();
                if(EditTextEmptyCheck)
                {
                    methodDaftar();
                }
                else {

                    Toast.makeText(SignUp.this, "Please Fill All the Fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void methodDaftar(){ // Creating methodDaftar
        progressDialog.setMessage("Mendaftar..."); //pesan progressdialog
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {  //pembuatan user baru

                        if(task.isSuccessful()){ //jika berhasil
                            Toast.makeText(SignUp.this,"Pendaftaran berhasil",Toast.LENGTH_LONG).show();

                            mAuth.signOut();
                            Intent i = new Intent(SignUp.this,Login.class);
                            startActivity(i);
                            finish();

                        }else{  //gagal
                            Toast.makeText(SignUp.this,"periksa koneksi anda",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();

                    }
                });

    }

    public void CheckEditTextIsEmptyOrNot(){
        email = emailDaftar.getText().toString().trim();
        password = passwordDaftar.getText().toString().trim();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            EditTextEmptyCheck = false;

        }
        else {

            EditTextEmptyCheck = true ;

        }

    }
}
