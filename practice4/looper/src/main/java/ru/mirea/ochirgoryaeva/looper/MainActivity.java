package ru.mirea.ochirgoryaeva.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    MyLooper myLooper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLooper = new MyLooper();
        myLooper.start();
    }
    public void onClickBtn(View view) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("KEY", "mirea");
        msg.setData(bundle);
        if (myLooper != null) {
            myLooper.handler.sendMessage(msg);
        }

        final int[] delayTime = {1};
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Log.d("Looper","Младший логист Очир-Горяева И.А., "+ delayTime[0]);
                    TimeUnit.SECONDS.sleep(delayTime[0]);
                    delayTime[0] +=1;
                    TimeUnit.SECONDS.sleep(delayTime[0]);
                    Log.d("Looper","Младший логист Очир-Горяева И.А., "+ delayTime[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

}