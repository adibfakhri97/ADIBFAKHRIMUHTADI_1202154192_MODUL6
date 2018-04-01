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
public class tab_terbaru extends Fragment {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter ;
    ProgressDialog progressDialog;
    List<methodUploadInfo> list = new ArrayList<>();


//    public tab_terbaru() {
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_main, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Memuat data...");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference("ADIB");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    methodUploadInfo imageUploadInfo = postSnapshot.getValue(methodUploadInfo.class);
                    list.add(imageUploadInfo);
                }

                adapter = new Adapter2(getContext(), list);
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
