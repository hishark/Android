package android.te32;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.view.Menu;
import android.widget.*;
import android.widget.TabHost.TabSpec;

//extendsҪ�ı���
//TabActivity������ʹ���� ����Ƭ������
public class MainActivity extends TabActivity {

	//1.�����Ա����
	TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_host);
		//2.��ʼ��
		tabHost=getTabHost();
		//3.�����ĸ�tab��ǩҳ
		TabSpec tab1=tabHost.newTabSpec("tab1");
		tab1.setContent(R.id.ltab1);
		tab1.setIndicator("��ϵ��");
		
		TabSpec tab2=tabHost.newTabSpec("tab2");
		tab2.setContent(R.id.ltab2);
		tab2.setIndicator("��Ϣ");
		
		TabSpec tab3=tabHost.newTabSpec("tab3");
		tab3.setContent(R.id.ltab3);
		tab3.setIndicator("����");
		
		TabSpec tab4=tabHost.newTabSpec("tab4");
		tab4.setContent(R.id.ltab4);
		tab4.setIndicator("���ͨ��");
		
		//�����ݼӵ�tabHost��ȥ
		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.addTab(tab3);
		tabHost.addTab(tab4);
	
				
		
	}

 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
