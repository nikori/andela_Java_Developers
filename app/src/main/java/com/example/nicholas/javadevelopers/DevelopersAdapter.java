package com.example.nicholas.javadevelopers;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by kori on 2017-08-28.
 */

public class DevelopersAdapter extends RecyclerView.Adapter<DevelopersAdapter.MyViewHolder> {

    private Context mContext;
    private List<Developers> developersList;

    public DevelopersAdapter(Context mContext, List<Developers> developersList) {
        this.mContext = mContext;
        this.developersList = developersList;
    }

    @Override
    public DevelopersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developer_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DevelopersAdapter.MyViewHolder holder, int position) {

        Developers dev = developersList.get(position);
        holder.title.setText(dev.getLogin());
//        holder.count.setText(album.getNumOfSongs() + " songs");
        holder.mcd.setCardBackgroundColor(Color.parseColor("#DCDCDC"));
        String mainUrl=dev.getAvatar_url();

        // loading album cover using Glide library
        Glide.with(mContext).load(mainUrl).into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return developersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView thumbnail, overflow;
        public CardView mcd;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
//            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
//            overflow = (ImageView) view.findViewById(R.id.overflow);
            mcd=(CardView) view.findViewById(R.id.card_view);
        }

    }
}
