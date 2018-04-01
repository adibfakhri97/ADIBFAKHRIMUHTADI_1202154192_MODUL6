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

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {
    String infoEmail2;
    String url;
    Context context;
    List<methodUploadInfo> listUploadImageInfo2;

    public Adapter2(Context context, List<methodUploadInfo> TempList) { //konstruktor untuk class Adapter2
        this.listUploadImageInfo2 = TempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        methodUploadInfo UploadInfo = listUploadImageInfo2.get(position);
        holder.tUser.setText(UploadInfo.getUserImage());
        holder.imageNameTextView.setText(UploadInfo.getImageName());
        url = UploadInfo.getImageURL();
        infoEmail2 = UploadInfo.getUserImage();

        Glide.with(context).load(url).into(holder.imageView); //loading gambar dari library glide
    }


    @Override
    public int getItemCount() {

        return listUploadImageInfo2.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tUser;
        public ImageView imageView;
        public TextView imageNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tUser = (TextView) itemView.findViewById(R.id.user1);
            imageNameTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();

            String element = listUploadImageInfo2.get(mPosition).toString();
            String uri = listUploadImageInfo2.get(mPosition).getImageURL();
            String title = listUploadImageInfo2.get(mPosition).getImageName();
            Intent i = new Intent(view.getContext(), DetailGambar.class);
            i.putExtra("infoEmail", infoEmail2);
            i.putExtra("url",uri);
            i.putExtra("nama",title);
            view.getContext().startActivity(i);


        }
    }
}