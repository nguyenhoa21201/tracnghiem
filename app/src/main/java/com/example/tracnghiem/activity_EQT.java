package com.example.tracnghiem;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class activity_EQT extends AppCompatActivity {

    private TextView tvQuestion, tvScore, tvQuestionNo, tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4;
    private Button btnNext;

    int totalQuestions;
    int qCounter =0;
    int Score;

    ColorStateList dfRbcolor;
    boolean answered;

    CountDownTimer countDownTimer;
    private QuestionModel currentQuestion;

    private List<QuestionModel> questionList;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_eqt);

        questionList = new ArrayList<>();
        tvQuestion = findViewById(R.id.textQuestion);
        tvScore = findViewById(R.id.textScore);
        tvQuestionNo = findViewById(R.id.textQuestionNo);
        tvTimer = findViewById(R.id.textTimer);

        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        btnNext = findViewById(R.id.btnNext);

        dfRbcolor = rb1.getTextColors();

        addQuestions();
        totalQuestions = questionList.size();
        showNextQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answered == false){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkAnswer();
                        countDownTimer.cancel();
                    }else {
                        Toast.makeText(activity_EQT.this,  "Please Select an Option", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showNextQuestion();
                }
            }
        });
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) + 1 ;
        if(answerNo == currentQuestion.getCorrectAnsNo()){
            Score++;
            tvScore.setText("Score: "+Score);

        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        switch (currentQuestion.getCorrectAnsNo()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                break;
        }
        if(qCounter < totalQuestions){
            btnNext.setText("Next");
        }else {
            btnNext.setText("Finish");
        }
    }

    private void showNextQuestion() {

        radioGroup.clearCheck();
        rb1.setTextColor(dfRbcolor);
        rb2.setTextColor(dfRbcolor);
        rb3.setTextColor(dfRbcolor);
        rb4.setTextColor(dfRbcolor);

        if(qCounter < totalQuestions){
            timer();
            currentQuestion = questionList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            qCounter++;
            btnNext.setText("Submit");
            tvQuestionNo.setText("Question: " +qCounter+ "/"+totalQuestions);
            answered = false;
        }else {
            finish();
        }
    }

    private void timer() {
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l) {
                tvTimer.setText("00:"+ 1/1000);
            }

            @Override
            public void onFinish() {
                showNextQuestion();
            }
        }.start();
    }

    private void addQuestions() {

        questionList.add(new QuestionModel( "We have two ….. We see with them.", "eyes", "legs", "hands", "ears", 1 ));
        questionList.add(new QuestionModel( "There ..... many books on the table.", "is", "have", "are", "has", 3 ));
        questionList.add(new QuestionModel( "They go... on Sundays", "shopping", "to shop", "for shopping", "to make shopping", 1 ));
        questionList.add(new QuestionModel( "I am afraid … studying English", "to", "of", "for", "with", 2 ));
        questionList.add(new QuestionModel( "It is very important for you... get well", "for", "with", "in", "to", 4 ));
    }
}

