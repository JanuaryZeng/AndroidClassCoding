package cn.edu.nuc.musicplayerdemo_94240;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton ibtnStop = null;
    private ImageButton ibtnPlay = null;
    private TextView tvSongName = null;
    private TextView tvAuthor = null;

    private MyListener myListener = null;

    private ActivityReceived activityReceived = null;

    public static final String CONTROL = "cn.edu.nuc.musicplayerdemo_94240.MainActivity.control";
    public static final String UPDATE = "cn.edu.nuc.musicplayerdemo_94240.MainActivity.update";

    //0x11表示停止状态 0x12表示播放状态 0x13表示暂停状态
    private int statue = 0x11;
    private int current = 0;

    private String[] songNames = new String[]{"生活不止眼前的苟且","平凡之路"};
    private String[] authors = new String[]{"许巍","朴树"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibtnStop = findViewById(R.id.ibtnstop);
        ibtnPlay = findViewById(R.id.ibtnplay);
        tvSongName = findViewById(R.id.tvSongName);
        tvAuthor = findViewById(R.id.tvAuthor);

        myListener = new MyListener();

        ibtnStop.setOnClickListener(myListener);
        ibtnPlay.setOnClickListener(myListener);

        activityReceived = new ActivityReceived();
        IntentFilter intentFilter = new IntentFilter(UPDATE);
        registerReceiver(activityReceived,intentFilter);

        Intent intent = new Intent(MainActivity.this, MusicSeries.class);
        startService(intent);

    }

    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CONTROL);
            //1表示播放或暂停； 2表示停止
            switch (v.getId()){
                case R.id.ibtnplay:
                    intent.putExtra("control",1);
                    break;
                case R.id.ibtnstop:
                    intent.putExtra("control",2);
                    break;
            }

            //发送普通广播
            sendBroadcast(intent);

        }
    }

    private class ActivityReceived extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            statue = intent.getIntExtra("update",-1);
            current = intent.getIntExtra("current",-1);

            if(current > 0) {
                tvSongName.setText(songNames[current]);
                tvAuthor.setText(authors[current]);
            }
            switch (statue){
                case 0x11:
                    ibtnPlay.setImageResource(R.drawable.play);
                    break;
                case 0x12:
                    ibtnPlay.setImageResource(R.drawable.pause);
                    break;
                case 0x13:
                    ibtnPlay.setImageResource(R.drawable.play);
                    break;
            }
        }
    }

}
