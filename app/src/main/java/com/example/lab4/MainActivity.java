package com.example.lab4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.idAdmin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth auth = FirebaseAuth.getInstance();
                if (auth.getCurrentUser() != null){
                    Log.d("msg"," email : " +  auth.getCurrentUser().getEmail());
                }else{
                    Intent fbIntent = AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.GoogleBuilder().build()
                            ))
                            .setIsSmartLockEnabled(false)
                            .build();
                    signLauncher.launch(fbIntent);
                }


            }
        });
    }


    private final ActivityResultLauncher<Intent> signLauncher = registerForActivityResult(new FirebaseAuthUIActivityResultContract(),
            result -> {
                onSignInResult(result);
            });

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {

        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            Log.d("msg","firebase uid : " + user.getEmail());
            Intent intent=new Intent(this,Admin.class);
            startActivity(intent);
        }else{
            Log.d("msg","Cancelo el login");
        }

    }


    public void vistaUser(View view){
        Intent intent=new Intent(this,UserActivity.class);
        startActivity(intent);
    }


}