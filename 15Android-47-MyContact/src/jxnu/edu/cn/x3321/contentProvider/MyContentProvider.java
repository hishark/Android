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

	//�̳�ContentProvider
	//��ͳһ�ķ�ʽ�����ṩ����
	//���ﶨ������ɾ�Ĳ�
	
	//1.ΪContentProvider����һ��Ψһ�ı�ʶ��URI������������Ӧ��ͨ���ñ�ʶ�ܷ��ʵ�MyContentProvider
	private static String auth = "jxnu.edu.cn.x3321";
	
	//2.��Ψһ�ı�ʶURI���õ�androidmanifest��ȥ
	//ȥandroidmanifest����
	
	//3.����URIƥ���� �����ж��û�������Ҫ������ʲô����
	private static UriMatcher um=new UriMatcher(UriMatcher.NO_MATCH);
	//��̬����� û��ʵ������ִ�еģ�����
	static {
		//�ⲿֻ�ܵ��������Ѿ�������˵Ĳ��� 
		//�����������ͱ�ʾ�����ı�ţ�
		//��һ��������ʾΨһ�ı�ʶURI
		//�ڶ����������Ƕ���Ĳ��������ͣ��ַ����������ȡ��������һ�����֪��
		um.addURI(auth, "all", 1);//��ѯ���ݿ�������û�
		um.addURI(auth, "#", 2);//��ѯuseridΪ#��һ����¼
		um.addURI(auth, "insert", 3);
	}
	
	//�ӿ�
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
		case 3://��insert �������
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
		//1���� ����auth+������ 2�������ѯ�ı���ֶ��� 3���ǲ�ѯ������where��������� 4���Ǻö����� 5��������
		//����Ĳ���һ�㶼null���� ��������ٿ���
		
		ui=new UserInterfaceImp(getContext());
		Cursor cursor=null;
		switch(um.match(uri)){
		case 1:
			//��all
			cursor=UserInterfaceImp.dbInstance.
			query("user", new String[] {"userid","userName","phone","imageName"}, null, null, null, null, null);
			break;
			
		case 2:
			//��#
			//�ⲿ�������������ʣ�    content://jxnu.edu.cn.x3321/2
			long id=ContentUris.parseId(uri);//��ȡ������#
			
			if(selection==null||selection.equals("")) {
				selection="userid="+id;
			}else {
				selection+="and userid="+id;
			}
			cursor=UserInterfaceImp.dbInstance.
					//2��Ϊnull �����ҳ������е�
					query("user", null, selection, null, null, null, null);
					break;
		}
		
		//�κ�һ��������ֻҪ����query�Ϳ��Եõ�Cursor���� �õ�����ʲôʲôʲô��
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
