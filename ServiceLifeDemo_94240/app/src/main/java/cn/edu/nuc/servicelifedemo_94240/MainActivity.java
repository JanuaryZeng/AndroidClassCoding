package cn.edu.nuc.servicelifedemo_94240;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnQidong = null;
    private Button btnTingzhi= null;
    private Button btnBind = null;
    private Button btnUnBind = null;
    private Button btnGetData = null;

    private static final String TAG = "MySeries";

    private ServiceConnection serviceConnection = null;

    private MySeries.MyBinder myBinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnQidong = findViewById(R.id.btnQidong);
        btnTingzhi= findViewById(R.id.btnTingzhi);
        btnBind = findViewById(R.id.btnBind);
        btnUnBind = findViewById(R.id.btnUnBind);
        btnGetData = findViewById(R.id.btnGetData);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i(TAG, "MainActivity's onServiceConnected invoked");
                myBinder = (MySeries.MyBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "MainActivity's onServiceDisconnected invoked");
            }



        };

        final Intent intent = new Intent(MainActivity.this, MySeries.class);


        btnQidong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intent.setAction("cn.edu.MY_SERVICE");
                startService(intent);
            }
        });

        btnTingzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, serviceConnection, Service.BIND_ABOVE_CLIENT);
            }
        });

        btnUnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
            }
        });

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Count="+myBinder.getCount(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
