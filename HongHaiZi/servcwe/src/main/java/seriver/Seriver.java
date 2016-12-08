package seriver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
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
        Log.i("DemoLog", "TestService -> onBind, Thread ID: " + Thread.currentThread().getId()+"++++++++++++++++++++++++++++++++++++++++++++++++");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("DemoLog","TestService -> onCreate, Thread ID: " + Thread.currentThread().getId()+"++++++++++++++++++++++++++++++++++++++++++++++++");
        Toast.makeText(getBaseContext(),"你好啊啊1212121212",Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("DemoLog", "TestService -> onStartCommand, startId: " + startId + ", Thread ID: " + Thread.currentThread().getId()+"++++++++++++++++++++++++++++++++++++++++++++++++");
        Toast.makeText(this,"你好啊啊",Toast.LENGTH_LONG).show();

        ni();
        return super.onStartCommand(intent, flags, startId);
    }

    private void ni() {
        Toast.makeText(getApplicationContext(),"你好-=-=-=-啊啊",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDestroy() {
        Log.i("DemoLog", "TestService -> onDestroy, Thread ID: " + Thread.currentThread().getId()+"++++++++++++++++++++++++++++++++++++++++++++++++");
        super.onDestroy();
    }
    @Override
    public void onStart(Intent intent, int startId) {
        Log.i("DemoLog", "TestService -> onStart, Thread ID: " + Thread.currentThread().getId()+"++++++++++++++++++++++++++++++++++++++++++++++++");
        super.onStart(intent, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("DemoLog", "TestService -> onUbind, Thread ID: " + Thread.currentThread().getId()+"++++++++++++++++++++++++++++++++++++++++++++++++");
        return super.onUnbind(intent);
    }


    public class LBinder extends Binder {
        Seriver getService() {
            return Seriver.this;
        }
}}
