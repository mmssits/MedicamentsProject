package com.example.medicalproject;

import static java.lang.Long.parseLong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import yuku.ambilwarna.AmbilWarnaDialog;

public class NewMedicamento extends AppCompatActivity {

    private SQLiteDatabase bancoDados;
    private EditText editTextNome;
    private EditText editTextDose;
    private EditText editTextInicio;
    private EditText editTextHoras;
    private EditText editTextDesc;
    private Button buttonSave;
    private Button buttonColor;
    private int defaultColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicamento);

        this.editTextNome = findViewById(R.id.editTextNome);
        this.editTextDose = findViewById(R.id.editTextDose);
        this.editTextInicio = findViewById(R.id.editTextInicio);
        this.editTextHoras = findViewById(R.id.editTextHoras);
        this.editTextDesc = findViewById(R.id.editTextDesc);


        defaultColor = ContextCompat.getColor(NewMedicamento.this, com.google.android.material.R.color.design_default_color_primary);

        this.buttonColor = findViewById(R.id.buttonCor);
        buttonColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                openColorPicker();
            }
        });


        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });
    }

    public void cadastrar(){

        if(!TextUtils.isEmpty(editTextNome.getText().toString()) & !TextUtils.isEmpty(editTextDose.getText().toString()) & !TextUtils.isEmpty(editTextHoras.getText().toString())){

            try {
                bancoDados = openOrCreateDatabase("appMedicamentos", MODE_PRIVATE, null);
                String sql = "INSERT INTO medicamentosTable (nome, dose, horaInicial, horas, descricao) VALUES (?, ?, ?, ?, ?)";

                SQLiteStatement stmt = bancoDados.compileStatement(sql);
                stmt.bindString(1, editTextNome.getText().toString());
                stmt.bindString(2, editTextDose.getText().toString());
                stmt.bindString(3, editTextInicio.getText().toString());
                stmt.bindString(4, editTextHoras.getText().toString());
                stmt.bindString(5, editTextDesc.getText().toString());

                stmt.executeInsert();
                bancoDados.close();
                finish();
                //bancoDados = openOrCreateDatabase("appMedicamentos", MODE_PRIVATE, null);

                //String select = "SELECT id, nome FROM medicamentosTable WHERE nome"

                //Cursor cursor = bancoDados.rawQuery("SELECT id FROM medicamentosTable WHERE (nome = '" + editTextNome.getText().toString() + "')", null);
                // Integer id = Integer.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("id")));

                //AlarmHelper alarm = new AlarmHelper();

                //try {
                    //alarm.createAlarm(this, Long.parseLong(editTextInicio.getText().toString()), Long.parseLong(editTextHoras.getText().toString()), id, new Intent(this, NotificationAlarm.class));
                    //bancoDados.close();

                //}
                //catch (Exception e ){
                    //e.printStackTrace();
                //}
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void openColorPicker(){

        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                buttonColor.setBackgroundColor(color);
            }
        });

        dialog.show();
    }
}