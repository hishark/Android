package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	//1.定义成员引用变量(?)
	Button btCompute,btClear;
	EditText etNumber1,etNumber2,etResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.使成员引用变量指向界面上的元素对象（引用变量的初始化）
		init();
		//3.定义、注册事件监听器
		btCompute.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strNumber1,strNumber2;
				strNumber1=etNumber1.getText().toString().trim();
				strNumber2=etNumber2.getText().toString().trim();
				if(strNumber1.equals("")||strNumber1==null) {
					//吐司吐司
					Toast.makeText(getApplicationContext(), "请输入被加数", Toast.LENGTH_SHORT).show();
				}else if(strNumber2.equals("")||strNumber2==null) {
					Toast.makeText(getApplicationContext(), "请输入加数", Toast.LENGTH_SHORT).show();
				}else {
					double number1=Double.parseDouble(strNumber1);
					double number2=Double.parseDouble(strNumber2);
					double result=number1+number2;
					etResult.setText(String.valueOf(result));
				}
			}
		});
		
		btClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etNumber1.setText("");
				etNumber2.setText("");
				etResult.setText("");
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		btCompute=(Button)this.findViewById(R.id.bt_compute);//findviewbyid返回的是view view作为父类无法赋给子类button所以要进行强制转换
		btClear=(Button)this.findViewById(R.id.bt_clear);//与上同理
		etNumber1=(EditText)this.findViewById(R.id.et_num1);
		etNumber2=(EditText)this.findViewById(R.id.et_num2);
		etResult=(EditText)this.findViewById(R.id.et_result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
