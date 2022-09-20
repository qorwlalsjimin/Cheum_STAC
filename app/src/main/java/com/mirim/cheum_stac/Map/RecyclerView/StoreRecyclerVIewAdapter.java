package com.mirim.cheum_stac.Map.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mirim.cheum_stac.R;

import java.util.ArrayList;

//리사이클러뷰 어댑터
public class StoreRecyclerVIewAdapter extends RecyclerView.Adapter<StoreRecyclerVIewAdapter.MyViewHolder>{

    Context context;
    ArrayList<fillStore> list;

    public StoreRecyclerVIewAdapter(Context context, ArrayList<fillStore> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.storeName.setText(list.get(position).storeName);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView storeName;

        public MyViewHolder(View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.text_store_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fill_store.newInstance();
                }
            });
        }
    }

}
