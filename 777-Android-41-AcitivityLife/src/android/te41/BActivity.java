package android.te41;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class BActivity extends Activity {

	Button bt2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		init();
		bt2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(BActivity.this,MainActivity.class);
				
				setResult(102, intent);
				//这里不需要用这个 直接start就可以了
				
				//startActivity(intent);
			
				finish();
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		bt2=(Button)this.findViewById(R.id.bt2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.b, menu);
		return true;
	}

}
