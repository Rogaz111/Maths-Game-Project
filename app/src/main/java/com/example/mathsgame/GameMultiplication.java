package com.example.mathsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class GameMultiplication extends AppCompatActivity {

    //Declaring TextViews
    TextView score;
    TextView life;
    TextView time;
    //Declare Textview and answer input field
    TextView question;
    EditText answer;
    //Declare Textview and answer input field
    Button ok;
    Button next;
    //Random Class import and containers for values
    Random random = new Random();
    int number1;
    int number2;
    int userAnswer;
    int realAnswer;
    int userScore = 0;
    int userLife = 3;

    //Must define time in miliseconds and final values must be caps
    CountDownTimer timer;
    //set Initial Time Value
    private static final long START_TIMER_IN_MILIS = 10000;
    //check if timer is running
    Boolean time_running;
    //Time left container
    long time_left_in_milis = START_TIMER_IN_MILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = findViewById(R.id.textViewScore);
        life = findViewById(R.id.textViewLifeNum);
        time = findViewById(R.id.textViewTimeVal);
        question = findViewById(R.id.textViewQuestion);
        answer = findViewById(R.id.editTextInputAns);
        ok = findViewById(R.id.buttonsubmit);
        next = findViewById(R.id.buttonNext);

        gameContinue();


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    System.out.println(Integer.parseInt(answer.getText().toString()));
                    userAnswer = Integer.parseInt(answer.getText().toString());
                    System.out.println(userAnswer);
                } catch (Exception e) {
                    System.out.println(e);
                }

                pauseTimer();

                if (userAnswer == realAnswer) {
                    userScore = userScore + 10;
                    score.setText(new StringBuilder().append("").append(userScore).toString());
                    question.setText(R.string.correct);

                } else {

                    userLife = userLife - 1;
                    life.setText(new StringBuilder().append("").append(userLife).toString());
                    question.setText(R.string.incorrect);
                }
                answer.setText("");


            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                resetTimer();
                answer.setText("");
                if (userLife <= 0) {
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GameMultiplication.this, Result.class);
                    intent.putExtra("score", userScore);
                    startActivity(intent);
                    finish();
                } else {
                    gameContinue();
                }

            }
        });

    }

    public void gameContinue() {
        number1 = random.nextInt(10);
        number2 = random.nextInt(10);
        realAnswer = number1 * number2;
        question.setText(number1 + " * " + number2);
        life.setText("" + userLife);
        startTimer();
        updateText();
    }


    public void startTimer() {
        timer = new CountDownTimer(time_left_in_milis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                time_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife = userLife - 1;
                life.setText("" + userLife);
                question.setText(R.string.timeranout);

            }
        }.start();
        time_running = true;
    }


    public void updateText(){

        int second = (int) (time_left_in_milis / 1000) % 60;

        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);
    }

    public void pauseTimer(){

        timer.cancel();
        time_running = false;
    }
    public void resetTimer(){

        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();

    }
}