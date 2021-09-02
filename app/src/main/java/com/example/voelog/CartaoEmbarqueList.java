package com.example.voelog;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartaoEmbarqueList extends AppCompatActivity {
    private SQLiteDatabase bancoDados;

    String saida="";
    GridView gridView;
    ArrayList<CartaoEmbarque> list;
    CartaoEmbarqueAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent t = getIntent();
       // if (t != null) {
        //    Bundle params = t.getExtras();
        //    saida = params.getString("id");
       // }
        setContentView(R.layout.activity_tela_cartao_embarque);

        gridView= (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new CartaoEmbarqueAdapter(this,R.layout.cartao_embarqueitem,list);
        gridView.setAdapter(adapter);

        try {
            bancoDados = openOrCreateDatabase("teste", MODE_PRIVATE,null);
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM passagem",null);
            list.clear();
            while (cursor.moveToNext()){
           // String origem = "aopa";
            //String destino = "aopa";
            //String ida = "aopa";
            //String volta = "aopa";


                    String origem = cursor.getString(3);
                    String destino = cursor.getString(4);
                    String ida = cursor.getString(8);
                    String volta = cursor.getString(9);
                    list.add(new CartaoEmbarque(origem,destino,ida,volta));



            }
        }catch (Exception e){
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();
    }
}
