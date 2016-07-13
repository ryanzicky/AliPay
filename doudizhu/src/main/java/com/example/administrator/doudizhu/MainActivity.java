package com.example.administrator.doudizhu;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    private MyConn conn;
    private Iservice iservice;//中间人对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.调用bindService 获取定义的中间人对象
        Intent intent = new Intent();
        intent.setAction("com.example.alipay");
        intent.setPackage("com.example.administrator.zhifubao");
        conn = new MyConn();

        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public void click(View view){

        try{
            boolean result = iservice.callPay("abc","123",100);
            if(result){
                Toast.makeText(getApplicationContext(),"成功",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"失败",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class MyConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //获取中间人对象
            iservice = Iservice.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        //当Activity销毁的时候解绑服务
        unbindService(conn);
        super.onDestroy();
    }
}
