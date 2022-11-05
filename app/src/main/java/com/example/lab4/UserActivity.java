package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

    }

    public void alineacion(View view){

        Intent intent=new Intent(this,AlineacionActivity.class);
        startActivity(intent);
    }

    public void hitos(View view){

        Intent intent=new Intent(this,HitosActivity.class);
        startActivity(intent);

    }

}