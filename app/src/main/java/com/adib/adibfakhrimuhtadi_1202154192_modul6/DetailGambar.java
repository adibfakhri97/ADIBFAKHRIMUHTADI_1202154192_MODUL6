package com.adib.adibfakhrimuhtadi_1202154192_modul6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailGambar extends AppCompatActivity {
    ImageView imageView;  //variable ImageView
    TextView title, infoEmail; //variable TextView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gambar);
        title = (TextView) findViewById(R.id.detailName);  //inisiasi TextView
        infoEmail = (TextView) findViewById(R.id.infoEmail); //inisiasi TextView
        Intent i = getIntent();
        String url = i.getStringExtra("url");
        String title = i.getStringExtra("nama");
        String userr = i.getStringExtra("infoEmail");

        imageView = (ImageView) findViewById(R.id.infoGambar);
        infoEmail.setText(userr);
        this.title.setText(title);
        Glide.with(this).load(url).into(imageView);
    }

    public void comment(View view) {

    }
}
