package jxnu.edu.cn.x3321.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import jxnu.edu.cn.x3321.Interface.UserInterface;
import jxnu.edu.cn.x3321.InterfaceImp.UserInterfaceImp;

public class MyContentProvider extends ContentProvider {

	//继承ContentProvider
	//以统一的方式对外提供数据
	//这里定义了增删改查
	
	//1.为ContentProvider定义一个唯一的标识（URI），方便其他应用通过该标识能访问到MyContentProvider
	private static String auth = "jxnu.edu.cn.x3321";
	
	//2.把唯一的标识URI配置到androidmanifest里去
	//去androidmanifest里找
	
	//3.定义URI匹配器 用来判断用户接下来要做的是什么操作
	private static UriMatcher um=new UriMatcher(UriMatcher.NO_MATCH);
	//静态代码块 没有实例化会执行的？？？
	static {
		//外部只能调用这里已经定义好了的操作 
		//第三个参数就表示操作的编号！
		//第一个参数表示唯一的标识URI
		//第二个参数就是定义的操作的类型，字符串可以随便取名，不过一般见名知意
		um.addURI(auth, "all", 1);//查询数据库的所有用户
		um.addURI(auth, "#", 2);//查询userid为#的一条记录
		um.addURI(auth, "insert", 3);
	}
	
	//接口
	UserInterface ui;
	
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		
		ui=new UserInterfaceImp(getContext());
		Cursor cursor=null;
		switch(um.match(uri)){
		case 3://即insert 插入操作
		}
		
		
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		//1参数 就是auth+操作名 2就是想查询的表的字段名 3就是查询条件（where后的条件） 4就是好多条件 5就是排序
		//后面的参数一般都null着啦 复杂情况再考虑
		
		ui=new UserInterfaceImp(getContext());
		Cursor cursor=null;
		switch(um.match(uri)){
		case 1:
			//即all
			cursor=UserInterfaceImp.dbInstance.
			query("user", new String[] {"userid","userName","phone","imageName"}, null, null, null, null, null);
			break;
			
		case 2:
			//即#
			//外部访问者这样访问：    content://jxnu.edu.cn.x3321/2
			long id=ContentUris.parseId(uri);//提取到整数#
			
			if(selection==null||selection.equals("")) {
				selection="userid="+id;
			}else {
				selection+="and userid="+id;
			}
			cursor=UserInterfaceImp.dbInstance.
					//2参为null 即查找出所有列的
					query("user", null, selection, null, null, null, null);
					break;
		}
		
		//任何一个访问者只要调用query就可以得到Cursor对象 得到数据什么什么什么的
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
