package com.example.voelog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase bancoDados;
    public Button botao;
    public Button btnLista;
    public EditText nome;
    public EditText cpf;
    public EditText email;
    public EditText telefone;
    public EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome= findViewById(R.id.editTextTextPersonName2);
        cpf=  findViewById(R.id.editTextNumber);
        email=  findViewById(R.id.editTextTextEmailAddress);
        telefone= findViewById(R.id.editTextPhone);
        senha=  findViewById(R.id.editTextTextPassword);
        botao= (Button)findViewById(R.id.btnCompreAqui);
        btnLista = (Button)findViewById(R.id.btnLista);

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarCliente();
            }
        });
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               cadastrar();
            }
        });

       criarBancoDados();
    }

    public void criarBancoDados(){
        try{
            bancoDados = openOrCreateDatabase("VoeLog", MODE_PRIVATE,null);
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS cliente("+
                            " id INTEGER PRIMARY KEY AUTOINCREMENT" +
                            ", nome VARCHAR" + ", cpf INTEGER"+",email VARCHAR"+
                            ",telefone INTEGER"+",senha INTEGER)");
            bancoDados.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cadastrar(){
        if(!TextUtils.isEmpty(nome.getText().toString()) &&
                !TextUtils.isEmpty(cpf.getText().toString())&&
                !TextUtils.isEmpty(email.getText().toString())&&
                !TextUtils.isEmpty(telefone.getText().toString())&&
                !TextUtils.isEmpty(senha.getText().toString())){
            try{
                bancoDados = openOrCreateDatabase("VoeLog", MODE_PRIVATE,null);
                String sql= "INSERT INTO cliente (nome,cpf,email,telefone,senha) VALUES (?,?,?,?,?)";
                SQLiteStatement stmt = bancoDados.compileStatement(sql);
                stmt.bindString(1,nome.getText().toString());
                stmt.bindString(2,cpf.getText().toString());
                stmt.bindString(3,email.getText().toString());
                stmt.bindString(4,telefone.getText().toString());
                stmt.bindString(5,senha.getText().toString());
                stmt.executeInsert();
                bancoDados.close();
                Toast.makeText(this, "Cadastro Efetuado", Toast.LENGTH_SHORT).show();
                finish();
            }catch (Exception e){
                e.printStackTrace();
            }

        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //define o titulo
            builder.setTitle("Erro");
            //define a mensagem
            builder.setMessage("Preencha todos os campos");
            builder.show();
        }
    }

    public void listarCliente(){
        Intent intent = new Intent(this, ListaCliente.class);
        startActivity(intent);
    }
}