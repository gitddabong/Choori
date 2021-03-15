package com.example.choori.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.choori.Board;
import com.example.choori.GlideApp;
import com.example.choori.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FirebaseStorage;

public class quiz_taking_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView board_title;
    ImageView showimg;
    TextView board_contents;
    TextView contents_coin;
    TextView author;
    Button do_submission;
    Button do_exit;
    LinearLayout linearLayout;
    Button choice1, choice2, choice3, choice4;


    String documentID = "";
    String img_name = ""; // 삭제할 때 이미지 네임 적용 위한 변수
    boolean isMultiple;
    String[] choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_taking_);

        board_title = (TextView)findViewById(R.id.board_title);
        showimg = (ImageView)findViewById(R.id.showimg);
        board_contents = (TextView)findViewById(R.id.board_contents);
        contents_coin = (TextView)findViewById(R.id.contents_coin);
        author = (TextView)findViewById(R.id.author);
        do_submission = (Button)findViewById(R.id.do_submission);
        do_exit = (Button)findViewById(R.id.do_exit);

        choice1 = new Button(getApplicationContext());
        choice1.setId(1);
        choice2 = new Button(getApplicationContext());
        choice2.setId(2);
        choice3 = new Button(getApplicationContext());
        choice3.setId(3);
        choice4 = new Button(getApplicationContext());
        choice4.setId(4);

        Intent intent = getIntent();
        documentID = intent.getExtras().getString("documentID");
        selectDoc(documentID);

        do_submission.setOnClickListener(this);
        do_exit.setOnClickListener(this);
    }

    private void selectDoc(String documentID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("board_write_test").document(documentID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()) {      //데이터가 존재할 경우
                        Board board = document.toObject(Board.class);
                        board_title.setText(board.getTitle());

                        // ====== 이미지 뷰 가져오기
                        //Toast.makeText(board_view.this,"uri: " + board.getImguri(), Toast.LENGTH_SHORT).show();
                        //FirebaseStorage storage = FirebaseStorage.getInstance(board.getImguri());
                        //Glide.with(getApplication()).load(storage).into(showimg);
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        StorageReference storageRef = storage.getReference();
                        StorageReference imageRef = storageRef.child("sample/" + board.getImguri() + ".jpg");
                        img_name = board.getImguri();
                        GlideApp.with(quiz_taking_Activity.this).load(imageRef).into(showimg);
                        // ====== 이미지 뷰 가져오기

                        board_contents.setText(board.getContents());
                        contents_coin.setText(Integer.toString(board.getCoin()));
                        author.setText(board.getAuthor());

                        isMultiple = board.isMultiple();
                        choice = board.getChoice().split(";");

                        //동적 텍스트뷰로 객관식 문제가 있을 경우 객관식 문항을 출력
                        if(isMultiple) {     //객관식
                            linearLayout = findViewById(R.id.contentLayout);
                            LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            choice1.setText("1. " + choice[0]);
                            choice2.setText("2. " + choice[1]);
                            choice3.setText("3. " + choice[2]);
                            choice4.setText("4. " + choice[3]);
                            choice1.setLayoutParams(linearLayoutParams);
                            choice2.setLayoutParams(linearLayoutParams);
                            choice3.setLayoutParams(linearLayoutParams);
                            choice4.setLayoutParams(linearLayoutParams);
                            linearLayout.addView(choice1);
                            linearLayout.addView(choice2);
                            linearLayout.addView(choice3);
                            linearLayout.addView(choice4);
                        }
                        //동적 텍스트뷰로 객관식 문제가 있을 경우 객관식 문항을 출력

                    }
                    else{

                    }

                }
                else{
                    Log.d("Choori", "get failed with", task.getException());
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.do_submission:


                break;
            case  R.id.do_exit:



                break;
            case choice1:

                break;
            case choice2.getId():

                break;
            case choice3.getId():

                break;
            case choice4.getId():

                break;
            default:
                break;

        }
    }
}