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

//这个是实现用户接口的类
public class UserInterfaceImp implements UserInterface {
	//数据库
	public static SQLiteDatabase dbInstance;
	Context context;

	//这个构造函数在source倒数第二个！
	public UserInterfaceImp(Context context) {		
		//super();
		//这里本来有个super()老师删掉了 那就删掉吧
		
		this.context = context;
		dbInstance=DatabaseHelper.openDatabase(context);
	}
	
	//重写用户接口类中的插入方法
	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		//往表里插入一条记录
		int bl=0;
		
		//第一种查找方式
		
		String  sql=""
				+"insert into user (userName,phone)"
				+ " values ('"+user.getUserName()+"','"+user.getPhone()+"')";
		
		 
		//这里记得要在user.getUserName()的前后加上+!!
		
		dbInstance.execSQL(sql);
		
		
		//第二种查找方式
		
		//一参是格式！假设每个字段都要插入值 先用?来代表一个值，null表示没有值
		//二参决定值是什么
		/*dbInstance.execSQL("insert into user values(null,?,?,?)",
				new String[] {user.getUserName(),user.getPhone(),user.getImageName()});
		*/
		
		
		//第三种查找方式
		
		//相当于buddle
		//先把所有的值封装到ContentValues中
		//ContentValues cv=new ContentValues();
		//key是表里面的字段 不能写错了
		//cv.put("userName", user.getUserName());
		//cv.put("phone", user.getPhone());
		//往user里面插入一个user对象
		//dbInstance.insert("user", null, cv); 
		
		bl=1;
		
		return bl;
	}

	
	//该方法可以得到所有联系人的数据 返回类型是arraylist
	@Override
	public ArrayList<HashMap<String, Object>> getAllUsers() {
		// TODO Auto-generated method stub
		
		//定义一个ArrayList<HashMap<String, Object>>类型的数组！
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		
		//sql最简单的一条查询语句
		String sql="select * from user";
		
		//定义游标
		Cursor cursor=null;
		
		//二参是附加条件，null就是没有附加条件咯
		//游标最开始指向-1，也就是最顶端，啥都没有的地方
		//rawQuery就是用最原始的sql语句进行查询
		//sqlite有对sql语句进行封装，如果用封装后的查询就是query
		
		//第一种查找方式
		cursor=dbInstance.rawQuery(sql, null);
		
		//第二种查找方式
		//一参表 二参列 三参之后都是sql语句中的各种限定条件
		//cursor=dbInstance.query("user", new String[] {"userid","userName","phone","imageName"}
		//, null, null, null, null, null);
				
				
		while(cursor.moveToNext()) {
			//如果游标指向的地方存在数据的话就把数据给放进user中
			
			//定义HashMap类型的user
			HashMap<String, Object> user=new HashMap<String, Object>();
			
			//把游标指向的一条数据给放进user
			//这里用cursor.getColumnIndex("userid")
			//得到数据库user表中游标当前指向的一行中的列名为userid的值
			//下面三行以此类推 注意根据值的类型改变get后面加的类型 
			user.put("userid", cursor.getInt(cursor.getColumnIndex("userid")));
			user.put("name", cursor.getString(cursor.getColumnIndex("userName")));
			user.put("phone", cursor.getString(cursor.getColumnIndex("phone")));
			user.put("imageName", cursor.getString(cursor.getColumnIndex("imageName")));

			//再把hashmap加入到list里面
			list.add(user);
		}
		
		
		
		
		
		return list;
	}

	@Override
	public int deleteById(int userid) {
		// TODO Auto-generated method stub
		int bl=0;
		
		//第一种删除方式
		String sql="delete from user where userid="+userid;
		dbInstance.execSQL(sql);
		
		//第二种删除方式
		//二参字段名字  三参字段值
		//二参多个字段的话就是userid=? and username=?
		//一定不要忘记问号！！！！
		//dbInstance.delete("user", "userid=?", new String[] {String.valueOf(userid)});
		
		
		//第三种
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
		//第一种查找方式
		cursor=dbInstance.rawQuery(sql, null);
		
		//第二种查找方式
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
		
		//第一种更新方式
		
		String sql="update user set username='"+user.getUserName()+"',phone='"+user.getPhone()+"' where userid="
		+user.getUserid();
		dbInstance.execSQL(sql);
		
		
		
		//第二种更新方式
		
		//相当于buddle
		//先把所有的值封装到ContentValues中
		//ContentValues cv=new ContentValues();
		//key是表里面的字段 不能写错了
		//cv.put("userName", user.getUserName());
		//cv.put("phone", user.getPhone());
		//更新user表中id为user.getuserid();的记录
		//dbInstance.update("user", cv, "userid=?",
		//		new String[] {String.valueOf(user.getUserid())}); 
				
		
		bl=1;
		return bl;
	}

	@Override
	public ArrayList<HashMap<String, Object>> getUsersByName(String name) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		
		//要取出多条记录 不能用等号了！！！！！
		String sql="select * from user where username like '%"+name+"%' ";//模糊查找
		
        Cursor cursor=null;
		
		cursor=dbInstance.rawQuery(sql, null);
		
		
		while(cursor.moveToNext()) {
			  
			HashMap<String, Object> user=new HashMap<String, Object>();
			 
			user.put("userid", cursor.getInt(cursor.getColumnIndex("userid")));
			user.put("name", cursor.getString(cursor.getColumnIndex("userName")));
			user.put("phone", cursor.getString(cursor.getColumnIndex("phone")));
			user.put("imageName", cursor.getString(cursor.getColumnIndex("imageName")));

			//再把hashmap加入到list里面
			list.add(user);
		}
		
		return list;
	}

	 

}
