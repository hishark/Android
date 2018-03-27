package com.example.a777.clienttest.Util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


/**
 * Created by 777 on 2018/3/26.
 */

public class LoginUtil {

    //模拟器用10.0.2.2，真机用无线局域网适配器ip——192.168.137.1
    private static String URL="http://10.0.2.2:8080/15Struts2-16-Struts-server/loginActivity.action?";
    public String loginToServer(String userName,String passWord){
        String result="";
        String strURL=URL+"userName="+userName+"&passWord="+passWord;

        /*
         * okhttp从这里开始不一样 也就几句话的功夫
         */
        HttpClient hc=new DefaultHttpClient();
        HttpPost request=new HttpPost(strURL);
        HttpResponse response=null;
        try {
            response=hc.execute(request);
            if(response.getStatusLine().getStatusCode()==200){
                //取得服务器传来的响应
                HttpEntity entity=response.getEntity();

                //这里仍然乱码，还要继续还原成json对象
                String strJson=EntityUtils.toString(entity,"utf-8");

                if(strJson!=null){
                    JSONObject jsonObject=new JSONObject(strJson);
                    result=jsonObject.getString("message").toString();
                }
            }else{
                result="没有网络或服务器连接不上";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
