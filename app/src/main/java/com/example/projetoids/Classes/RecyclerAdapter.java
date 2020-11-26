package com.example.projetoids.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projetoids.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static  final  String Tag = "RecyclerView";
    private Context mContext;
    private ArrayList<Campanhas> listCampanhas;

    public RecyclerAdapter(Context mContext, ArrayList<Campanhas> listCampanhas) {
        this.mContext = mContext;
        this.listCampanhas = listCampanhas;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_listcampanhas, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtView.setText(listCampanhas.get(position).getNome());

        Glide.with(mContext)
                .load(listCampanhas.get(position).getImagemUrl())
                .into(holder.imageView);


    }


    @Override
    public int getItemCount() {
        return listCampanhas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //widgets
        ImageView imageView;
        TextView txtView;

        public  ViewHolder(@NonNull View itemView){
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtView = itemView.findViewById(R.id.txtView);
        }
    }

}
