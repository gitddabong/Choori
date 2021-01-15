package com.example.choori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.choori.authentication.AuthActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button firebaseauthbtn = (Button)findViewById(R.id.firebaseauthbtn);
        firebaseauthbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.firebaseauthbtn:
                Intent i = new Intent(this, AuthActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}