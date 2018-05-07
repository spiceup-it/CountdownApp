package com.example.yuzzle.countdownapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView t1,t2,t3,t4,t5;
    private Handler handler = new Handler();
    private int days=0, hours=0, minutes=0, seconds=0, progressTime = 6000000;
    private static int time;
    private String daysString, hoursString, minutesString, secondsString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.counttext1);
        t2=findViewById(R.id.counttext2);
        t3=findViewById(R.id.counttext3);
        t4=findViewById(R.id.counttext4);
        t5=findViewById(R.id.linktext);


        time=6000000;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressTime > 0) {
                    progressTime = decreaseTime();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            days=progressTime/86400;
                            daysString=String.valueOf(days);
                            if(days < 10) {
                                daysString="0"+String.valueOf(days);
                            }
                            hours=(progressTime/3600)%24;
                            hoursString=String.valueOf(hours);
                            if(hours < 10) {
                                hoursString="0"+String.valueOf(hours);
                            }
                            minutes=(progressTime%3600)/60;
                            minutesString=String.valueOf(minutes);
                            if(minutes < 10) {
                                minutesString="0"+String.valueOf(minutes);
                            }
                            seconds=(progressTime%3600)%60;
                            secondsString=String.valueOf(seconds);
                            if(seconds < 10) {
                                secondsString="0"+String.valueOf(seconds);
                            }
                            t1.setText(daysString);
                            t2.setText(hoursString);
                            t3.setText(minutesString);
                            t4.setText(secondsString);
                        }
                    });
            }
        }
                private int decreaseTime() {
                    try{
                        Thread.sleep(1000);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    return --time;
            }
        }).start();
    }

    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.fifa.com"));
        startActivity(intent);
    }

}
