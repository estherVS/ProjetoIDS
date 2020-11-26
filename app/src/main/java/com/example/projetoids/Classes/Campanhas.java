package com.example.projetoids.Classes;

public class Campanhas {

    String nome;
    String imagemUrl;

    public Campanhas(){
    }

    public Campanhas(String nome, String imagemUrl) {
        this.nome = nome;
        this.imagemUrl = imagemUrl;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
