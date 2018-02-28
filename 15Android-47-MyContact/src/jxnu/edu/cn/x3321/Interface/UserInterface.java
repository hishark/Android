package jxnu.edu.cn.x3321.Interface;

import java.util.ArrayList;
import java.util.HashMap;

import jxnu.edu.cn.x3321.bean.User;

public interface UserInterface {
	public int insert(User user);
	//先做个假的返回int
	
	public ArrayList <HashMap<String,Object>> getAllUsers();

	public int deleteById(int userid);
	
	public User getUserById(int userid);
	
	public int update(User user);

	public ArrayList<HashMap<String, Object>> getUsersByName(String name);
}
