package com.adib.adibfakhrimuhtadi_1202154192_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewLayout extends AppCompatActivity {
    FirebaseAuth firebaseAuth ; //Menambahkan variable FirebaseAuth
    DatabaseReference databaseReference; //Menambahkan variable DatabaseReference
    RecyclerView recyclerView; //membuat variable recyclerView
    RecyclerView.Adapter adapter ; //menambahkan variable dari adapter RecyclerView
    ProgressDialog progressDialog; //Menambahkan variable Progress Dialog
    List<methodUploadInfo> listImageUploadInfo = new ArrayList<>(); //menambahkan variable listImageUploadInfo dari class methodUploadInfo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance(); //instance firebase untuk RecyclerViewLayout

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab); //FAB
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecyclerViewLayout.this, TambahGambar.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView); //inisiasi RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewLayout.this)); //membuat recyclerView menggunakan layout Linear
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); //membuat recyclerview berbentuk grid 2

        progressDialog = new ProgressDialog(RecyclerViewLayout.this); //menambahkan progress dialog ke RecyclerViewLayout
        progressDialog.setMessage("Memuat data dari server..."); //pesan ketika progressdialog muncul
        progressDialog.show(); //method untuk menampilkan progress dialog

        databaseReference = FirebaseDatabase.getInstance().getReference(TambahGambar.Database_Path); //membuat folder pada storage firebase untuk menyimpan file gambar


        databaseReference.addValueEventListener(new ValueEventListener() {  //listener upload
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    methodUploadInfo imageUploadInfo = postSnapshot.getValue(methodUploadInfo.class);

                    listImageUploadInfo.add(imageUploadInfo);
                }

                adapter = new Adapter2(RecyclerViewLayout.this, listImageUploadInfo);

                recyclerView.setAdapter(adapter);

                // Hiding the progress dialog.
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
                progressDialog.dismiss();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            // Destroying login season.
                    firebaseAuth.signOut();

            // Finishing current User Profile activity.
            finish();

            // Redirect to btnLogin Activity after click on logout button.
            Intent intent = new Intent(RecyclerViewLayout.this, Login.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
