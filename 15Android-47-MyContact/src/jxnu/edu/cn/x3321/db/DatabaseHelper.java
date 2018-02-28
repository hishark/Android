package jxnu.edu.cn.x3321.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	static final String DBName="contact";
	static final int Version=1;
	
	public static SQLiteDatabase dbInstance;

	
	
	/*context:上下文对象
	 * name:创建、访问的数据库名
	 * factory:游标工厂类，可以为null
	 * version:数据库的版本号,必须是>0的整数
	 */
	
	//这个构造函数会自动提醒创建，里面的东西不用改
	public DatabaseHelper(Context context, String name,CursorFactory factory,int version) {
		//创建数据库，只要数据库名和版本号不变，它只会创建一次数据库
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	
	/*
	 * 在创建数据库之后将自动执行oncreate方法来创建表，它只会创建一次
	 */
	
	//创建这个类自带的方法 
	//初始状态方法里啥也没有，老师自己写进去的
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		//创建user表的sql语句
		String sql=""
				+ "create table user("
				+ "userid integer primary key autoincrement ,"
				+ "userName varchar(20),"
				+ "phone text,"
				+ "imageName text"
				+ ")";
		//执行sql语句
		db.execSQL(sql);

	}

	/*
	 * 在创建数据库后，如果version发生变化，将自动执行onUpgrade方法来创建或修改表
	 */
	
	//创建这个类自带的方法 
	//初始状态方法里啥也没有， 这里不要更新不要管这个，要更新再说
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	
	//这个方法是老师自定义的！
	//目的就是得到该数据库！
	public static SQLiteDatabase openDatabase(Context context){
		if(dbInstance==null){
			DatabaseHelper db=new DatabaseHelper(context,DBName,null,Version);
			dbInstance=db.getWritableDatabase();
		}
		
		return dbInstance;
		
	}

}
