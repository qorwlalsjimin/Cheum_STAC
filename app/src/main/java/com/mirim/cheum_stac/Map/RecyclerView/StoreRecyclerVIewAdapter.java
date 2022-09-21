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
public class StoreRecyclerVIewAdapter extends RecyclerView.Adapter<StoreRecyclerVIewAdapter.ViewHolder>{

    Context context;
    ArrayList<fillStore> list; //localDataSet

    //온클릭 이벤트 구현
    public interface OnItemClickListener{
        void onItemClicked(int position, String data);
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }

    //뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView storeName; //textView
        public ViewHolder(View itemView) {
            super(itemView);
            storeName = itemView.findViewById(R.id.text_store_name);
        }
    }

    //생성자
    public StoreRecyclerVIewAdapter(Context context, ArrayList<fillStore> list) {
        super();
        this.context = context;
        this.list = list;
    }

    //RecyclerView Adapter 필수 구현 항목
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "";
                int position = viewHolder.getAdapterPosition();
                if(position != RecyclerView.NO_POSITION)
                    data = viewHolder.storeName.getText().toString();
                itemClickListener.onItemClicked(position, data);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.storeName.setText(list.get(position).storeName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
