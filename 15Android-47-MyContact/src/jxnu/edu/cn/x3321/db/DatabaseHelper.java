package jxnu.edu.cn.x3321.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	static final String DBName="contact";
	static final int Version=1;
	
	public static SQLiteDatabase dbInstance;

	
	
	/*context:�����Ķ���
	 * name:���������ʵ����ݿ���
	 * factory:�α깤���࣬����Ϊnull
	 * version:���ݿ�İ汾��,������>0������
	 */
	
	//������캯�����Զ����Ѵ���������Ķ������ø�
	public DatabaseHelper(Context context, String name,CursorFactory factory,int version) {
		//�������ݿ⣬ֻҪ���ݿ����Ͱ汾�Ų��䣬��ֻ�ᴴ��һ�����ݿ�
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	
	/*
	 * �ڴ������ݿ�֮���Զ�ִ��oncreate��������������ֻ�ᴴ��һ��
	 */
	
	//����������Դ��ķ��� 
	//��ʼ״̬������ɶҲû�У���ʦ�Լ�д��ȥ��
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		//����user���sql���
		String sql=""
				+ "create table user("
				+ "userid integer primary key autoincrement ,"
				+ "userName varchar(20),"
				+ "phone text,"
				+ "imageName text"
				+ ")";
		//ִ��sql���
		db.execSQL(sql);

	}

	/*
	 * �ڴ������ݿ�����version�����仯�����Զ�ִ��onUpgrade�������������޸ı�
	 */
	
	//����������Դ��ķ��� 
	//��ʼ״̬������ɶҲû�У� ���ﲻҪ���²�Ҫ�������Ҫ������˵
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	
	//�����������ʦ�Զ���ģ�
	//Ŀ�ľ��ǵõ������ݿ⣡
	public static SQLiteDatabase openDatabase(Context context){
		if(dbInstance==null){
			DatabaseHelper db=new DatabaseHelper(context,DBName,null,Version);
			dbInstance=db.getWritableDatabase();
		}
		
		return dbInstance;
		
	}

}
