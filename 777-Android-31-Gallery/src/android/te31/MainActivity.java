package android.te31;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MainActivity extends Activity {

	// ���岽�裺1.����һ������洢ͼƬ���� 2.��װ�������� 3.���ص�gallery 4.����
	//1.�����Ա����
	ImageView iv;
	Gallery gl;//һ���ܱ�ʾ������ʹ���� gallery�Ѿ�������viewpager������
	
	int imageIDs[]=new int[] {
		R.drawable.cat1,	
		R.drawable.cat2,
		R.drawable.cat3,
		R.drawable.cat4,
		R.drawable.cat5,
		R.drawable.cat6,
		R.drawable.cat7,
		R.drawable.cat8,
		R.drawable.cat9,
		R.drawable.cat10,
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//2.��ʼ��
		init();
		
		//3.��ͼƬ��װ����������ȥ
		MyAdapter ma=new MyAdapter();
		//���������������һ���ڲ���
		
		//4.������������gallery
		gl.setAdapter(ma);
		
		//5.����ע���¼�������
		//ע��set���ĸ�set gallery��ĳ��item��ѡ��ʱ�ļ�����
		gl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
		 
			//ʲô��ûѡ��ʱ����ô��
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
			}
			
			//arg2֪��ѡ������ͼƬ
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				iv.setImageResource(imageIDs[arg2%imageIDs.length]);//����ʵ��ѭ������
			}
		});
	}

	//���ڲ���ʵ��������
	public class MyAdapter extends BaseAdapter{

		
		//Ҫʵ��ѭ��ʵ�� �������һ���ص���һ�� �������getcount���ܷ���imageIds.length,Ӧ����һ����������
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

		//��������� ��ôpositionҲ�����һֱ��������
		
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return imageIDs[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView iv=new ImageView(getApplicationContext());
			iv.setImageResource(imageIDs[position%imageIDs.length]);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			//ˮƽ��ֱ����Ӧ�ߴ�
			iv.setLayoutParams(new Gallery.LayoutParams(200,200));
			return iv;
		}
		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		iv=(ImageView)this.findViewById(R.id.iv);
		gl=(Gallery)this.findViewById(R.id.gl);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
