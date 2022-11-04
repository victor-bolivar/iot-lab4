package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AlineacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alineacion);
    }

    public void alinLocal(View view){
        Intent intent=new Intent(this,EquipoLocalActivity.class);
        startActivity(intent);
    }

    public void alinVisita(View view){
        Intent intent=new Intent(this,EquipoVisitaActivity.class);
        startActivity(intent);
    }

}