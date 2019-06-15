package cn.edu.nuc.sendmessagesdemo_94240;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * 常用的系统级的服务有:
 * 1、SmsMessage
 * 2、TelephoneManager：获取网络状态、读取SIM卡信息
 * 3、AudioManager：调节音量的大小，设置静音
 * 4、AlarmManager：手机闹钟服务，全局定时器
 * 5、Vibrator：震动器
 */
public class MainActivity extends AppCompatActivity {

    private EditText number = null;
    private EditText message = null;
    private Button send = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = (EditText)findViewById(R.id.etNumber);
        message = (EditText)findViewById(R.id.etMessage);
        send = (Button)findViewById(R.id.btnSend);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SEND_SMS},
                1);

        send.setOnClickListener(new ButtonLinstener(true));
    }

    private class ButtonLinstener implements View.OnClickListener{
        private boolean flag = false;

        public ButtonLinstener(boolean flag){
            this.flag = flag;
        }

        @Override
        public void onClick(View v) {

            if(flag){
                String num = number.getText().toString().trim();
                String content = message.getText().toString().trim();

                SmsManager smsManager = SmsManager.getDefault();

                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                        0,
                        new Intent(),
                        0);

                List<String> msgs = smsManager.divideMessage(content);
                for(String msg: msgs){
                    smsManager.sendTextMessage(num,
                            null,
                            msg,
                            pendingIntent,
                            null);
                }

                Toast.makeText(MainActivity.this, "短信发送完毕", Toast.LENGTH_LONG).show();
            }else {

            }

        }
    }

}

