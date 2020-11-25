package com.example.projetoids.Classes;

public class PostExp {



    String foto_usuario;
    String n_usuario;
    String cont_texto;
    String cont_foto;

    public PostExp(String foto_usuario, String n_usuario, String cont_texto, String cont_foto) {
        this.foto_usuario = foto_usuario;
        this.n_usuario = n_usuario;
        this.cont_texto = cont_texto;
        this.cont_foto = cont_foto;
    }

    public PostExp() {

    }

    public void setFoto_usuario(String foto_usuario) {
        this.foto_usuario = foto_usuario;
    }

    public void setN_usuario(String n_usuario) {
        this.n_usuario = n_usuario;
    }

    public String getFoto_usuario() {
        return foto_usuario;
    }

    public String getN_usuario() {
        return n_usuario;
    }

    public String getCont_texto() {
        return cont_texto;
    }

    public void setCont_texto(String cont_texto) {
        this.cont_texto = cont_texto;
    }

    public String getCont_foto() {
        return cont_foto;
    }

    public void setCont_foto(String cont_foto) {
        this.cont_foto = cont_foto;
    }

}
