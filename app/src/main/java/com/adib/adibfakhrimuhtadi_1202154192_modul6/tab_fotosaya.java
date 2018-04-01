package com.adib.adibfakhrimuhtadi_1202154192_modul6;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class tab_fotosaya extends Fragment {

    DatabaseReference databaseReference; //Menambahkan variable DatabaseReference
    FirebaseAuth firebaseAuth ; //Menambahkan variable FirebaseAuth
    FirebaseUser firebaseUser; //Menambahkan variable FirebaseUser
    RecyclerView recyclerView; //Menambahkan variable recyclerview
    RecyclerView.Adapter adapter ; //Menambahkan variable dari adapter Recyclerview
    ProgressDialog progressDialog; //Menambahkan variable progressdialog
    List<methodAdapter1> listImageUploadInfo = new ArrayList<>(); //Menambahkan variable listImageUploadInfo dari class methodAdapter1

//    public tab_fotosaya() {
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_foto_saya, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView2);  //inisiasi RecyclerView

        firebaseAuth = FirebaseAuth.getInstance(); //instance firebase untuk tab_fotosaya
        firebaseUser = firebaseAuth.getCurrentUser();  //untuk mengambil user yang sedang login

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); //2 grid

        progressDialog = new ProgressDialog(getContext());  //progress dialog
        progressDialog.setMessage("Memuat data...");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference("ADIB");  //memasukkan data kedalam folder ADIB pada firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                listImageUploadInfo.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    methodAdapter1 feedData = postSnapshot.getValue(methodAdapter1.class);

                    String user = tabLayout.emailUser;
                    String data = String.valueOf(feedData.getUserImage());

                    if(data.equals(user)){
                        listImageUploadInfo.add(feedData);}
                }

                adapter = new Adapter1(getContext(), listImageUploadInfo);
                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();

            }
        });
        return rootView;
    }

}
