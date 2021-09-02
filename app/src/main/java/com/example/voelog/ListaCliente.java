package com.example.voelog;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaCliente extends AppCompatActivity {
    private SQLiteDatabase bancoDados;
    public GridView gridViewDados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);

        gridViewDados = (GridView) findViewById(R.id.gridViewDados);

        listarDados();
    }

    public void listarDados(){
        try{
            bancoDados = openOrCreateDatabase("VoeLog", MODE_PRIVATE,null);
            Cursor meuCursor= bancoDados.rawQuery("SELECT id, nome,cpf,email,telefone,senha FROM cliente",null);
            ArrayList<String> linhas = new ArrayList<String>();
            ArrayAdapter meuAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,android.R.id.text1,  linhas);
            gridViewDados.setAdapter(meuAdapter);
            meuCursor.moveToFirst();
            while (meuCursor!=null){
                linhas.add(meuCursor.getString(1));
                meuCursor.moveToNext();
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
}