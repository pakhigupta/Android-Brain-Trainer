package com.pakhigupta.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    int locationOfCorrectAnswer;
    int score = 0;
    int totalQuestions = 0;

    public void generateQuestion() {

        answers.clear();

        Random rand = new Random();

        int a = rand.nextInt(41);
        int b = rand.nextInt(41);

        String sumString = Integer.toString(a) + "+" + Integer.toString(b);
        sumTextView.setText(sumString);

        locationOfCorrectAnswer = rand.nextInt(4); //gives 0/1/2/3

        int incorrectAnswer;
        for (int i=0; i<4; i++) {
            if(i == locationOfCorrectAnswer) {
                answers.add(a+b);
            }
            else {
                incorrectAnswer = rand.nextInt(81);
                while (incorrectAnswer == (a+b)) {
                    incorrectAnswer = rand.nextInt(81);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void chooseAnswer(View view) {

        totalQuestions++;

        Log.i("Button Tapped", view.getTag().toString());
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            resultTextView.setText("Correct!");

        }
        else {

            resultTextView.setText("Wrong!");

        }

        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestions));
        generateQuestion();

    }

    public void playAgain(View view) {

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);


        score = 0;
        totalQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);


        new CountDownTimer(30000 + 100,1000) {

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l/1000)+"s");

            }

            @Override
            public void onFinish() {

                //game finished
                timerTextView.setText("0s");
                resultTextView.setText("Score:" + scoreTextView.getText().toString());
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);


            }
        }.start();

        generateQuestion();

    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));//any view would do

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton= (Button) findViewById(R.id.start);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

    }
}
