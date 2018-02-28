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
		//���ַ����ļ�����ֱ�Ӱ󶨵ĵ�button��ť��ȥ�ˣ�����button�Ϻܷ��㰡 �е���js����¼���Ӧ
		
	}

	
	public void oper(View v) {//һ��Ҫ��v����
		switch(v.getId()) {
		case R.id.bt_compute:
			compute();
			break;
		case R.id.bt_clear:
			clear();
			break;
		}
		//ͨ��button�����õ�id���ж�һ�µ������ĸ�button�������ĸ�����Ӧ�ĸ�����
	}
	
	private void clear() {
		// TODO Auto-generated method stub
		etNumber1.setText("");
		//�ø�edittext�е����ݱ�Ϊ��
		etNumber2.setText("");
		etResult.setText("");
	}

	private void compute() {
		// TODO Auto-generated method stub
		String strNumber1,strNumber2;
		strNumber1=etNumber1.getText().toString().trim();
		strNumber2=etNumber2.getText().toString().trim();
		//�õ�edittext�е�����Ȼ��ת��Ϊ�ַ���Ȼ��ȥ�����ַ���ǰ��Ŀո�
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
			//��resultǿ��ת��ΪString����
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
