package cn.edu.nuc.handlerdemo_94240;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //1、声明一个TextView的对象
    private TextView random_tv = null;
    //2、声明一个Handler的对象
    private Handler myhandler = null;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random_tv.findViewById(R.id.random_tv);

        random_tv.setText("产生随机数："+Math.random());

        myhandler = new Handler(){
            public void handleMessage(Message message){
                if(message.what == 0x11){
                    random_tv.setText("产生的随机数：" + Math.random());
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){

                        Message message = new Message();
                        message.what = 0x11;
                        myhandler.sendMessage(message);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

    }
}
