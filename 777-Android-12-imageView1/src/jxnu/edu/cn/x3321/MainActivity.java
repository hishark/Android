package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	//1.定义图片数组
	int []images=new int[] {
			R.drawable.list1,//id是一串地址所以可以存在整形数组
			R.drawable.list2,
			R.drawable.list3,
			R.drawable.list4,
			R.drawable.list5,
			R.drawable.list6,
			R.drawable.list7,
			R.drawable.list8,
	};
	int currentImage=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.获得id为ll的线性布局
		LinearLayout layout=(LinearLayout)this.findViewById(R.id.ll);
		
		//创建imageView的组件
		final ImageView imge=new ImageView(MainActivity.this);
		imge.setImageResource(images[currentImage]);
		
		//将imageView组件添加到id=ll的线性布局layout里去
		layout.addView(imge);
		
		//注册定义事件监听器
		imge.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//改变imageView中的图片
				currentImage=(currentImage+1)%images.length;
				imge.setImageResource(images[currentImage]);//改变imageview承载的图片 也不叫承载啦大概就这个意思
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
