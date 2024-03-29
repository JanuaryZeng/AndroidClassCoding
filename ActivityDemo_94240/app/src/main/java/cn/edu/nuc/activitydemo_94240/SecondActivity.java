package cn.edu.nuc.activitydemo_94240;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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
