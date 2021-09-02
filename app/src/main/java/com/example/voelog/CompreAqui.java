package com.example.voelog;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Calendar;

public class CompreAqui extends AppCompatActivity implements View.OnClickListener{
    SQLiteDatabase bdpassagem;
    Spinner spIda, spVolta;
    Spinner spAdulto, spCrianca, spBebe;
    ArrayAdapter<String> adaptador;
    ArrayAdapter<String> adaptador2;
    String aeroportos[] = {"GRU Guarulhos","PVH Gov.JorgeTeixeira","CGH Congonhas", "BSB Brasilia","CGB Cuiabá","FOR Fortaleza"};
    String numeros[] = {"0","1","2","3"};
    int status= 0;
    int qtadulto=0;
    int qtcrianca=0;
    int qtbebe=0;
    int idavolta= 1;
    String idateste= "1";
    EditText iData,vData;
    int dia,mes,ano;
    int diai,mesi,anoi,diav,mesv,anov;
    TextView textoData;
    String data, dataIda ="", dataVolta="", origem="", destino="", quantAdulto="", quantCrianca="", quantbebe="", id;
    RadioGroup grupo;
    RadioButton rbIda,rbAmbos;
    Button btnagoravai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compre_aqui);

        criaBandodeDados();
        //
        iData = (EditText) findViewById(R.id.eddata1);
        iData.setShowSoftInputOnFocus(false);
        vData = (EditText) findViewById(R.id.eddata2);
        vData.setShowSoftInputOnFocus(false);
        textoData = (TextView) findViewById(R.id.tDataVolta);
        iData.setOnClickListener(escutaData);

        grupo = (RadioGroup) findViewById(R.id.grupo);
        rbIda = (RadioButton) findViewById(R.id.rbida);
        rbAmbos = (RadioButton) findViewById(R.id.rbambos);
        grupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup  rgrupo, int opcaoid){
                boolean ida = R.id.rbida == opcaoid;
                boolean ambos = R.id.rbambos == opcaoid;
                if (ida) {
                    idavolta=1;
                    rbAmbos.setChecked(false);
                    vData.setVisibility(EditText.INVISIBLE);
                    textoData.setVisibility(TextView.INVISIBLE);
                }else if (ambos) {
                    idavolta=2;
                    rbIda.setChecked(false);
                    vData.setOnClickListener(escutaDataV);
                    vData.setVisibility(EditText.VISIBLE);
                    textoData.setVisibility(TextView.VISIBLE);
                }
            }
        });
        spIda = (Spinner) findViewById(R.id.sporigem);
        spVolta = (Spinner) findViewById(R.id.sppara);

        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,aeroportos);
        spIda.setAdapter((adaptador));
        spVolta.setAdapter(adaptador);
        spIda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> ad, View view, int i, long l){
                int indice = ad.getSelectedItemPosition();
                origem = aeroportos[indice].toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });
        spVolta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> ad, View view, int i, long l){
                int indice = ad.getSelectedItemPosition();
                destino=aeroportos[indice].toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });
        //
        spAdulto = (Spinner) findViewById(R.id.spadulto);

        spCrianca = (Spinner) findViewById(R.id.spcrianca);
        spBebe = (Spinner) findViewById(R.id.spbebe);
        adaptador2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,numeros);
        spAdulto.setAdapter(adaptador2);
        spAdulto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> ad, View view, int i, long l){
                int indice = ad.getSelectedItemPosition();
                quantAdulto = numeros[indice].toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });
        spCrianca.setAdapter(adaptador2);
        spCrianca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> ad, View view, int i, long l){
                int indice = ad.getSelectedItemPosition();
                quantCrianca = numeros[indice].toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });
        spBebe.setAdapter(adaptador2);
        spBebe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> ad, View view, int i, long l){
                int indice = ad.getSelectedItemPosition();
                quantbebe = numeros[indice].toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });
        btnagoravai = findViewById(R.id.btnagoravai);
        btnagoravai.setOnClickListener(this);

        Calendar mcurrentDate = Calendar.getInstance();
        ano = mcurrentDate.get(Calendar.YEAR);
        mes = mcurrentDate.get(Calendar.MONTH) ;
        dia = mcurrentDate.get(Calendar.DAY_OF_MONTH);
    }//onCreate
    private View.OnClickListener escutaData = new View.OnClickListener() {//Calendario para escolha da data de ida
        public void onClick(View v) {
            DatePickerDialog mDatePicker = new DatePickerDialog (CompreAqui.this,
                    new DatePickerDialog.OnDateSetListener(){
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth,int selectedday){
                            selectedmonth+=1;
                            data=String.format("%2d/%2d/%4d",selectedday,selectedmonth,selectedyear);
                            dataIda= data;
                            iData.setText(data);
                        }
                    },ano,mes,dia);
            mDatePicker.setTitle("Selecione uma data");
            mDatePicker.show();
            diai=dia;
            mesi=mes;
            anoi=ano;
        }
    };//escutaData
    private View.OnClickListener escutaDataV = new View.OnClickListener() {// Calendario para escolha da data de volta
        public void onClick(View v) {
            DatePickerDialog mDatePicker = new DatePickerDialog (CompreAqui.this,
                    new DatePickerDialog.OnDateSetListener(){
                        public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth,int selectedday){
                            selectedmonth+=1;
                            data=String.format("%2d/%2d/%4d",selectedday,selectedmonth,selectedyear);
                            dataVolta= data;
                            vData.setText(data);
                        }
                    },ano,mes,dia);
            mDatePicker.setTitle("Selecione uma data");
            mDatePicker.show();
        }
    };//escutaDataV
    public void geraIdentificador(){
        Random rand = new Random();
        String cod= "XBY";
        String aleatorio=String.valueOf(rand.nextInt(1000));
        String identificador = cod.concat(aleatorio);
        id = identificador;
    }
    public void onClick(View v){ // botao CampreAqui btnagoravai
        //DBAdapter db = new DBAdapter(this);
        //String saida="";
        geraIdentificador();
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        comprarPassagem();
       Toast.makeText(this, "Cadastro Efetuado", Toast.LENGTH_SHORT).show();
        // inserir a viagem na tabela "viagens" do banco de dados "voegol"
    }
    public void criaBandodeDados(){
        try{
            bdpassagem = openOrCreateDatabase("teste", MODE_PRIVATE,null);
            bdpassagem.execSQL("CREATE TABLE IF NOT EXISTS passagem("+
                    " id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ",identificador VARCHAR"+", ida INTEGER" + ", origem VARCHAR"+",destino VARCHAR"+
                    ",adulto INTEGER"+",crianca INTEGER"+", bebe INTEGER"+",dataida VARCHAR"+",datavolta VARCHAR"+",status INTEGER)");
            bdpassagem.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void comprarPassagem(){

            try{
                bdpassagem = openOrCreateDatabase("teste", MODE_PRIVATE,null);
                String sql= "INSERT INTO passagem (identificador,ida,origem,destino,adulto,crianca,bebe,dataida,datavolta,status) VALUES (?,?,?,?,?,?,?,?,?,?)";
                SQLiteStatement stmt = bdpassagem.compileStatement(sql);
                stmt.bindString(1,id);
                stmt.bindLong(2,idavolta);
                stmt.bindString(3,origem);
                stmt.bindString(4,destino);
                stmt.bindString(5,quantAdulto);
                stmt.bindString(6,quantCrianca);
                stmt.bindString(7,quantbebe);
                stmt.bindString(8,dataIda);
                stmt.bindString(9,dataVolta);
                stmt.bindLong(10,status);
                stmt.executeInsert();
                bdpassagem.close();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //define o titulo
                builder.setTitle("Compra Efetuada!");
                //define a mensagem
                builder.setMessage("Código Identificador da passagem: "+id);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.show();
                //Toast.makeText(this, "Compra Efetuada", Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                e.printStackTrace();
            }


    }
}//class