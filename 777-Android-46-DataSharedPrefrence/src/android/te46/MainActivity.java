package android.te46;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	
	//1.�����Ա����
	EditText etUserName,etPassword;
	CheckBox cbRemember,cbShow;
	Button btLogin,btCancel;
	
	//���������ר����������xml�ĵ��ģ��Ѿ���װ���˵� 
	//������Ϊһ���ܵ�
	SharedPreferences sp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.ͨ��sp���󴴽����ߴ�/data/data/SharedPreferences/user.xml
		//user����Բ���.xml��׺,ȱʡ����.xml��׺��ֻҪ�����������xml�ļ�����ɶ�ͺ���
		//��һ�����������ļ������ڶ��������ǲ������xml�ĵ���ģʽ ��
		sp=this.getSharedPreferences("user",Context.MODE_APPEND);
		
		//4.�м��мǣ�һ���������Ҫ��user.xml�ĵ��ж�ȡ��ʼ��������Ϣ
		//�������ʱxml�Ͳ����ڵĻ���isRemember��ֵ����false
		//������ڵĻ����ͷ���xml���Ѵ��ڵ�isRemember��ֵ
		//�ڶ��������Ľ��ͣ�Value to return if this preference does not exist.
		boolean isRemember=sp.getBoolean("isRemember",false);
		
		//��xml�ļ��Ѵ��ڲ���isRemember��ֵΪtrueʱ��������Ķ���
		if(isRemember) {
			
			etUserName.setText(sp.getString("name", ""));//xml�ļ��в�����name��ֵ�ͷ���""�����ھͷ������е�ֵ
			etPassword.setText(sp.getString("password", ""));//xml�ļ��в�����password��ֵ�ͷ���""�����ھͷ������е�ֵ
			cbShow.setChecked(sp.getBoolean("isShow", false));//xml�ļ��в�����isShow��ֵ�ͷ���false�����ھͷ������е�ֵ
			cbRemember.setChecked(sp.getBoolean("isRemember", false));//xml�ļ��в�����isRemember��ֵ�ͷ���false�����ھͷ������е�ֵ
			
			//�����Ѿ���֮ǰ���µ������������ú���
			//Ҳ����ʵ���˼�ס�û��Ĳ���
			
			
			//��δ���û���κ�ʵ���ô�
			
			/*//���������ж�һ���Ƿ�������ʾ������
			//���isShow�Ѿ���ѡ�� ��ô��������ʾ����
			if(sp.getBoolean("isShow", false)) {
				
				etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
				 
			}*/
			
			
		}
		
		
		//5.����ע���¼�������
		cbRemember.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//sp��һ���ܵ������ڵ�������ܵ���edit�����õ�һ��editor����
				//ͨ��editor���������д��user.xml��
				//Ҳ����˵���ܵ�����ͷ��һͷ��edit��һͷ��user.xml����edit��user.xml�����ƶ�����ok����
				Editor edit=sp.edit();
				
				//6.����Ҫ����û����������ֵ
				String userName=etUserName.getText().toString().trim();
				String password=etPassword.getText().toString().trim();
				
				//Ȼ���жϡ���ס�û���ѡ���Ƿ�ѡ�У������ѡ���˾�������ס�û���һϵ�в���
				//Ҳ���ǰ�����д��user.xml����ס�û�����ס�û���һ�����ã�
				if(cbRemember.isChecked()) {
					//������ȫ���ƽ�edit ����user.xml
					edit.putString("name", userName);
					edit.putString("password", password);
					
					//���������������͵ı�������ʶ������ѡ���״̬ 
					edit.putBoolean("isRemember", true);
					edit.putBoolean("isShow", cbShow.isChecked());
					
					//������Ҫһ�����ύ
					edit.commit();
					
				}else {
					edit.putString("name", "");
					edit.putString("password", "");
					edit.putBoolean("isRemember", false);
					edit.putBoolean("isShow", cbShow.isChecked());
					
					//������Ҫһ���Ե��ύ
					edit.commit();
				}
			}
		});
		
		cbShow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Editor edit=sp.edit();
				
				if(cbShow.isChecked()) {
					
					 
					edit.putBoolean("isShow", true);
					edit.commit();
					
					//etpassword����༭��������ķ�ʽ��ʾ���� 
					etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
					
					
				}else {
					edit.putBoolean("isShow", false);
					edit.commit();
					
					//etpassword����༭��������ķ�ʽ��ʾ����
					etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
					
				}
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		etUserName=(EditText)this.findViewById(R.id.et_userName);
		etPassword=(EditText)this.findViewById(R.id.et_Password);
		cbRemember=(CheckBox)this.findViewById(R.id.cd_remember);
		cbShow=(CheckBox)this.findViewById(R.id.cb_show);
		btLogin=(Button)this.findViewById(R.id.bt_login);
		btCancel=(Button)this.findViewById(R.id.bt_cancel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
