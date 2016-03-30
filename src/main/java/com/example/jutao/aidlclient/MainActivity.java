package com.example.jutao.aidlclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jutao.aidl.IServiceAidl;
import com.example.jutao.aidl.Person;
import com.example.jutao.aidl.PersonAidl;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
  private EditText mEtnum1;
  private EditText mEtnum2;
  private EditText mEtRes;
  private Button mBuRes;
  private Button mBuPerson;
  MyListener myListener = new MyListener();

  IServiceAidl iServiceAidl;
  PersonAidl iPersonAidl;
  ServiceConnection conn = new ServiceConnection() {
    //绑定服务时
    @Override public void onServiceConnected(ComponentName name, IBinder service) {
      //拿到了远程的服务
      iServiceAidl = IServiceAidl.Stub.asInterface(service);
    }

    //当服务断开时
    @Override public void onServiceDisconnected(ComponentName name) {
      //回收资源
      iServiceAidl = null;
    }
  };
  ServiceConnection personConn = new ServiceConnection() {
    //绑定服务时
    @Override public void onServiceConnected(ComponentName name, IBinder service) {
      //拿到了远程的服务
      iPersonAidl = PersonAidl.Stub.asInterface(service);
    }

    //当服务断开时
    @Override public void onServiceDisconnected(ComponentName name) {
      //回收资源
      iPersonAidl = null;
    }
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
    //一启动应用程序就绑定服务
    bindService();
  }

  void initView() {
    mEtnum1 = (EditText) findViewById(R.id.id_ed1);
    mEtnum2 = (EditText) findViewById(R.id.id_ed2);
    mEtRes = (EditText) findViewById(R.id.id_res);
    mBuRes = (Button) findViewById(R.id.id_bures);
    mBuRes.setOnClickListener(myListener);
    mBuPerson = (Button) findViewById(R.id.id_bu_person);
    mBuPerson.setOnClickListener(myListener);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    unbindService(conn);
    unbindService(personConn);
  }

  private class MyListener implements View.OnClickListener {

    @Override public void onClick(View v) {
      switch (v.getId()) {
        case R.id.id_bures:
          int num1 = Integer.parseInt(mEtnum1.getText().toString().trim());
          int num2 = Integer.parseInt(mEtnum2.getText().toString().trim());
          try {
            int res = iServiceAidl.add(num1, num2);
            mEtRes.setText(res + "");
          } catch (RemoteException e) {
            e.printStackTrace();
            mEtRes.setText("ERROR");
          }
          break;
        case R.id.id_bu_person:
          try {
            List<Person> persons = iPersonAidl.add(new Person("奥巴马", 48));
            Log.d("Person", persons.toString());
          } catch (RemoteException e) {
            e.printStackTrace();
          }
          break;
      }
    }
  }

  private void bindService() {
    //远程调用
    //1、获取服务端
    Intent intent = new Intent();
    //Android 5.0之后不支持隐式意图，必须是显式意图来启动绑定服务
    intent.setComponent(
        new ComponentName("com.example.jutao.aidl", "com.example.jutao.aidl.RemoteService"));
    //第三个参数是一个flag，绑定时自动启动

    bindService(intent, conn, Context.BIND_AUTO_CREATE);

    Intent personIntent = new Intent();
    personIntent.setComponent(
        new ComponentName("com.example.jutao.aidl", "com.example.jutao.aidl.PersonService"));
    bindService(personIntent, personConn, BIND_AUTO_CREATE);
  }
}
