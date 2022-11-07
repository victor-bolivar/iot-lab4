package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void vistaAdmin(View view){
        Intent intent=new Intent(this,Admin.class);
        startActivity(intent);
    }

    public void vistaUser(View view){
        Intent intent=new Intent(this,UserActivity.class);
        startActivity(intent);
    }


}