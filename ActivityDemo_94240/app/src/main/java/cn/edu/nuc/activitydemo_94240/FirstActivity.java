package cn.edu.nuc.activitydemo_94240;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    public static final String TAG = "FirstActivity";

    private Button btn1 = null;
    private Button btn2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.i(TAG, "onCreate: is invoked.");

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart: is invoked.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume: is invoked.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause: is invoked.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop: is invoked.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy: is invoked.");
    }

}
