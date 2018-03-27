package com.example.a777.okhttptest.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a777.okhttptest.R;
import com.example.a777.okhttptest.Util.DialogUtil;
import com.example.a777.okhttptest.Util.LoginUtil;

public class LoginActivity extends AppCompatActivity {
    /**
     * 定义控件
     */
    EditText et_username,et_password;
    Button bt_login;

    String userName,passWord;

    String strResult="";

    //Handler用来从子线程往主线程传输数据
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            strResult = msg.obj.toString();
            DialogUtil.createDialog(strResult,LoginActivity.this).show();

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = (EditText)this.findViewById(R.id.username);
        et_password = (EditText)this.findViewById(R.id.password);
        bt_login = (Button)this.findViewById(R.id.login);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = et_username.getText().toString().trim();
                passWord = et_password.getText().toString().trim();
                if(userName==null||userName.equals("")||passWord==null||passWord.equals("")){
                    Toast.makeText(getApplicationContext(),"请输入完整的登录信息", Toast.LENGTH_SHORT).show();
                }else{
                    //实例化登录工具类
                    LoginUtil lu = new LoginUtil();

                    //把handler对象传到子线程去，然后子线程通过handler把数据发回主线程，更新UI
                    lu.getDataAsync(userName,passWord,handler);
                }
            }
        });
    }
}
