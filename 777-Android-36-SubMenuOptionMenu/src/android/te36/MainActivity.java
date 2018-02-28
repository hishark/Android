package android.te36;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.widget.*;
 

public class MainActivity extends Activity {

	//1.
	TextView tv;
	String []hobbies=new String[] {
			"��Ӿ","����","����"
	};
	
	//�Ժ�Ҫ���������boolean���� �ܶ�ʱ��һ��boolean���Խ���ܶ��鷳������ ����ǰ�������������ð�����
	boolean []bl=new boolean[] {
			false,false,false
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		tv=(TextView)this.findViewById(R.id.tv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		//3.�����Ӳ˵�
		SubMenu submenu=menu.addSubMenu("�Ա�");
		submenu.add(0,1,0,"��");
		submenu.add(0,2,0,"��");
		submenu.setGroupCheckable(0, true, true);
		//ʹ���Ϊ0��ֻ��ѡһ��
		
		menu.add("����");
		
		
		MenuItem exit=menu.add("�˳�");
		exit.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				//�˳�����Ӧ�ó���
				finish();
				return false;
			}
		});
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//4.��Ӧ�û�����Ĳ˵���
		String result="���ѡ���ǣ�";
		if(item.getTitle().equals("��")) {
			result+="�� ...";
		}else if(item.getTitle().equals("��")) {
			result+="Ů...";
		}else if(item.getTitle().equals("����")) {
			AlertDialog dialog=createDialog();
			dialog.show();
		}
		
		tv.setText(result);
		
		return super.onOptionsItemSelected(item);
	}

	private AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		
		 
		ad.setTitle("����ѡ��Ի���");
		ad.setIcon(R.drawable.ic_launcher);
	 	 
		 //������1.Ҫ��ʾ�ĸ����ݼ� 3.������ ����ĳһ����ѡ��ͻ���һ�������¼� ��������¼��ļ�����������
		 //�ڶ���������һ��boolean���� ������ʾ��ѡ���ѡ��״̬
		 //�ó������õĳ�ʼ״̬����������ѡ��
		 ad.setMultiChoiceItems(hobbies,bl,new DialogInterface.OnMultiChoiceClickListener() {
			
			 //������1.�Ի���2.ѡ�еĵڼ���3.ѡ�е��±�Ϊwhich����һ���Ƿ�ѡ��
			 //�����ѡ���״̬�ȵ�ѡ��ĸ���һЩ��Ҫ�ж���ѡ���˻���ûѡ�С�
			 //������������ʾ��ǰ��ѡ���ѡ��״̬��ѡ��orδѡ�У� 
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				//isChecked : True if the click checked the item, else false. 
				// TODO Auto-generated method stub
				//�洢���ս�����ַ���
				String result="";
				//����ַ���ÿ��һ��ѡ�����գ��̼��̼�
				
				bl[which]=isChecked;//��ǰ������ѡ�ѡ�У��±�which
				//blһֱ�����Ÿ�ѡ���ѡ��״̬
				
				//����һ��bl�����ж�һ��true����false
				for(int i=0;i<bl.length;i++) {
					if(bl[i]) {
						//���ѡ����
						result=result+" "+hobbies[i];
						//�Ͱ���ѡ��ӽ��ַ�����
					}
				}
				
				
				//������ʾǰ���ж�һ�� ����ַ���Ϊ�վ����ÿյ� ��������ݾ�ԭ�ⲻ��������ȥ
				if(result.length()>0) {
					tv.setText(result);
				}else {
					tv.setText("");
				}
					
				
				
			}
		} );
		

		
		ad.setPositiveButton("ȷ��",null );
		ad.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				tv.setText("");
				//��Ȼ����ȡ���͸ɴ�ص���ʼ״̬ ʡ�ó��ֲ��
				bl[0]=false;
				bl[1]=false;
				bl[2]=false;
				
			}
		}) ;
		
		
		alertDialog=ad.create();//ad��builder���� ����create()����AlertDialog����
		return alertDialog;
	}

}
