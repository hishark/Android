package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{

	//1.�����Ա���ñ���(?)
	Button btCompute,btClear;
	EditText etNumber1,etNumber2,etResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.ʹ��Ա���ñ���ָ������ϵ�Ԫ�ض������ñ����ĳ�ʼ����
		init();
		//3.ע��(��)������
		btCompute.setOnClickListener(new Compute());//��button�¼�Դ��ע��һ�������������������¼�������������ô���
		btClear.setOnClickListener(new Clear());
		//�����ӵĻ�onCreate����Ĵ������ͻ�������� �о�����ÿ�һЩ ֮ǰֱ��������ע�ᶨ��ͺܶ���룬���ѿ�
	}

	
	//4.���������
	public class Compute implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String strNumber1,strNumber2;
			strNumber1=etNumber1.getText().toString().trim();//trimȥ���ַ���ǰ��Ŀո�
			strNumber2=etNumber2.getText().toString().trim();
			if(strNumber1.equals("")||strNumber1==null) {
				//��˾��˾
				Toast.makeText(getApplicationContext(), "�����뱻����", Toast.LENGTH_SHORT).show();
			}else if(strNumber2.equals("")||strNumber2==null) {
				Toast.makeText(getApplicationContext(), "���������", Toast.LENGTH_SHORT).show();
			}else {
				double number1=Double.parseDouble(strNumber1);
				double number2=Double.parseDouble(strNumber2);
				double result=number1+number2;
				etResult.setText(String.valueOf(result));
			}
		}
		 
	 }
	
	public class Clear implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			etNumber1.setText("");
			etNumber2.setText("");
			etResult.setText("");
		}
		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		btCompute=(Button)this.findViewById(R.id.bt_compute);
		btClear=(Button)this.findViewById(R.id.bt_clear);
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
