package android.pra09;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends Activity {

	//1.�����Ա����
	AutoCompleteTextView ac;
	MultiAutoCompleteTextView mac;
	String books[]=new String[] {
		"java�����ŵ�����",
		"Android�����ŵ�����",
		"java��������",
		"java����ѧ",
		"Android�������",
		"C���Դ����ŵ�����",
		"C++�����ŵ�����"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		init();
		//3.������ ����򵥵�arrayadapter
		ArrayAdapter<String> ma=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,books);
		//4.����������
		ac.setAdapter(ma);
		mac.setAdapter(ma);
		
		//��һ��һ��Ҫ�ǵ� ��Ȼ������Ч����
		mac.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}

	private void init() {
		// TODO Auto-generated method stub
		ac=(AutoCompleteTextView)this.findViewById(R.id.ac);
		mac=(MultiAutoCompleteTextView)this.findViewById(R.id.mac);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
