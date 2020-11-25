package com.example.projetoids;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoids.Classes.PostExp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExpAdapter extends RecyclerView.Adapter<ExpAdapter.ViewHolder> {

    private static final String Tag="RecyclerView";
    private Context eContext;
    private ArrayList<PostExp> postExpList;

    public ExpAdapter(Context eContext, ArrayList<PostExp> postExpList) {
        this.eContext = eContext;
        this.postExpList = postExpList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgUser;
        TextView txtUser;
        TextView texPost;
        ImageView imgPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.foto_up);
            txtUser= itemView.findViewById(R.id.nome_up);
            texPost=itemView.findViewById(R.id.txt_conteudo);
            imgPost=itemView.findViewById(R.id.img_post);
        }
    }

    @NonNull
    @Override
    public ExpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experiencia_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(postExpList.get(position).getFoto_usuario()).into(holder.imgUser);
        holder.txtUser.setText(postExpList.get(position).getN_usuario());
        holder.texPost.setText(postExpList.get(position).getCont_texto());
        Picasso.get().load(postExpList.get(position).getCont_foto()).into(holder.imgPost);
    }

    @Override
    public int getItemCount() {
        return postExpList.size();
    }
}
