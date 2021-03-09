package com.example.choori;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.choori.authentication.FirebaseUIActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

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

    String nickname = "";
    String databaseID = "";
    String DB_nickname = "";

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore Firestore = FirebaseFirestore.getInstance();

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

        //<------------------------ 파이어베이스 파이어스토어 DB 조회하여 닉네임 설정 ------------------------>

        // ↓ Authentication 계정 정보 조회 및 users 테이블 입력 (수정: 2021.03.08)
        FirebaseUser user = mAuth.getCurrentUser();
        databaseID = user.getUid();

        // users 테이블에 Authentication UID 정보가 있는지 확인하는 과정
        Firestore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    for (DocumentSnapshot snap : value.getDocuments()) {
                        Map<String, Object> shot = snap.getData();
                        if (databaseID.equals(snap.getId())) {
                            DB_nickname = String.valueOf(snap.get("nickname"));
                            break;
                        } else {
                            //
                        }
                    }
                }
            }
        });
        Toast.makeText(MainMenu.this, "value: "+DB_nickname, Toast.LENGTH_SHORT).show();
        // ↑ Authentication 계정 정보 조회 및 users 테이블 입력 (수정: 2021.03.08)

        if (DB_nickname.equals("")) {
            final EditText nickname_text = new EditText(MainMenu.this);

            AlertDialog.Builder nickname_dlg = new AlertDialog.Builder(MainMenu.this);
            nickname_dlg.setTitle("닉네임 설정 // 최대 10자");
            nickname_dlg.setView(nickname_text);
            nickname_dlg.setCancelable(false); // 외부 화면으로 취소 불가능
            nickname_dlg.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    nickname = nickname_text.getText().toString();
                    Toast.makeText(MainMenu.this, "닉네임: " + nickname + "입력 되었습니다.", Toast.LENGTH_SHORT).show();
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("nickname", nickname);
                    Firestore.collection("users").document(databaseID).set(userMap, SetOptions.merge());
                }
            });
            nickname_dlg.show();
        }
        //<------------------------ 파이어베이스 파이어스토어 DB 조회하여 닉네임 설정 ------------------------>
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