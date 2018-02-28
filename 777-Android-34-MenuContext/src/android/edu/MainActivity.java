package android.edu;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.�����Ա����
	EditText et1,et2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.Ϊ�����ı���ע�������Ĳ˵�����������ʾ�����Ĳ˵�(����Ҫ���ó����¼�  �ⶫ���Դ���)
		registerForContextMenu(et1);//Ϊet1ע��һ�������Ĳ˵� 
		registerForContextMenu(et2);//Ϊet2ע��һ�������Ĳ˵�
		//����ע��������Ĳ˵���ʲô ����ר�ŵķ��� onCreateContextMenu
	}

	
	
	//view�����¼�Դ menu���ǵ�ǰ�˵�
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		//����¼�ԴΪet1 ����ʾ���²˵�
		if(v==et1) {
			//������һ���ı�����ʾ�Ĳ˵�
			menu.add(0, 1, 0, "ȫ������");
			menu.add(0, 2, 0, "ȫ��ճ��");
			menu.add(0, 3, 0, "ȫ������");
			//��һ�����������groupid
			//�ڶ������������ڵı��itemid
			//������������ʾ��ʾ��˳��orderid
			//���ĸ�������ʾ��ʾ������title
		}else if(v==et2) {
			menu.add(0, 4, 0, "menu1");
			menu.add(0, 5, 0, "menu2");
			menu.add(0, 6, 0, "menu3");
		}
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case 1:
		case 2:
		case 3:
			et1.append("\n"+item.getTitle()+"������");
			//append��ʾ׷���ַ���  settext��ʾ����
			break;
		case 4:
		case 5:
		case 6:
			et2.append("\n"+item.getTitle()+"������");
			break;
		}
		  
		return super.onContextItemSelected(item);
	}

	private void init() {
		// TODO Auto-generated method stub
		et1=(EditText)this.findViewById(R.id.et1);
		et2=(EditText)this.findViewById(R.id.et2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
