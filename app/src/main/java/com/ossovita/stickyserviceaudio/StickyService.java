package com.ossovita.stickyserviceaudio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
//Müzik çalma işlemini bir servis haline getiriyoruz
public class StickyService extends Service {
    MediaPlayer mp;
    private static final String TAG = "StickyService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //servis başlatıldığında onStartCommand çalışır.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand: ");
        mp = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mp.setLooping(true);
        mp.start();
        //servis sonlandırılmadığı sürece burada yapılan işlem devam eder, yani sticky'dir
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy: ");
        mp.stop();
        super.onDestroy();
    }
}
