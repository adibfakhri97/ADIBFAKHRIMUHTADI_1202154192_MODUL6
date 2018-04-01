package com.adib.adibfakhrimuhtadi_1202154192_modul6;

/**
 * Created by adib on 1/04/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by AndroidJSon.com on 6/18/2017.
 */

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {
    String infoEmail;
    Context context;
    List<methodAdapter1> listUploadImageInfo;

    public Adapter1(Context context, List<methodAdapter1> TempList) {  //konstruktor untuk class Adapter1
        this.listUploadImageInfo = TempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) { //menyimpan hasil upload ke screen
        methodAdapter1 UploadInfo = listUploadImageInfo.get(position);
        holder.imageNameTextView.setText(UploadInfo.getImageName());
        holder.user.setText(tabLayout.emailUser);
        infoEmail = UploadInfo.getUserImage();
        Glide.with(context).load(UploadInfo.getImageURL()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listUploadImageInfo.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView user;
        public ImageView imageView;
        public TextView imageNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView1);
            user = (TextView) itemView.findViewById(R.id.user);
            imageNameTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            String element = listUploadImageInfo.get(mPosition).toString();
            String uri = listUploadImageInfo.get(mPosition).getImageURL();
            String nama = listUploadImageInfo.get(mPosition).getImageName();
            Intent i = new Intent(view.getContext(), DetailGambar.class);
            i.putExtra("infoEmail", infoEmail);
            i.putExtra("url",uri);
            i.putExtra("nama",nama);
            view.getContext().startActivity(i);
        }
    }
}
