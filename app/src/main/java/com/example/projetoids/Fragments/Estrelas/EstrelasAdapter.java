package com.example.projetoids.Fragments.Estrelas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoids.R;

import java.util.ArrayList;
import java.util.List;

public class EstrelasAdapter extends RecyclerView.Adapter <EstrelasAdapter.EstrelasViewHolder>{
    public List<EstrelaItem> mConquistasList;

    public static class EstrelasViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public EstrelasViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView= itemView.findViewById(R.id.imageView);
            mTextView1=itemView.findViewById(R.id.textView);
            mTextView2=itemView.findViewById(R.id.textView2);
        }
    }

    public EstrelasAdapter(List<EstrelaItem> exampleList){
        mConquistasList = exampleList;
    }

    @NonNull
    @Override
    public EstrelasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.conquista_item,parent, false);
        return new EstrelasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EstrelasViewHolder holder, int position) {
        EstrelaItem currentItem = mConquistasList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getmText1());
        holder.mTextView2.setText(currentItem.getmText2());
    }

    @Override
    public int getItemCount() {
        return mConquistasList.size();
    }
}
