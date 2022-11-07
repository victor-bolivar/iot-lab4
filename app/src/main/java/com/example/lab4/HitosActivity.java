package com.example.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lab4.beans.Hito;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HitosActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    private ValueEventListener valueEventListener;
    DatabaseReference referenceHitos;
    ArrayList<Hito> listHitos;
    ListaHitosAdapter adapter;
    int golesAlianza=0;
    int golesUniversitario=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitos);
        firebaseDatabase = FirebaseDatabase.getInstance();
        referenceHitos =firebaseDatabase.getReference().child("hitos");
        RecyclerView recyclerView = findViewById(R.id.HitosRecycleView);
        listHitos = new ArrayList<>();
        adapter = new ListaHitosAdapter(HitosActivity.this,listHitos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HitosActivity.this));
        valueEventListener =referenceHitos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listHitos.clear();
                    for (DataSnapshot datasnap : snapshot.getChildren()) {
                        Hito hito = datasnap.getValue(Hito.class);
                        String subcadenagoles="gol";
                        String subcadenagolesContra="gol en contra";
                        String subcadenagolesContra2="autogol";
                        //goles
                        if(hito.getHito().toLowerCase().contains(subcadenagoles)){
                            if(hito.getNombreEquipo().toLowerCase().equals("alianza lima")){
                                golesAlianza=golesAlianza+1;
                            }else{
                                golesUniversitario=golesUniversitario+1;
                            }
                        }
                        //goles en contra
                        if(hito.getHito().toLowerCase().contains(subcadenagolesContra) ||
                                hito.getHito().toLowerCase().contains(subcadenagolesContra2)){
                            if(hito.getNombreEquipo().toLowerCase().equals("alianza lima")){

                                golesUniversitario=golesUniversitario+1;
                            }else{
                                golesAlianza=golesAlianza+1;
                            }
                        }


                        TextView resultadoTextView = findViewById(R.id.resultadoTextView);
                        String score= String.valueOf(golesAlianza)+"-"+String.valueOf(golesUniversitario);
                        resultadoTextView.setText(score);
                        listHitos.add(hito);

                    }

                    adapter.notifyDataSetChanged();
                } else {

                    listHitos.clear();
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        referenceHitos.removeEventListener(valueEventListener);
    }



}