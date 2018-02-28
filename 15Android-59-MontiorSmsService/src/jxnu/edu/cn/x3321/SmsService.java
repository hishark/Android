package jxnu.edu.cn.x3321;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class SmsService extends Service {
	ContentResolver cr;
	
	

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
				//1.mainfest.xml中增加访问SMS的权限
				//2.定义一个Uri对象获得SMS中的数据
				Uri uri=Uri.parse("content://sms");
				//3.定义一个内容接收者ContentResolver对象接收contentProivder共享（提供）的数据
				cr=getContentResolver();
				cr.registerContentObserver(uri,true, new ContentObserver(new Handler()) {

					@Override
					public void onChange(boolean selfChange, Uri uri) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(),">>>你收到新的短信！",Toast.LENGTH_SHORT).show();
						Cursor c=cr.query(uri, new String[]{"address","body"},null , null, null);
						String str="";
						while(c.moveToNext()){
							for(int i=0;i<c.getColumnCount();i++){
								String colName=c.getColumnName(i);
								str=str+"字段名称："+colName+"="+c.getString(i);
							}
							str=str+"\n";			
						}
						System.out.println(str);
						c.close();
						super.onChange(selfChange, uri);
					}
					
				});
				//4.在mainfest.xml中注册serivce
		super.onCreate();
	}



	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
