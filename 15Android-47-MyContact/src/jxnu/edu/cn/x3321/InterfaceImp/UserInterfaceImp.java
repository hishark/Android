package jxnu.edu.cn.x3321.InterfaceImp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.bean.User;
import jxnu.edu.cn.x3321.db.DatabaseHelper;

//�����ʵ���û��ӿڵ���
public class UserInterfaceImp implements UserInterface {
	//���ݿ�
	public static SQLiteDatabase dbInstance;
	Context context;

	//������캯����source�����ڶ�����
	public UserInterfaceImp(Context context) {		
		//super();
		//���ﱾ���и�super()��ʦɾ���� �Ǿ�ɾ����
		
		this.context = context;
		dbInstance=DatabaseHelper.openDatabase(context);
	}
	
	//��д�û��ӿ����еĲ��뷽��
	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		//���������һ����¼
		int bl=0;
		
		//��һ�ֲ��ҷ�ʽ
		
		String  sql=""
				+"insert into user (userName,phone)"
				+ " values ('"+user.getUserName()+"','"+user.getPhone()+"')";
		
		 
		//����ǵ�Ҫ��user.getUserName()��ǰ�����+!!
		
		dbInstance.execSQL(sql);
		
		
		//�ڶ��ֲ��ҷ�ʽ
		
		//һ���Ǹ�ʽ������ÿ���ֶζ�Ҫ����ֵ ����?������һ��ֵ��null��ʾû��ֵ
		//���ξ���ֵ��ʲô
		/*dbInstance.execSQL("insert into user values(null,?,?,?)",
				new String[] {user.getUserName(),user.getPhone(),user.getImageName()});
		*/
		
		
		//�����ֲ��ҷ�ʽ
		
		//�൱��buddle
		//�Ȱ����е�ֵ��װ��ContentValues��
		//ContentValues cv=new ContentValues();
		//key�Ǳ�������ֶ� ����д����
		//cv.put("userName", user.getUserName());
		//cv.put("phone", user.getPhone());
		//��user�������һ��user����
		//dbInstance.insert("user", null, cv); 
		
		bl=1;
		
		return bl;
	}

	
	//�÷������Եõ�������ϵ�˵����� ����������arraylist
	@Override
	public ArrayList<HashMap<String, Object>> getAllUsers() {
		// TODO Auto-generated method stub
		
		//����һ��ArrayList<HashMap<String, Object>>���͵����飡
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		
		//sql��򵥵�һ����ѯ���
		String sql="select * from user";
		
		//�����α�
		Cursor cursor=null;
		
		//�����Ǹ���������null����û�и���������
		//�α��ʼָ��-1��Ҳ������ˣ�ɶ��û�еĵط�
		//rawQuery��������ԭʼ��sql�����в�ѯ
		//sqlite�ж�sql�����з�װ������÷�װ��Ĳ�ѯ����query
		
		//��һ�ֲ��ҷ�ʽ
		cursor=dbInstance.rawQuery(sql, null);
		
		//�ڶ��ֲ��ҷ�ʽ
		//һ�α� ������ ����֮����sql����еĸ����޶�����
		//cursor=dbInstance.query("user", new String[] {"userid","userName","phone","imageName"}
		//, null, null, null, null, null);
				
				
		while(cursor.moveToNext()) {
			//����α�ָ��ĵط��������ݵĻ��Ͱ����ݸ��Ž�user��
			
			//����HashMap���͵�user
			HashMap<String, Object> user=new HashMap<String, Object>();
			
			//���α�ָ���һ�����ݸ��Ž�user
			//������cursor.getColumnIndex("userid")
			//�õ����ݿ�user�����α굱ǰָ���һ���е�����Ϊuserid��ֵ
			//���������Դ����� ע�����ֵ�����͸ı�get����ӵ����� 
			user.put("userid", cursor.getInt(cursor.getColumnIndex("userid")));
			user.put("name", cursor.getString(cursor.getColumnIndex("userName")));
			user.put("phone", cursor.getString(cursor.getColumnIndex("phone")));
			user.put("imageName", cursor.getString(cursor.getColumnIndex("imageName")));

			//�ٰ�hashmap���뵽list����
			list.add(user);
		}
		
		
		
		
		
		return list;
	}

	@Override
	public int deleteById(int userid) {
		// TODO Auto-generated method stub
		int bl=0;
		
		//��һ��ɾ����ʽ
		String sql="delete from user where userid="+userid;
		dbInstance.execSQL(sql);
		
		//�ڶ���ɾ����ʽ
		//�����ֶ�����  �����ֶ�ֵ
		//���ζ���ֶεĻ�����userid=? and username=?
		//һ����Ҫ�����ʺţ�������
		//dbInstance.delete("user", "userid=?", new String[] {String.valueOf(userid)});
		
		
		//������
		//dbInstance.execSQL("delete from user where userid=?", new String[] {String.valueOf(userid)});
		
		bl=1;
		return bl;
	}

	@Override
	public User getUserById(int userid) {
		// TODO Auto-generated method stub
		User user=null;
		String sql="select * from user where userid="+userid;
		Cursor cursor=null;
		//��һ�ֲ��ҷ�ʽ
		cursor=dbInstance.rawQuery(sql, null);
		
		//�ڶ��ֲ��ҷ�ʽ
		//cursor=dbInstance.query("user", new String[] {"userid","userName","phone","imageName"}
		//, "userid="+userid, null, null, null, null);
		
		while(cursor.moveToNext()) {
			user=new User();
			user.setUserid(cursor.getInt(cursor.getColumnIndex("userid")));
			user.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
			user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
			user.setImageName(cursor.getString(cursor.getColumnIndex("imageName")));
		}
		return user;
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		int bl=0;
		
		//��һ�ָ��·�ʽ
		
		String sql="update user set username='"+user.getUserName()+"',phone='"+user.getPhone()+"' where userid="
		+user.getUserid();
		dbInstance.execSQL(sql);
		
		
		
		//�ڶ��ָ��·�ʽ
		
		//�൱��buddle
		//�Ȱ����е�ֵ��װ��ContentValues��
		//ContentValues cv=new ContentValues();
		//key�Ǳ�������ֶ� ����д����
		//cv.put("userName", user.getUserName());
		//cv.put("phone", user.getPhone());
		//����user����idΪuser.getuserid();�ļ�¼
		//dbInstance.update("user", cv, "userid=?",
		//		new String[] {String.valueOf(user.getUserid())}); 
				
		
		bl=1;
		return bl;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getUsersByName(String name) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		
		//Ҫȡ��������¼ �����õȺ��ˣ���������
		String sql="select * from user where username like '%"+name+"%' ";//ģ������
		
        Cursor cursor=null;
		
		cursor=dbInstance.rawQuery(sql, null);
		
		
		while(cursor.moveToNext()) {
			  
			HashMap<String, Object> user=new HashMap<String, Object>();
			 
			user.put("userid", cursor.getInt(cursor.getColumnIndex("userid")));
			user.put("name", cursor.getString(cursor.getColumnIndex("userName")));
			user.put("phone", cursor.getString(cursor.getColumnIndex("phone")));
			user.put("imageName", cursor.getString(cursor.getColumnIndex("imageName")));

			//�ٰ�hashmap���뵽list����
			list.add(user);
		}
		
		return list;
	}

	 

}
