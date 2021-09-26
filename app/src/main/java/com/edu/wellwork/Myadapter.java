package com.edu.wellwork;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyviewHolder> {

    Context context;

    ArrayList<ReviewDet> list;

    public Myadapter(Context context, ArrayList<ReviewDet> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.reviews,parent,false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        ReviewDet reviewDet = list.get(position);
        holder.UserName.setText(reviewDet.getUserName());
        holder.UserReview.setText(reviewDet.getUserReview());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView UserName, UserReview;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            UserName = itemView.findViewById(R.id.UserName);
            UserReview = itemView.findViewById(R.id.UserReview);
        }
    }
}
