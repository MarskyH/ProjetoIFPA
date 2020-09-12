package com.example.livraria;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

// todas essas classes vão ser utilizadas
public class Consultar extends AppCompatActivity {
    EditText busca_id; // declaração do editText
    TextView mostraConteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar);
        mostraConteudo = findViewById(R.id.mostraConteudo);
        busca_id = findViewById(R.id.busca_id);
    }

    public void buscar(View view) {
        if (this.busca_id.length() != 0) {
            BancoDados banco = new BancoDados(this);
            int i = Integer.parseInt(busca_id.getText().toString());
            Livros livros = banco.consultaLivro(i);
            mostraConteudo.setText("ID:" + livros.id + "\n" +
                    "TÍTULO:" + livros.titulo + "\n" +
                    "EDITORA:" + livros.editora + "\n" +
                    "ANO:" + livros.ano + "\n" +
                    "AUTOR:" + livros.autor + "\n");
            busca_id.setText("");
        } else {
            Toast.makeText(this, "Insira um valor no campo acima", Toast.LENGTH_LONG).show();
        }


    }
}






//  Toast.makeText(this, "ID NÃO EXISTE!", Toast.LENGTH_LONG).show();mostraConteudo.setText(livros.toString());//desse jeito um endereço é exibido