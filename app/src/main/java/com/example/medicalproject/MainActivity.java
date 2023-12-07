package com.example.medicalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase bancoDados;
    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startApp();
            }
        });

        createDatabase();
    }

    public void createDatabase(){
        try {
            bancoDados = openOrCreateDatabase("appMedicamentos", MODE_PRIVATE, null);
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS medicamentosTable("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT"+
                    ", cor INTEGER"+
                    ", nome VARCHAR"+
                    ", dose VARCHAR"+
                    ", horaInicial VARCHAR"+
                    ", horas VARCHAR"+
                    ", dias VARCHAR"+
                    ", descricao VARCHAR)");
            bancoDados.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void startApp(){
        Intent intent = new Intent(MainActivity.this, Listagem.class);
        startActivity(intent);
    }
}