package com.jinjoa.schoolvoca;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    QuizDataSource dataSource;
    List<QuizItem> listCurrentQuiz;
    QuizItem currentQuiz;

    TextView textViewVoca;
    Button button1, button2, button3, button4;

    List<Button>listButtons = new ArrayList<>();

    private int currentQuizIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        dataSource = new QuizDataSource();
        dataSource.downloadQUiz();
        listCurrentQuiz = dataSource.getQuiz(4);

        textViewVoca = (TextView)findViewById(R.id.textViewVoca);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);

        button1.setOnClickListener(onClick);
        button2.setOnClickListener(onClick);
        button3.setOnClickListener(onClick);
        button4.setOnClickListener(onClick);

        listButtons.add(button1);
        listButtons.add(button2);
        listButtons.add(button3);
        listButtons.add(button4);

        currentQuizIndex = 0;
        nextQuiz();
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button)view;
            String buttonStr = button.getText().toString();
            if(currentQuiz.strKorean.compareTo(buttonStr) == 0) {
                currentQuizIndex++;
                if(currentQuizIndex >= listCurrentQuiz.size()) {
                    Toast.makeText(getApplicationContext(), "정답! 퀴즈를 모두 마치셨습니다.", Toast.LENGTH_SHORT).show();
                    // 딜레이 후에 실행하기
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1000);
                } else {
                    Toast.makeText(getApplicationContext(), "정답", Toast.LENGTH_SHORT).show();
                    nextQuiz();
                }
            } else {
                Toast.makeText(getApplicationContext(), "오답", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void nextQuiz() {
        currentQuiz = listCurrentQuiz.get(currentQuizIndex);
        textViewVoca.setText(currentQuiz.strEnglish);

        List<QuizItem> newList = new ArrayList<>(listCurrentQuiz);
        newList.remove(0);
        Collections.shuffle(newList);

        Collections.shuffle(listButtons);
        listButtons.get(0).setText(currentQuiz.strKorean);
        listButtons.remove(0);

        int i = 0;
        for(Button button : listButtons) {
            QuizItem otherQuiz = newList.get(i);
            i++;
            button.setText(otherQuiz.strKorean);
        }
    }
}
