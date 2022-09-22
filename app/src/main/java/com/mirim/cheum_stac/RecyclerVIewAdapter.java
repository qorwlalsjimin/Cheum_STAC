package com.mirim.cheum_stac;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mirim.cheum_stac.Product.fillProduct;

import java.util.ArrayList;

//리사이클러뷰 어댑터
public class RecyclerVIewAdapter extends RecyclerView.Adapter<RecyclerVIewAdapter.MyViewHolder>{

    Context context;
    ArrayList<fillProduct> list;

    // 리스너 객체 참조를 저장하는 변수
    private static OnItemClickListener mListener = null;

    // OnItemClickListener 객체 참조를 어댑터에 전달하는 메서드
    public static void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

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
                    int pos = getAdapterPosition();
                    // 리스너 객체의 메서드 호출
                    if (pos != RecyclerView.NO_POSITION)
                    {

                        mListener.onItemClick(view, pos);
                    }
                }
            });
        }
    }

}
