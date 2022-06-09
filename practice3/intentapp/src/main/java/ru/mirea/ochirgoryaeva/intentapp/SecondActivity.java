package ru.mirea.ochirgoryaeva.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tvOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvOut = (TextView) findViewById(R.id.textView);
        String text = (String) getIntent().getSerializableExtra("key");
        tvOut.setText(text);
    }
    private String TAG = SecondActivity.class.getSimpleName();
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "SecondActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "SecondActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "SecondActivity: onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "SecondActivity: onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "SecondActivity: onDestroy()");
    }

    public void onClickFirstActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}