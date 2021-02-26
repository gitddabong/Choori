package com.example.choori.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.choori.MainActivity;
import com.example.choori.R;

public class scenario extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario);

        Button skip_button = (Button)findViewById(R.id.skip_button);
        skip_button.setEnabled(false); // Default 버튼 비활성화
        skip_button.setOnClickListener(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(MainActivity.this,"check ok :)", Toast.LENGTH_SHORT).show(); // 5초 확인 용 메시지
                Intent i = new Intent(scenario.this, AuthActivity.class); // 메인 메뉴 모여 있는 액티비티가 없어서 우선 AuthActivity.class로 임시로 이동 (2021.02.26)
                startActivity(i);
            }
        }, 10000);// 10초 정도 딜레이를 준 후 시작

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                skip_button.setEnabled(true);  // 5초 뒤 버튼 활성화
            }
        }, 5000);    //5초 뒤 버튼 활성화
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.skip_button:
                Intent i = new Intent(scenario.this, AuthActivity.class); // 메인 메뉴 모여 있는 액티비티가 없어서 우선 AuthActivity.class로 임시로 이동 (2021.02.26)
                startActivity(i);
                break;
            default:
                break;
        }
    }
}