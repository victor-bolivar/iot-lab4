package com.example.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lab4.beans.Hito;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AlineacionActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;

    ArrayList<Hito> HitosBack = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alineacion);

        firebaseDatabase =FirebaseDatabase.getInstance();

        firebaseDatabase.getReference().child("hitos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot hitos : snapshot.getChildren()){
                    Hito hitos1 = hitos.getValue(Hito.class);
                    HitosBack.add(hitos1);
                    System.out.println(" hito prueba es  " + hitos.getKey() + " | " + hitos1.getNombreEquipo());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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