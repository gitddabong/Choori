package com.example.choori;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    /*
    Button mute;
    Button earn_coin;
    Button pay_coin;
    Button questioning;
    Button qna;
    Button challenge;
     */
    Button ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        /*
        mute = (Button)findViewById(R.id.mute);
        earn_coin = (Button)findViewById(R.id.earn_coin);
        pay_coin = (Button)findViewById(R.id.pay_coin);
        questioning = (Button)findViewById(R.id.questioning);
        qna = (Button)findViewById(R.id.qna);
        challenge = (Button)findViewById(R.id.challenge);
         */
        ranking = (Button)findViewById(R.id.ranking);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*
            case R.id.mute:
                break;

            case R.id.earn_coin:
                break;

            case R.id.pay_coin:
                break;

            case  R.id.questioning:
                break;

            case  R.id.qna:
                break;

            case R.id.challenge:
                break;

             */

            case R.id.ranking:
                break;

            default:
                break;
        }
    }
}