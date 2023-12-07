package com.example.medicalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateMedicamento extends AppCompatActivity {

    private SQLiteDatabase bancoDados;
    private EditText editTextNome;
    private EditText editTextDose;
    private EditText editTextInicio;
    private EditText editTextHoras;
    private EditText editTextDias;
    private EditText editTextDesc;
    private Button buttonUpdate;
    private Button buttonDelete;
    private Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicamento);

        this.editTextNome = findViewById(R.id.editTextNome);
        this.editTextDose = findViewById(R.id.editTextDose);
        this.editTextInicio = findViewById(R.id.editTextInicio);
        this.editTextHoras = findViewById(R.id.editTextHoras);
        this.editTextDesc = findViewById(R.id.editTextDesc);

        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 1);

        carregarDados();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDados();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRegistro();
            }
        });

    }

    public void carregarDados(){
        try {
            bancoDados = openOrCreateDatabase("appMedicamentos", MODE_PRIVATE, null);
            Cursor cursor = bancoDados.rawQuery("SELECT id, nome, dose, horaInicial, horas, descricao FROM medicamentosTable WHERE id = " + id.toString(), null);
            cursor.moveToFirst();
            editTextNome.setText(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            editTextDose.setText(cursor.getString(cursor.getColumnIndexOrThrow("dose")));
            editTextInicio.setText(cursor.getString(cursor.getColumnIndexOrThrow("horaInicial")));
            editTextHoras.setText(cursor.getString(cursor.getColumnIndexOrThrow("horas")));
            editTextDesc.setText(cursor.getString(cursor.getColumnIndexOrThrow("descricao")));

            bancoDados.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDados(){
        String valueNome = editTextNome.getText().toString();
        String valueDose = editTextDose.getText().toString();
        String valueInicio = editTextInicio.getText().toString();
        String valueHoras = editTextHoras.getText().toString();
        String valueDescricao = editTextDesc.getText().toString();

        try{
            bancoDados = openOrCreateDatabase("appMedicamentos", MODE_PRIVATE, null);
            String sql = "UPDATE medicamentosTable SET nome=?, dose=?, horaInicial=?, horas=?, descricao=? WHERE id=?";

            SQLiteStatement stmt = bancoDados.compileStatement(sql);
            stmt.bindString(1,valueNome);
            stmt.bindString(2,valueDose);
            stmt.bindString(3,valueInicio);
            stmt.bindString(4,valueHoras);
            stmt.bindString(5,valueDescricao);
            stmt.bindLong(6,id);
            stmt.executeUpdateDelete();

            bancoDados.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        finish();
    }

    public void deleteRegistro() {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(UpdateMedicamento.this);
        msgBox.setTitle("Excluir");
        msgBox.setIcon(android.R.drawable.ic_menu_delete);
        msgBox.setMessage("Você realmente deseja excluir esse registro?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                delete();
                finish();
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        msgBox.show();
    }

    public void delete() {
        try{
            bancoDados = openOrCreateDatabase("appMedicamentos", MODE_PRIVATE, null);
            String sql = "DELETE FROM medicamentosTable WHERE id =?";
            SQLiteStatement stmt = bancoDados.compileStatement(sql);
            stmt.bindLong(1, id);
            stmt.executeUpdateDelete();

            bancoDados.close();

            //AlarmHelper alarm = new AlarmHelper();
            //alarm.cancelAlarm(UpdateMedicamento.this, id);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}