package com.example.a777.clienttest.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a777.clienttest.R;
import com.example.a777.clienttest.Util.DialogUtil;
import com.example.a777.clienttest.Util.LoginUtil;

public class loginActivity extends AppCompatActivity {
    /**
     * 定义控件
     */
    EditText et_username,et_password;
    Button bt_login;

    String userName,passWord;
    String strResult="";

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
                    //异步任务访问网络更方便啦
                    new AsyncTask<String,Void, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            // TODO Auto-generated method stub
                            /*
                             * 利用编写的LoginUtil工具类访问服务器端
                             */
                            LoginUtil lu=new LoginUtil();
                            strResult=lu.loginToServer(userName, passWord);

                            //这里返回的值传给下面的onPostExecute与主线程产生交互
                            return strResult;
                        }

                        protected void onPostExecute(String strResult) {
                            /**
                             * 和主线程进行交互
                             * 可以直接访问界面上的东西
                             * 对话框要在这里产生
                             */
                            DialogUtil.createDialog(strResult,loginActivity.this).show();
                        }
                    //execute里面为空，因为LoginUtil里已经定义好访问地址了
                    }.execute("");
                }
            }
        });
    }
}
