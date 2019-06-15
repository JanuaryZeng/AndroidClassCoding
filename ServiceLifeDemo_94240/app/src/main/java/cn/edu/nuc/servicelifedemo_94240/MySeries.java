package cn.edu.nuc.servicelifedemo_94240;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.util.Printer;

import java.security.PrivateKey;

public class MySeries extends Service {

    private static final String TAG = "MySeries";
    private boolean quit = false; //计数子线程的循环控制变量
    private int count = 0;
    private MyBinder mybinder = new MyBinder();

    public class MyBinder extends Binder{

        public MyBinder() {
            Log.i(TAG,"MyBinder's construct invoked.");
        }

        public int getCount(){
            return count;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        Log.i(TAG,"MySeries's onCreate invoked.");
        super.onCreate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!quit){
                    try {//快捷键ctrl & alt & T
                        count++;
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intent1 = new Intent(MySeries.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(MySeries.this,0,intent1,0);

        Notification.Builder builder = new Notification.Builder(this);

        builder. setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent);

        Notification notification = builder.build();


        startForeground(44, notification);
        Log.i(TAG,"MySeries's onStartCommand invoked.");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"MySeries's onDestroy invoked.");
        super.onDestroy();
    }

    @Nullable
    @Override
    //IBinder是一个接口，它用于实现Service和其他组建的数据交互
    //该接口有一个实现类Binder，通常我们会写一个Binder类得子类，让这个子类的对象来具体实现
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"MySeries's onBind invoked.");
        return mybinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"MySeries's onUnbind invoked.");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG,"MySeries's onRebind invoked.");
        super.onRebind(intent);
    }
}
