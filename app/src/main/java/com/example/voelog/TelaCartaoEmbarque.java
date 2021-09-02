package com.example.voelog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaCartaoEmbarque extends AppCompatActivity {
    private SQLiteDatabase bancoDados;
    Button btnbusca;
    int telaCartao;
    String saida = "";
    EditText edtbuscaid;
    ArrayList listId = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cartao_embarque);
        edtbuscaid = (EditText) findViewById(R.id.edtbuscacodigo);

        btnbusca = (Button) findViewById(R.id.btnbusca);
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

                listId.add(identificador);

                if(saida.equals(identificador) ){

                    Intent intent = new Intent(TelaCartaoEmbarque.this,CartaoEmbarqueList.class);
                    Bundle params = new Bundle();
                    params.putString("id",saida);
                    intent.putExtras(params);
                    startActivity(intent);
                    break;
                }else if(cursor.isLast()){
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                    //define o titulo
                    builder2.setTitle("Erro");
                    //define a mensagem
                    builder2.setMessage("Codigo Verificador inexistente!");
                    builder2.show();

                }
            }
          //  for(int i=0;i<listId.size()<i++){

           // }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}