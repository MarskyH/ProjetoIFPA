package com.example.livraria;

public class Livros {
    int id;
    String ano;
    String titulo;
    String editora;
    String autor;

    public Livros(String titulo, String editora, String ano, String autor) {
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
        this.autor = autor;
    }

    public Livros(int id, String titulo, String editora, String ano, String autor)
    {
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
        this.autor = autor;
    }

    public Livros() {

    }

    public int getid()
    {
        return id;
    }

    public void setid(int id)
    {
        this.id = id;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getEditora()
    {
        return editora;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
