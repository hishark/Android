package jxnu.edu.cn.x3321;

import android.os.Bundle;

import java.util.ArrayList;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;
 

public class MainActivity extends Activity {

	//1.�����Ա����
	EditText etNumber1,etNumber2,etResult;
	Button btCompute,btClear;
	Spinner sp;
	ArrayList<String> al=new ArrayList<String>();
	MyAdapter ma;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.׼������
		al.add("+");//�ÿո�ſ��Ǹ�spinner ������trimȥ���ո�
		al.add("-");
		al.add("*");
		al.add("/");
		
		//4.�Զ�����������װ����
		ma=new MyAdapter(getApplicationContext(),al);
		
		//5.������������sp��
		sp.setAdapter(ma);
		
		//6.����ע�������
        btCompute.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strNumber1,strNumber2;
				String symbol;//���ű���
				
				TextView tv=(TextView)sp.getChildAt(0);
				//�õ�spinner��ǰ�û�ѡ�����һ��TextView
				
				symbol=tv.getText().toString().trim();
				//�õ�+-*/���� �ǵ�ȥ���ո�
				
				strNumber1=etNumber1.getText().toString().trim();
				strNumber2=etNumber2.getText().toString().trim();
				if(strNumber1.equals("")||strNumber1==null) {
					//��˾��˾
					Toast.makeText(getApplicationContext(), "�����������1", Toast.LENGTH_SHORT).show();
				}else if(strNumber2.equals("")||strNumber2==null) {
					Toast.makeText(getApplicationContext(), "�����������2", Toast.LENGTH_SHORT).show();
				}else {
					double number1=Double.parseDouble(strNumber1);
					double number2=Double.parseDouble(strNumber2);
					double result=0;//�ǵó�ʼ��Ϊ0��Ȼ�ᱨ����
					
					//��������Ҫ�жϷ�����ʲô
					//if(symbol=="+")���������ж��ַ������ ������==�����ж϶����ǲ���һ���� 
					//Ҳ�����жϵ�ַ�ǲ���һ���� ��Ȼ���symbol��+�ĵ�ַ�϶���һ�� ���Բ�Ӧ�������ж�
					//����Ҫ�жϵ�����������ֵ�ǲ��ǵ���ĳ��ֵ  ����Ӧ����String.equals()����
					if(symbol.equals("+")) {
						result=number1+number2;
					}else if(symbol.equals("-")){
						result=number1-number2;
					}else if(symbol.equals("*")){
						result=number1*number2;
					}else if(symbol.equals("/")){
						result=number1/number2;
					}
					//���ý����ʾ
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
		btCompute=(Button)this.findViewById(R.id.bt_compute);
		btClear=(Button)this.findViewById(R.id.bt_clear);
		etNumber1=(EditText)this.findViewById(R.id.et_num1);
		etNumber2=(EditText)this.findViewById(R.id.et_num2);
		etResult=(EditText)this.findViewById(R.id.et_result);
		sp=(Spinner)this.findViewById(R.id.sp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
