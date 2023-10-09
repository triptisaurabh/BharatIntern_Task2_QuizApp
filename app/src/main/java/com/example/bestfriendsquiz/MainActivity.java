package com.example.bestfriendsquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitButton;

    int score=0;
    int totalQuestion = QuesAns.question.length;
    int currentQuestionIndex = 0;
    String selectAnswer ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitButton = findViewById(R.id.submit_button);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        totalQuestionTextView.setText("Total Questions : "+totalQuestion);

        loadNewQuestion();

    }

    @Override
    public void onClick(View view){

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId()==R.id.submit_button){
            if (selectAnswer.equals(QuesAns.correctanswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        } else {
            //choices button was clicked
            selectAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }


    void loadNewQuestion(){

        if (currentQuestionIndex==totalQuestion){
            FinishQuiz();
            return;
        }
        questionTextView.setText(QuesAns.question[currentQuestionIndex ]);
        ansA.setText(QuesAns.choices[currentQuestionIndex][0]);
        ansB.setText(QuesAns.choices[currentQuestionIndex][1]);
        ansC.setText(QuesAns.choices[currentQuestionIndex][2]);
        ansD.setText(QuesAns.choices[currentQuestionIndex][3]);

    }

    void FinishQuiz(){
        String passStatus = "";
        if (score > totalQuestion*0.80){
            passStatus = "Congratulation! You're Tripti's Best Friend";

        }else{
            passStatus = "Sorry you Don't Seem to Know Tripti Well";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();


    }
    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

}