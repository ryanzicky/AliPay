package com.example.administrator.zhifubao;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Administrator on 2016/7/13 0013.
 */
public class PayService extends Service {

    //2.把定义的中间人对象返回
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    //支付宝支付的方法
    public boolean pay(String name,String pwd,int money){

        System.out.println("验证用户名和密码");
        System.out.println("验证手机是否携带病毒");
        System.out.println("调用C语言 做一些加密处理");
        if("abc".equals(name) && "123".equals(pwd) && money < 5000){
            return true;
        }else{
            return false;
        }
    }

    //1.定义中间人对象
    private class MyBinder extends Iservice.Stub{
        @Override
        public boolean callPay(String name, String pwd, int money) {
            return pay(name,pwd,money);
        }
    }
}
