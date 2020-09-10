package com.example.projetoids;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConquistasAdapter extends RecyclerView.Adapter <ConquistasAdapter.ConquistasViewHolder>{
    public ArrayList<ConquistaItem> mConquistasList;

    public static class ConquistasViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public ConquistasViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView= itemView.findViewById(R.id.imageView);
            mTextView1=itemView.findViewById(R.id.textView);
            mTextView2=itemView.findViewById(R.id.textView2);
        }
    }

    public ConquistasAdapter(ArrayList<ConquistaItem> exampleList){
        mConquistasList = exampleList;
    }

    @NonNull
    @Override
    public ConquistasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.conquista_item,parent, false);
        ConquistasViewHolder evh = new ConquistasViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ConquistasViewHolder holder, int position) {
        ConquistaItem currentItem = mConquistasList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getmText1());
        holder.mTextView2.setText(currentItem.getmText2());
    }

    @Override
    public int getItemCount() {
        return mConquistasList.size();
    }
}
