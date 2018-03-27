package com.example.a777.okhttptest.Util;

import android.content.Entity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.a777.okhttptest.Activity.LoginActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 777 on 2018/3/27.
 */

public  class LoginUtil {

    //模拟器用10.0.2.2，真机用无线局域网适配器ip——192.168.137.1
    private static String URL="http://10.0.2.2:8080/15Struts2-16-Struts-server/loginActivity.action";

    public String result = "";

    public void getDataAsync(String userName,String passWord,final Handler handler)  {
        //实例化OkHttpClient
        OkHttpClient client = new OkHttpClient();
        //创建表单请求体
        FormBody.Builder formBody = new FormBody.Builder();

        /**
         * 传递键值对参数
         * key一定要和LoginActivityAction里面的变量同名！！！一定要同名！！！
         */
        formBody.add("userName",userName);
        formBody.add("passWord",passWord);

        //创建Request对象
        Request request = new Request.Builder()
                .url(URL)
                .post(formBody.build())
                .build();

        /**
         * Get的异步请求，不需要跟同步请求一样开启子线程
         * 但是回调方法还是在子线程中执行的
         * 所以要用到Handler传数据回主线程更新UI
         */
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            //回调的方法执行在子线程
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){

                    //得到Json键值对{“key”:“value”}
                    String temp = response.body().string();
                    Log.d("test",temp);

                    //利用Gson解析Json键值对
                    Test test = new Gson().fromJson(temp,Test.class);
                    result = test.getMessage();

                    //通过handler传递数据到主线程
                    Message msg = new Message();
                    msg.obj = result;
                    handler.sendMessage(msg);
                }else{
                    result="error";
                }
            }
        });
    }

    static class Test{
        private String message;
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }

}

