package cn.edu.nuc.musicplayerdemo_94240;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class MusicSeries extends Service {

    //0x11表示停止状态 0x12表示播放状态 0x13表示暂停状态
    int statue = 0x11;
    //存放的是当前正在播放的歌曲对应的索引值记录
    int current = 0;
    private MediaPlayer mediaPlayer = null;
    private AssetManager assetManager = null;
    private String[] musics = new String[]{
            "life.mp3","poor.mp3"
    };

    private ServiceReceiver serviceReceiver = null;


    public MusicSeries() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = new MediaPlayer();
        assetManager = getAssets();

        serviceReceiver = new ServiceReceiver();
        //动态注册小serviceReceiver

        IntentFilter intentFilter = new IntentFilter(MainActivity.CONTROL);
        registerReceiver(serviceReceiver, intentFilter);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                current++;

                if(current >= 2){
                    current = 0;
                }

                Intent intent = new Intent(MainActivity.UPDATE);
                intent.putExtra("update", current);
                sendBroadcast(intent);
                prepareAndPlay(musics[current]);

            }
        });

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class ServiceReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int control = intent.getIntExtra("control", -1);

            switch (control){
                case 1:
                    if(statue == 0x12){
                        //播放器暂停播放
                        mediaPlayer.pause();
                        statue = 0x13;
                    }else if(statue == 0x11){
                        //播放器开始播放
                        prepareAndPlay(musics[current]);
                        statue = 0x12;
                    }else if(statue == 0x13){
                        mediaPlayer.start();
                        statue = 0x12;
                    }
                    break;
                case 2:
                    mediaPlayer.stop();
                    statue = 0x11;
                    break;
            }

            Intent intent1 = new Intent(MainActivity.UPDATE);
            intent1.putExtra("update",statue);
            intent1.putExtra("current",current);
            sendBroadcast(intent1);
        }
    }

    private void prepareAndPlay(String music){
        try {

            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(music);
            mediaPlayer.reset();
            mediaPlayer.setDataSource(
                    assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength());

            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
