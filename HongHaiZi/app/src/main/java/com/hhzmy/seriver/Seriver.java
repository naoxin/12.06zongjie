package com.hhzmy.seriver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * 1.作用
 * 2.作者：李延
 * 3.时间：2016、11、24
 */

public class Seriver extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getBaseContext(),"你好啊啊",Toast.LENGTH_LONG).show();
    }
}
