package com.example.livraria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {
    private static final int VERSAO_BANCO_DADOS = 6;//para criar constantes:private static final, não dá para mudar.
    private static final String NOME_BANCO_DADOS = "gerenciaLivros";
    private static final String TABELA_LIVROS = "livros";
    private static final String CAMPO_ID = "id";
    private static final String CAMPO_TITULO = "nome";
    private static final String CAMPO_EDITORA = "editora";
    private static final String CAMPO_ANO = "ano";
    private static final String CAMPO_AUTOR = "autor";


    public BancoDados(Context context) {
        super(context, NOME_BANCO_DADOS, null, VERSAO_BANCO_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {
        banco.execSQL("create table "+TABELA_LIVROS +"("+CAMPO_ID+" integer primary key autoincrement,"+CAMPO_TITULO+" text,"+
                CAMPO_EDITORA+" text,"+CAMPO_ANO+" text,"+CAMPO_AUTOR+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int i, int i1)//atualizar o banco de dados
    {
        //"banco" objeto criando no começo, da classe SQLiteDataBase
        banco.execSQL("DROP TABLE IF EXISTS " + TABELA_LIVROS);//como se estivesse fazendo DROP TABLE IF EXISTS contatos
        onCreate(banco);
    }

    public void insereLivro(Livros livros) {
        SQLiteDatabase banco = this.getWritableDatabase();//é o que vai dar a permissão para gravar e ler. a informação não é gravada diretamente
        ContentValues values = new ContentValues();// limbo das informções. a classe possui varios metodos, como o put
        values.put(CAMPO_TITULO, livros.getTitulo());//uniao dos dois mundos
        values.put(CAMPO_EDITORA, livros.getEditora());//onde quer colocar. o que vai ser colocado
        values.put(CAMPO_ANO, livros.getAno());
        values.put(CAMPO_AUTOR, livros.getAutor());
        banco.insert(TABELA_LIVROS, null, values);//metodo insert. agora de fato vai gravar os dados na tabela
        banco.close();//fechar o canal de gravação e impedir o banco de ser corrompido
    }

    public Livros consultaLivro(int id)//metodo get
    {
        SQLiteDatabase banco = this.getReadableDatabase();

        Cursor cursor = banco.query(TABELA_LIVROS, new String[]{CAMPO_ID, CAMPO_TITULO, CAMPO_EDITORA, CAMPO_ANO, CAMPO_AUTOR}
                , CAMPO_ID+ "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Livros livros = new Livros(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        return livros; // retornando o resultado da busca
    }
    public List<Livros> listaTodosLivros()
    {
        List<Livros>  listaLivros = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABELA_LIVROS;// selectQuerry guarda esse comando SQL
        SQLiteDatabase db = this.getWritableDatabase(); // esse escreve e ler
        Cursor cursor = db.rawQuery(selectQuery, null);//
        if (cursor.moveToFirst())//posicionar na primeira posição
        {
            do//criar varios objetos contatos, até todos serem add
            {
                Livros livros = new Livros();
                livros.setid(Integer.parseInt(cursor.getString(0)));
                livros.setTitulo(cursor.getString(1));
                livros.setEditora(cursor.getString(2));
                livros.setAno(cursor.getString(3));
                livros.setAutor(cursor.getString(4));
                listaLivros.add(livros);//contatos sendo adicionados na lista de contatos
            }
            while (cursor.moveToNext());
        }
        return listaLivros;
    }


}


















/*  public int atualizaLivros (Livros livros)//modificar contatos
    {
        SQLiteDatabase banco = this.getWritableDatabase();// abre o banco de dados para escrita e leitura
        ContentValues values =  new ContentValues();//opera com um registro
        values.put(CAMPO_ID, livros.getid());//
        values.put(CAMPO_TITULO, livros.getTitulo());//
        values.put(CAMPO_EDITORA, livros.getEditora());//
        values.put(CAMPO_ANO, livros.getAno());//
        values.put(CAMPO_AUTOR, livros.getAutor());//
        return banco.update(TABELA_LIVROS, values, CAMPO_ID + "=?", new String[] {String.valueOf(livros.getid())});//nome da tabela, objeto, seleção do que eu quero(criterio), o que vai atender esse criterio
    }
*/
 /*public void deletaLivros(Livros livros)
    {
        SQLiteDatabase banco = this.getWritableDatabase();//pra deletar precisa gravar
        banco.delete(TABELA_LIVROS, CAMPO_ID + "=?", new String[] {String.valueOf(livros.getid())});
        banco.close();
    }*/