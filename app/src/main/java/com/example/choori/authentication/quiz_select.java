package com.example.choori.authentication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.choori.Quiz;
import com.example.choori.QuizAdapter;
import com.example.choori.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class quiz_select extends AppCompatActivity implements View.OnClickListener{

    private List<Quiz> quiz_data;
    private FirebaseFirestore Firestore = FirebaseFirestore.getInstance();
    private QuizAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_select);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        //각 행의 열의 개수를 다르게 조정하는 것도 가능
        /*
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int gridPosition = position % 2;
                switch (gridPosition){
                    case 0:
                    case 1:
                    case 2:
                }
                return 0;
            }
        });
        */
        recyclerView.setLayoutManager(layoutManager);

        //==================문제 리스트 조회
        quiz_data = new ArrayList<>();
        Firestore.collection("board_write_test").orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    quiz_data.clear();
                    for (DocumentSnapshot snap : value.getDocuments()) {
                        Map<String, Object> shot = snap.getData();
                        String title = String.valueOf(snap.get("title"));
                        int coin = Integer.parseInt(String.valueOf(snap.get("coin")));
                        String author = String.valueOf(snap.get("author"));
                        String documentID = snap.getId();
                        Quiz data = new Quiz(title, coin, author, documentID);
                        quiz_data.add(data);
                    }
                    adapter = new QuizAdapter(quiz_data);


                    //클릭 이벤트
                    adapter.setOnItemClickListener(new QuizAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {     //position은 리사이클러뷰 내부의 게시글의 위치. 0부터 시작.
                            Intent intent = new Intent(getApplicationContext(), quiz_taking_Activity.class);
                            intent.putExtra("documentID", quiz_data.get(position).getDocumentID());
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                }
            }
        });

    }

    //아직 클릭 리스너를 구현할 필요가 없으므로 보류

    @Override
    public void onClick(View view) {
        /*
        Intent i = null;
        switch (view.getId()){
            case R.id.board_write_button:
                i = new Intent(this, board_write.class);
                startActivity(i);
                break;
            default:
                break;
        }
        */

    }

}
