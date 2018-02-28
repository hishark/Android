package android.te50;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.*;

public class MainActivity extends Activity {

	//1.
	EditText et;
	Button bt;
	WebView wv;
	String urlStr="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.
		init();
		
		//3.定义注册事件监听器
		bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				urlStr="http://";
				String url=et.getText().toString().trim();
				if(url==null||url.equals("")) {
					Toast.makeText(getApplicationContext(), "请输入正确的网址",Toast.LENGTH_SHORT).show();
					
				}else{
					urlStr=urlStr+url;
					//在androidmanifest中添加访问internet权限
					//-----第一种加载网页方式------
					wv.loadUrl(urlStr);
					 
					
					//-----第二种加载网页方式------
					//通过隐式意图来启动
					//Uri uri=Uri.parse(urlStr);
					//Intent intent=new Intent(Intent.ACTION_VIEW,uri);
					//startActivity(intent);
				}
			}
		});
		
		//-----第三种加载网页方式------
		//"<text/html>"以这种方式打开
		/*wv.loadData("<html>"+
		              "<body>"+
				         "<a href=\"http://www.baidu.com\">www.baidu.com</a>"+
		              "</body>"+
				    "</html>", "<text/html>", "utf-8");*/
	
	}

	private void init() {
		// TODO Auto-generated method stub
		et=(EditText)this.findViewById(R.id.et);
		bt=(Button)this.findViewById(R.id.bt);
		wv=(WebView)this.findViewById(R.id.wv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
