package com.example.voelog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import 	android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.PreparedStatement;

public class Login extends AppCompatActivity  {


    private SQLiteDatabase bancoDados;
    public Button btnCadastra;
    public Button btnEntra;
    public EditText nome;
    public EditText senha;
    public boolean checkusernamepassword = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnCadastra = findViewById(R.id.btnCadastra);
        btnEntra = findViewById(R.id.btnEntra);
        nome = findViewById(R.id.edtnome);
        senha = findViewById(R.id.edtsenha);

        btnCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnEntra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                startActivity(intent);
                //logar();
            }
        });

    }

    public void logar(){
        try {
            bancoDados = openOrCreateDatabase("VoeLog", MODE_PRIVATE,null);
            Cursor cursor= bancoDados.rawQuery("SELECT * FROM cliente WHERE nome=? and senha=?", new String[] {nome.toString(), senha.toString()} );
            Toast.makeText(this, cursor.getCount(), Toast.LENGTH_SHORT).show();
            cursor.moveToFirst();
            while ( !cursor.isAfterLast()) {
                String name= cursor.getString(cursor.getColumnIndex("nome"));
                String family= cursor.getString(cursor.getColumnIndex("senha"));
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
               cursor.moveToNext();
            }
           /* if(cursor.getCount()>0)
                Toast.makeText(this, "fechou o pacote", Toast.LENGTH_SHORT).show();
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //define o titulo
                builder.setTitle("Erro");
                //define a mensagem
                builder.setMessage("Nome ou senha invalida!");
                builder.show();
            } */




        }catch (Exception e){

            e.printStackTrace();
        }
    }

}