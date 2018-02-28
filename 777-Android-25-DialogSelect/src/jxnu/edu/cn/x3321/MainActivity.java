package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	//1.�����Ա����
	EditText etDegree,etHobbies;
	Button btDegree,btHobbies;
	
	String []degrees=new String[] {
			"����","˶ʿ","��ʿ"
	};
	String []hobbies=new String[] {
			"��Ӿ","����","����"
	};
	
	//�Ժ�Ҫ���������boolean���� �ܶ�ʱ��һ��boolean���Խ���ܶ��鷳������ ����ǰ�������������ð�����
	boolean []bl=new boolean[] {
			false,false,false
	};
	//��������ʾ��ѡ���ѡ��״̬
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.����ע���¼�������
		btDegree.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				AlertDialog ad=createSingleChoiceDialog();
				//�Զ�����һ��createSingleDialog�����������Ի��� ����Ĵ���ͼ�ֱ��һЩ
				ad.show();
			}
		});
		btHobbies.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog ad=createMultiChoiceDialog();//�Զ����createDialog���������Ի���
				ad.show();
			}
		});
		
	}

	protected AlertDialog createMultiChoiceDialog() {
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
					etHobbies.setText(result);
				}else {
					etHobbies.setText("");
				}
					
				
				
			}
		} );
		

		
		ad.setPositiveButton("ȷ��",null );
		ad.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				etHobbies.setText("");
				//��Ȼ����ȡ���͸ɴ�ص���ʼ״̬ ʡ�ó��ֲ��
				bl[0]=false;
				bl[1]=false;
				bl[2]=false;
				
			}
		}) ;
		
		
		alertDialog=ad.create();//ad��builder���� ����create()����AlertDialog����
		return alertDialog;
	}

	protected AlertDialog createSingleChoiceDialog() {
		// TODO Auto-generated method stub
		AlertDialog alertDialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(MainActivity.this);
		
		//ͷ����
		ad.setTitle("ѧλѡ��Ի���");
		ad.setIcon(R.drawable.ic_launcher);
		
		//�м䲿��
		//ר�ŵĶԻ���ķ���
		//������1.Ҫ��ʾ�ĸ����ݼ� 2.ȱʡ�������һ�ѡ�� 3.������ ����ĳһ����ѡ��ͻ���һ�������¼� ��������¼��ļ�����������
		ad.setSingleChoiceItems(degrees, 0, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//������1.��ǰ�Ի���  2.��������һ��ѡ�� which�����±�
				etDegree.setText(degrees[which]);
			}
		});
		

		
		ad.setPositiveButton("ȷ��",null );
        ad.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				etDegree.setText("");
			}
		}) ;
		
		//���϶��Ƕ�ad�������̣����Լ���ô��⣩�����������ǰ����õ�ad���д���֮��Ž�alertDialog���棡
		//ע��AlertDialog��AlertDialog.Builder��������ͬ�Ķ��� ע��ע��^(*��(oo)��)^
        
		alertDialog=ad.create();//ad��builder���� ����create()����AlertDialog����
		return alertDialog;
	}

	private void init() {
		// TODO Auto-generated method stub
		etDegree=(EditText)this.findViewById(R.id.et_degree);
		etHobbies=(EditText)this.findViewById(R.id.et_hobbies);
		btDegree=(Button)this.findViewById(R.id.bt_degree);
		btHobbies=(Button)this.findViewById(R.id.bt_hobbies);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
