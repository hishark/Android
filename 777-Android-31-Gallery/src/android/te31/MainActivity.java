package android.te31;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MainActivity extends Activity {

	// 整体步骤：1.定义一个数组存储图片数据 2.封装到适配器 3.加载到gallery 4.处理
	//1.定义成员变量
	ImageView iv;
	Gallery gl;//一条杠表示不建议使用了 gallery已经可以用viewpager代替了
	
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
		
		//2.初始化
		init();
		
		//3.把图片封装到适配器中去
		MyAdapter ma=new MyAdapter();
		//这个适配器定义了一个内部类
		
		//4.加载适配器到gallery
		gl.setAdapter(ma);
		
		//5.定义注册事件监听器
		//注意set是哪个set gallery中某个item被选中时的监听器
		gl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
		 
			//什么都没选的时候怎么样
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_LONG).show();
			}
			
			//arg2知道选中哪张图片
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				iv.setImageResource(imageIDs[arg2%imageIDs.length]);//求余实现循环播放
			}
		});
	}

	//用内部类实现适配器
	public class MyAdapter extends BaseAdapter{

		
		//要实现循环实现 看完最后一个回到第一个 所以这个getcount不能返回imageIds.length,应该是一个无穷大的数
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

		//这里无穷大 那么position也会跟着一直往无穷大加
		
		
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
			//水平垂直自适应尺寸
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
