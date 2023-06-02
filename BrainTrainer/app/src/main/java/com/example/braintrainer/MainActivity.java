package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton,button0,button1,button2,button3,playAgainBtn;
    TextView sumText,textTimer;
    int score=0;
    int numberOfGamesPlayed=0;
    TextView textCorrect,textScore;
    int locationOfCorrectAnswer;
    ArrayList<Integer> answer=new ArrayList<>();

    public void chooseAnswer(View view){
        if(String.valueOf(locationOfCorrectAnswer).equals(view.getTag().toString())){
//            Log.i("message","You got it");
            textCorrect.setText("Correct !!");
            score++;

        }else{
//            Log.i("message","Wrong answer");
            textCorrect.setText("Wrong!!");
        }
        numberOfGamesPlayed++;
        textScore.setText(score+"/"+numberOfGamesPlayed);
        newQuestion();
    }

    public void playAgain(View view){
        score=0;
        numberOfGamesPlayed=0;
        textTimer.setText("30s");
        textScore.setText(score+"/"+numberOfGamesPlayed);
        newQuestion();
        playAgainBtn.setVisibility(View.INVISIBLE);
        CountDownTimer countDownTimer=new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textTimer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                textCorrect.setText("Done");
                playAgainBtn.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void newQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sumText.setText(a+ "+" +b);
        //location of your ans
        locationOfCorrectAnswer=rand.nextInt(4);
        //adding four answer to all four buttons
        answer.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answer.add(a+b);
            }else{
                //if we generate wrong ans and it is right ans
                int wrongAnswer=rand.nextInt(41);
                while(wrongAnswer==a+b){
                    wrongAnswer=rand.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }
        button0.setText(String.valueOf(answer.get(0)));
        button1.setText(String.valueOf(answer.get(1)));
        button2.setText(String.valueOf(answer.get(2)));
        button3.setText(String.valueOf(answer.get(3)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.go);
        sumText=findViewById(R.id.sumText);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        playAgainBtn=findViewById(R.id.playAgainbtn);
        textScore=findViewById(R.id.textScore);
        textCorrect=findViewById(R.id.textCorrect);
        textTimer=findViewById(R.id.textTimer);

        playAgain(findViewById(R.id.textScore));


    }
}