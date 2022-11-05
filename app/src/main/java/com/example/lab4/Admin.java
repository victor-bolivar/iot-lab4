package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab4.beans.Hito;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Admin extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        firebaseDatabase = FirebaseDatabase.getInstance(); // se obtiene la conexion a la db

    }

    public void guardar (View view){

        // 1. se obtiene la referencia a la db
        DatabaseReference ref = firebaseDatabase.getReference();
        DatabaseReference refHitos = ref.child("hitos");

        // 2. se obtienen los datos
        EditText editTextEquipo = findViewById(R.id.editTextTextEquipo);
        EditText editTextTextNombreJugador = findViewById(R.id.editTextTextNombreJugador);
        EditText editTextTextApellidoJugador = findViewById(R.id.editTextTextApellidoJugador);
        EditText editTextTextHito = findViewById(R.id.editTextTextHito);

        Hito hito = new Hito();
        hito.setNombreEquipo(editTextEquipo.getText().toString());
        hito.setNombreJugador(editTextTextNombreJugador.getText().toString());
        hito.setApellidoJugador(editTextTextApellidoJugador.getText().toString());
        hito.setHito(editTextTextHito.getText().toString());


        // 3. se guardan los datos
        refHitos.push().setValue(hito).addOnSuccessListener(unused -> {
            Toast.makeText(Admin.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
        });

        // 4. se resetean los valores
        editTextEquipo.setText("");
        editTextTextNombreJugador.setText("");
        editTextTextApellidoJugador.setText("");
        editTextTextHito.setText("");


    }
}