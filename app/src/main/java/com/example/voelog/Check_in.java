package com.example.voelog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Check_in extends AppCompatActivity {
    private SQLiteDatabase bancoDados;
    int status = 1;
    int id;
    Button btnbusca;
    String saida = "";
    EditText edtbuscaid;
    ArrayList listId = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        edtbuscaid = (EditText) findViewById(R.id.edtbuscacodigo2);

        btnbusca = (Button) findViewById(R.id.btnbusca2);
        btnbusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(), "Compra Efetuada", Toast.LENGTH_SHORT).show();
                saida= edtbuscaid.getText().toString();
                buscaID();

            }
        });
    }

    public void buscaID(){
        try {
            bancoDados = openOrCreateDatabase("teste", MODE_PRIVATE,null);
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM passagem",null);
            listId.clear();

            while (cursor.moveToNext()){
                // String origem = "aopa";
                //String destino = "aopa";
                //String ida = "aopa";
                //String volta = "aopa";
                String identificador = cursor.getString(1);
                 id = cursor.getInt(0);

                listId.add(identificador);

                if(saida.equals(identificador) ){
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
                    //define o titulo
                    builder3.setTitle("Check-In");
                    //define a mensagem
                    builder3.setMessage("Deseja confirmar o Check-In da Passagem "+identificador+"?");
                    builder3.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           insereStatus();
                        }
                    });
                    builder3.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder3.show();
                    break;

                }else {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                    //define o titulo
                    builder2.setTitle("Erro");
                    //define a mensagem
                    builder2.setMessage("Código Identificador não encontrado!");
                    builder2.show();

                }
            }

            //  for(int i=0;i<listId.size()<i++){

            // }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void insereStatus(){
        bancoDados = openOrCreateDatabase("teste", MODE_PRIVATE,null);
        String sql= "UPDATE passagem SET status=? where id=?";
        SQLiteStatement stmt = bancoDados.compileStatement(sql);
        stmt.bindLong(1,status);
        stmt.bindLong(2,id);
        stmt.executeUpdateDelete();
        bancoDados.close();
        Toast toast = Toast.makeText(this,"Check-In Confirmado!",Toast.LENGTH_LONG);
        toast.show();
        finish();
    }

}