package com.example.livraria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class Listar extends AppCompatActivity {
    ListView lista;
    ArrayAdapter<String> adapter;
    ArrayList<String> listview = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);
        BancoDados banco = new BancoDados(this);
        lista = (ListView) findViewById(R.id.lista);
        List<Livros> listaDeLivros = banco.listaTodosLivros();
        for (Livros l:listaDeLivros){
            String item = "ID:" + l.id + "\n" +
                          "TÍTULO:" + l.titulo + "\n" +
                          "EDITORA:" + l.editora + "\n" +
                          "ANO:" + l.ano + "\n" +
                          "AUTOR:" + l.autor + "\n";
            listview.add(item);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listview);
        lista.setAdapter(adapter);

    }
}












