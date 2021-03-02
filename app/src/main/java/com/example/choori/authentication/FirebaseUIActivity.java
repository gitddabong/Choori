package com.example.choori.authentication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.choori.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseUIActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RC_SIGN_IN                = 1000;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore Firestore = FirebaseFirestore.getInstance();

    int lifepoint = 10; // Default: 산소 캡슐 10개
    int coin_point = 0; // Default: 코인 0개
    String userID = "";
    //String nickname = "";
    //String DB_nickname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_ui);

        Button firebaseuiauthbtn = (Button)findViewById(R.id.firebaseuiauthbtn);
        firebaseuiauthbtn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                // ↓ Authentication 계정 정보 조회 및 users 테이블 입력 2021.03.02
                FirebaseUser user = mAuth.getCurrentUser();

                // users 테이블에 Authentication UID 정보가 있는지 확인하는 과정
                Firestore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (value != null) {
                            for (DocumentSnapshot snap : value.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                if (user.getUid().equals(snap.getId())) {
                                    userID = snap.getId();
                                } else {
                                    userID = "none";
                                }
                                //DB_nickname = String.valueOf(snap.get("nickname"));
                            }
                        }
                    }
                });

                if (user.getUid().equals(userID)) {
                    Toast.makeText(FirebaseUIActivity.this, "hello " + userID, Toast.LENGTH_SHORT).show();
                } else {
                    if (user != null) {
                        Map<String, Object> userMap = new HashMap<>();
                        userMap.put("lifepoint", lifepoint);
                        userMap.put("coin", coin_point);
                        Firestore.collection("users").document(user.getUid()).set(userMap, SetOptions.merge());
                    } else {
                        Toast.makeText(FirebaseUIActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
                // users 테이블에 Authentication UID 정보가 있는지 확인하는 과정
                // ↑ Authentication 계정 정보 조회 및 users 테이블 입력 2021.03.02

                /*

                if (DB_nickname != null) {

                }

                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("닉네임 설정");
                alert.setMessage("글자 수 제한은 최대 10자입니다.");
                final EditText name = new EditText(this);
                InputFilter[] FilterArray = new InputFilter[1];
                FilterArray[0] = new InputFilter.LengthFilter(10); //글자 수 제한
                name.setFilters(FilterArray);
                alert.setView(name);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) { //확인 버튼
                        String username = name.getText().toString();
                        nickname.setText(username);
                        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("nickname", username);
                        editor.commit();
                        Toast.makeText(FirebaseUIActivity.this, "닉네임이 설정되었습니다.", Toast.LENGTH_LONG).show();
                    }
                });
                alert.setNegativeButton("취소",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) { //취소 버튼
                        Toast.makeText(FirebaseUIActivity.this, "취소 하셨어요!", Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();

                 */

                Intent i = new Intent(this, scenario.class);
                i.putExtras(data);
                startActivity(i);
            }
            else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.firebaseuiauthbtn:
                signin();
                break;
            default:
                break;
        }
    }

    /*
     인증 요청
     */
    private void signin()
    {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setTheme(getSelectedTheme())                 // Theme 설정
                        .setLogo(getSelectedLogo())                  // 로고 설정
                        .setAvailableProviders(getSelectedProviders())// Providers 설정
                        .setTosAndPrivacyPolicyUrls("https://naver.com",
                                "https://google.com")
                        .setIsSmartLockEnabled(true)              //SmartLock 설정
                        .build(),
                RC_SIGN_IN);
    }

    /*
     * FirebaseUI에 표시할 테마 정보
     * @return 테마 정보
     */
    private int getSelectedTheme()
    {
        return AuthUI.getDefaultTheme();
    }

    /*
     * Firebase UI에 표시할 로고 이미지
     * @return 로고 이미지
     */
    private int getSelectedLogo()
    {
        return AuthUI.NO_LOGO;
    }

    /*
     * FirebaseUI를 통해 제공 받을 인증 서비스 설정
     * @return 인증 서비스
     */
    private List<AuthUI.IdpConfig> getSelectedProviders()
    {
        List<AuthUI.IdpConfig> selectedProviders = new ArrayList<>();
        CheckBox googlechk = (CheckBox)findViewById(R.id.google_provider);
        //CheckBox facebookchk = (CheckBox)findViewById(R.id.facebook_provider);
        //CheckBox twitterchk = (CheckBox)findViewById(R.id.twitter_provider);
        CheckBox emailchk = (CheckBox)findViewById(R.id.email_provider);

        if(googlechk.isChecked())
        {
            selectedProviders.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        }
/*
        if(facebookchk.isChecked())
        {
            selectedProviders.add(new AuthUI.IdpConfig.FacebookBuilder().build());
        }

        if(twitterchk.isChecked())
        {
            selectedProviders.add(new AuthUI.IdpConfig.TwitterBuilder().build());
        }
 */

        if(emailchk.isChecked())
        {
            selectedProviders.add(new AuthUI.IdpConfig.EmailBuilder().build());
        }

        return selectedProviders;
    }
}