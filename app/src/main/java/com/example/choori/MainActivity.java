package com.example.choori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.choori.authentication.AuthActivity;
import com.example.choori.authentication.FirebaseUIActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(MainActivity.this,"check ok :)", Toast.LENGTH_SHORT).show(); // 5초 확인 용 메시지
                Intent i = new Intent(MainActivity.this, FirebaseUIActivity.class);
                startActivity(i);
            }
        }, 5000);// 5초 정도 딜레이를 준 후 시작

        //Button firebaseauthbtn = (Button)findViewById(R.id.firebaseauthbtn);
        //firebaseauthbtn.setOnClickListener(this);
    }


    /*
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
    */
}