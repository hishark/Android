package jxnu.edu.cn.x3321.activity;

import jxnu.edu.cn.x3321.R;
import jxnu.edu.cn.x3321.R.layout;
import jxnu.edu.cn.x3321.R.menu;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.InterfaceImp.UserInterfaceImp;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class ContactActivity extends Activity {

	//1.�����Ա����
	ListView lv;

	//����<>��ֱ�ӷ�User�����Ǽ�Ҫ��SimpleAdapter�޷�������Ҫ�������ţ��Ͱ�User���HashMap
	//ArrayList <User> list=new ArrayList<User>();
	ArrayList <HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
	
	//����ӿ�
	UserInterface ui;
	
	//����sa������
	SimpleAdapter sa;
	
	//����һ����������ס��ѡ������һ����¼
	//��������������¸ü�¼��userid
	//��һ����ֵ��ʾΪ��
	//ֻҪ�����������Ϊ��ʱ�ͱ�ʾ�ɹ�ѡ����һ����¼
	public static int CLICK_ID=-10000;
	
	 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
		//2.��ʼ��
		init();
		
		//3.�����ݿ��user����ȡ�����е���ϵ�ˣ���װ��arraylist��ȥ
		//ʵ����һ���ӿڣ���UserInterfaceImp������ʵ�������ǵ�getApplicationContext()
		ui=new UserInterfaceImp(getApplicationContext());
		//ͨ���ӿ�ʵ�ֵķ������õ�������ϵ�˴��ArrayList 
		list=ui.getAllUsers();
		
		 
		
		
		//4.��list���ݷ�װ����������
		//�������б���ÿһ�еĲ����ļ����Լ�д��
		//�Ĳ���list��Ҫ���б�����ʾ����������
		//�������Щ����Ҫ��������ʾ��
		sa=new SimpleAdapter(this, list, R.layout.contactlistitem,
				new String[] {"name","phone"},new int[] {R.id.tv_Name,R.id.tvPhone});
	
		//�����������ص�listview����
	    lv.setAdapter(sa);
	    
	    //ע�������Ĳ˵�
	    registerForContextMenu(lv);
	    
	}

	
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//���֪����ǰѡ�е���listview�е���һ����¼��
		//ͨ������������Եõ���
		//AdapterContextMenuInfo info=(AdapterContextMenuInfo)item.getMenuInfo();
		//info.position�͸�����listview��ǰѡ�еļ�¼���±�
		
		AdapterContextMenuInfo info=(AdapterContextMenuInfo)item.getMenuInfo();
		
		//�õ��绰���� 
		String phone=(String)list.get(info.position).get("phone");
		
		//��ñ�ѡ�еļ�¼�Ĺؼ��֣�userid��
		CLICK_ID=(Integer)list.get(info.position).get("userid");
		
		
		//�ж�
		if(item.getTitle().equals("����")) {
			//ͨ����ʽ��ͼ����ϵͳ��绰����
			//ͨ����ʽ��ʽ������ϵͳ�Ĵ�绰�ĵ����
			//��ʽ����һ��Ҫ�ǵ���manifest����ע���������CALL_PHONEȨ��
			// <uses-permission android:name="android.permission.CALL_PHONE"/>
			
			//��ʽ����һ����ͼintent
			Intent intent=new Intent();
			intent.setAction("android.intent.action.CALL");
			//Ҳ��������
			//intent.setAction(Intent.ACTION_CALL);
			
			//�ѵ绰�����ַ���ת��(parse)��URI���󣬴���ϵͳ��绰�����
			//ע��һ�����tel:�ǹ̶��ģ�������Ϲ�ģ����˾�û������
			Uri uri=Uri.parse("tel:"+phone);
			
			//intent��һ������setData(Uri data)��ר����������uri����
			intent.setData(uri);
			
			//���������ͼ
			startActivity(intent);
			
		}else if(item.getTitle().equals("���Ͷ���")) {
			//ͨ����ʽ��ͼ������ϵͳ�����ŵĽ���
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_SENDTO);
			//�������Ҳok�������о������Ǹ���������������Ҫ��
			//intent.setAction("android.intent.action.SENDTO"); 
			
			//���绰�����ַ���ת����uri�����ٴ���ϵͳ���Ͷ��ŵ����
			//����intent��ͼ��Ϣ���ն˵ĵ绰������ʲô
			Uri uri=Uri.parse("smsto:"+phone);
			
			intent.setData(uri);
			//����ϢҲŪ��ȥ ���sms_bodyҲ�ǹ̶��� �����ԸĶ� �Ķ��˴򿪵���Ϣ�༭����Ͳ���������edittext���������Ϣ�ˣ�
			intent.putExtra("sms_body", "");
			
			startActivity(intent);
		}else if(item.getTitle().equals("�޸���ϵ��")) {
			
			//CLICKID�õ���һ��id�Ļ��Ϳ���ִ�����²���
			if(CLICK_ID!=-10000) {
				Intent intent=new Intent(ContactActivity.this,UpdateContactActivity.class);
				intent.putExtra("userid", CLICK_ID);
				startActivity(intent);
				finish();
				
			}
			
			
		}else if(item.getTitle().equals("ɾ����ϵ��")) {
			if(CLICK_ID!=-10000) {
				//ɾ����һ�����������
				//ɾ֮ǰҪ����һ���Ի��� ��ȷ��ɾ�������ѣ���
				AlertDialog ad=createDialog();
				ad.show();
			}
		}
		
		return super.onContextItemSelected(item);
	}




	private AlertDialog createDialog() {
		// TODO Auto-generated method stub
		AlertDialog dialog;
		AlertDialog.Builder ad=new AlertDialog.Builder(ContactActivity.this);
		
		ad.setTitle("ɾ����ϵ��");
		ad.setIcon(R.drawable.icon);
		ad.setMessage("ɾ��������ã�����");
		ad.setPositiveButton("ɾɾɾ", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//ɾ����һ����¼
				if(deleteItem()!=0) {
					
					//ɾ���ٵ����Լ��ͺ��� �����µ�ȡ��ȫ��������
					//���ǵ����Լ���û�б�ǩ����
					//����ֱ�ӵ���mycontactactivity
					//��ʵ�Ҿ���������ûɶ��˼ ��������һ��intent�ĸо��ܼ� 
					Toast.makeText(getApplicationContext(), "�������뿪����", Toast.LENGTH_SHORT).show();
				    Intent intent=new Intent(ContactActivity.this,MyContactActivity.class);
				    startActivity(intent);
				    finish();
				    
				}else {
					Toast.makeText(getApplicationContext(), "�������뿪��ʧ����", Toast.LENGTH_SHORT).show();
				    
				}
				
			}
		});
		ad.setNegativeButton("ȡ��", null);
		dialog=ad.create();
		return dialog;
	}




	protected int deleteItem() {
		// TODO Auto-generated method stub
		//����ҵ���߼���
		//��һ�����飺���ýӿ��еķ���
		//��ʵ����
		int bl=0;
		
		ui=new UserInterfaceImp(getApplicationContext());
		ui.deleteById(CLICK_ID);
		
		bl=1;
		return bl;
	}




	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.add("����");
		menu.add("���Ͷ���");
		menu.add("�޸���ϵ��");
		menu.add("ɾ����ϵ��");
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}




	private void init() {
		// TODO Auto-generated method stub
		lv=(ListView)this.findViewById(R.id.lv_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

}
