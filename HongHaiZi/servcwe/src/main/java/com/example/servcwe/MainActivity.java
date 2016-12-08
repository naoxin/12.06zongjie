 package com.example.servcwe;

 import android.content.ComponentName;
 import android.content.Context;
 import android.content.Intent;
 import android.content.ServiceConnection;
 import android.os.Bundle;
 import android.os.IBinder;
 import android.support.v7.app.AppCompatActivity;
 import android.util.Log;
 import android.view.View;

 import seriver.Seriver;

 public class MainActivity extends AppCompatActivity {

     Seriver seriver=new Seriver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("DemoLog", "Thread ID: " + Thread.currentThread().getId()+"++++++++++++++++++++++++++++++++++++++++++++++++");

        Log.i("DemoLog", "before test startService++++++++++++++++++++++++++++++++++++++++++++++++");
        Log.i("DemoLog", "after test startService++++++++++++++++++++++++++++++++++++++++++++++++");

    }
     public void kai(View v){
         Intent intent=new Intent(MainActivity.this, Seriver.class);
         startService(intent);
     }
     public void ting(View v){
         Intent intent=new Intent(MainActivity.this, Seriver.class);
         stopService(intent);
     }
     public void bkai(View v){
         Intent intent=new Intent(MainActivity.this, Seriver.class);
         bindService(intent, mServiceConnection,Context.BIND_AUTO_CREATE);
     }

     public void bting(View v){
         unbindService(mServiceConnection);
     }
     private ServiceConnection mServiceConnection = new ServiceConnection() {
         @Override
         public void onServiceConnected(ComponentName name, IBinder service) {
         }
         public void onServiceDisconnected(ComponentName name) {
         }
     };

}
