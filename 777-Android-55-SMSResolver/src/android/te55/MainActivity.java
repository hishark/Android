package android.te55;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv;
	ContentResolver cr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	 
		tv=(TextView)this.findViewById(R.id.tv);
		cr=getContentResolver();
		//inbox是收件箱   outbox是发件箱  draft是草稿箱
		String strUri="content://sms/draft";
		Uri uri=Uri.parse(strUri);
		
		Cursor cursor=cr.query(uri, new String[] {"address","body"}, null, null, null);
	    
		String str="";
		while(cursor.moveToNext()) {
	    	for(int i=0;i<cursor.getColumnCount();i++) {
	    		String colName=cursor.getColumnName(i);
	    		str=str+"字段名称："+colName+"="+cursor.getString(i);
	    	}
	    	str=str+"\n";
	    	
	    }
		cursor.close();
		tv.setText(str);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
