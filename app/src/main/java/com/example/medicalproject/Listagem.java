package com.example.medicalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Listagem extends AppCompatActivity {

    private SQLiteDatabase bancoDados;
    private ListView listMedicaments;
    private Button buttonNew;
    private Integer idSelected;
    private ArrayList<Integer> arrayId = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listMedicaments = (ListView) findViewById(R.id.listView);

        listarDados();

        buttonNew = (Button) findViewById(R.id.newMedicament);
        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewRegister();
            }
        });

        listMedicaments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                idSelected = arrayId.get(i);
                openUpdateActivity();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        listarDados();
    }

    public void listarDados(){
        bancoDados = openOrCreateDatabase("appMedicamentos", MODE_PRIVATE, null);
        Cursor cursor = bancoDados.rawQuery("SELECT * FROM medicamentosTable", null);

        if(cursor.getCount() > 0){
            ArrayList<String> itens = new ArrayList<>();

            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                itens.add("\r\n" + cursor.getString(cursor.getColumnIndexOrThrow("nome")) + "   -   " + cursor.getString(cursor.getColumnIndexOrThrow("dose")) + "\r\n" + cursor.getString(cursor.getColumnIndexOrThrow("descricao")) + "\r\n");
                arrayId.add(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                cursor.moveToNext();
            }

            ArrayAdapter adapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    itens
            );

            listMedicaments.setAdapter(adapter);
        }

        bancoDados.close();

    }

    public void openNewRegister(){
        Intent intent = new Intent(this, NewMedicamento.class);
        startActivity(intent);
    }

    public void openUpdateActivity(){
        Intent intent = new Intent(this, UpdateMedicamento.class);
        intent.putExtra("id", idSelected);
        startActivity(intent);
    }
}