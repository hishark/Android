package jxnu.edu.cn.x3321;

public class Student {
	public int Avatar;
	public String Name;  
    public String Sex;  
    public int Age;  
    public String Tel;
    Student(int img,String name,String sex,int age){  
    	Avatar=img;
        Name=name;  
        Sex=sex;  
        Age=age; 
     
    }//构造方法
    public int getAvatar(){  
        return Avatar;  
    }
    
    public String getName(){  
        return Name;  
    }  
      
    public String getSex(){  
        return Sex;  
    } 
     public int getAge(){  
         return Age;  
     }  
     
     
     
     public String getStuIfo(){   
         return ("Name:"+Name+"\n    Sex:"+Sex+"\n    Age:"+Age);  
     }  
}
