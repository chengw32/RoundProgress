package com.test.custom.roundprogress;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RoundProgress viewById = (RoundProgress) findViewById(R.id.pg);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progress <= 98){
                        viewById.setProgress(progress+=2);
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (progress >= 100){
                                int progressBgColor = viewById.getProgressBgColor();
                                if (Color.BLUE == progressBgColor){
                                    viewById.setProgressBgColor(Color.GREEN);
                                    viewById.setProgressColor(Color.BLUE);
                                }else {
                                    viewById.setProgressBgColor(Color.BLUE);
                                    viewById.setProgressColor(Color.GREEN);
                                }
                                progress = 0 ;
                            }
                        }
                    }
                }).start();
            }
        });

    }
}
