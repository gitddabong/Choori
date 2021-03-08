package com.example.choori;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<com.example.choori.QuizAdapter.ViewHolder> {

    //파이어스토어 리스트 지정
    private List<Quiz> quiz_data;
    //파이어스토어 리스트 지정

    ArrayList<Quiz> items = new ArrayList<Quiz>();

    public QuizAdapter(List<Quiz> quiz_data) {
        this.quiz_data = quiz_data;
    }

    //커스텀 리스너 정의
    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    //리스너 객체 참조를 저장하는 변수
    static OnItemClickListener mListener = null;

    //커스텀 리스너 구현
    //OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.quiz_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Quiz item = quiz_data.get(position);
        holder.textView.setText(item.getTitle());
        holder.textView2.setText(Integer.toString(item.getCoin()));
        holder.author.setText(item.getAuthor());
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return quiz_data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;
        TextView author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            author = itemView.findViewById(R.id.author);

            //아이템 클릭 이벤트 처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //클릭 이벤트가 발생한 위치 가져오기
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        //데이터 참조
                        if(mListener != null){
                            mListener.onItemClick(v, position);
                        }
                    }
                }
            });
        }

        public void setItem(Quiz item){
            textView.setText(item.getTitle());
            textView2.setText(Integer.toString(item.getCoin()));
        }
    }

    public void addItem(Quiz item){
        items.add(item);
    }

    public void setItems(ArrayList<Quiz> items){
        this.items = items;
    }

    public Quiz getItem(int position){
        return items.get(position);
    }

    public Quiz setItem(int position, Quiz item){
        return items.set(position, item);
    }

}
