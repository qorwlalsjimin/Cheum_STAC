package com.mirim.cheum_stac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mirim.cheum_stac.Product.fillProduct;

import java.util.ArrayList;

//리사이클러뷰 어댑터
public class RecyclerVIewAdapter extends RecyclerView.Adapter<RecyclerVIewAdapter.MyViewHolder>{

    Context context;
    ArrayList<fillProduct> list;

    public RecyclerVIewAdapter(Context context, ArrayList<fillProduct> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.image.setImageResource(list.get(position).img);
        holder.best.setText(list.get(position).best);
        holder.name.setText(list.get(position).name);
        holder.price.setText(list.get(position).price);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_fill_recycler, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView best;
        public TextView name;
        public TextView price;




        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.news1);
            name = itemView.findViewById(R.id.news1_2);
            best = itemView.findViewById(R.id.news1_1);
            price = itemView.findViewById(R.id.news1_3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fill_product.newInstance();
                }
            });
        }
    }

}
