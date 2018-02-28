package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	//1.定义成员引用变量
	ImageView iv;
	Button btAmplify,btReduce,btLeftRoat,btRightRoat;
	Bitmap bmp;
	int size=100;
	//初始的透明度大小
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.初始化
		init();
		
		//3.生成bitmap对象并放到imageView中 设置透明度
		bmp=BitmapFactory.decodeResource(getResources(), R.drawable.list1);
		//这句话还挺好理解，Bitmap工厂解码list1这张图片！然后存到了bmp中
		
		iv.setImageBitmap(bmp);//把bmp设置为当前imageview的内容物
		iv.setAlpha(size);//设置当前imageview的透明度
		
		//4.定义注册事件监听器
		iv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				size=size+20;
				iv.setAlpha(size);//点一下透明一点点
			}
		});
		btAmplify.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bmp=scaleToFit(bmp,1.2f);//自定义的缩放方法
				iv.setImageBitmap(bmp);//缩放成功后放入imageview中
			}
		});
		btReduce.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bmp=scaleToFit(bmp,0.8f);
				iv.setImageBitmap(bmp);
			}
		});
		btLeftRoat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bmp=RoateToFit(bmp,-90f);
				iv.setImageBitmap(bmp);
			}
		});
		btRightRoat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bmp=RoateToFit(bmp,+90f);
				iv.setImageBitmap(bmp);
			}
		});
	}

	protected Bitmap RoateToFit(Bitmap bmp2, float f) {
		// TODO Auto-generated method stub
		int width=bmp2.getWidth();
		int height=bmp2.getHeight();
		Matrix matrix=new Matrix();
		matrix.postRotate(f);//定义了一个画布
		Bitmap bmResult=Bitmap.createBitmap(bmp2, 0, 0, width, height,matrix,true);//讲义P335
		//从源位图bmp2的指定坐标点（0，0）开始，从中挖取宽高widthheight的一块出来创建新的bitmap对象 并且按照matrix制定的规则进行变换
		
		return bmResult;
	}

	protected Bitmap scaleToFit(Bitmap bmp2, float f) {
		// TODO Auto-generated method stub
		int width=bmp2.getWidth();
		int height=bmp2.getHeight();
		Matrix matrix=new Matrix();
		matrix.postScale(f,f);//定义了形参大小f的一个画布
		Bitmap bmResult=Bitmap.createBitmap(bmp2, 0, 0, width, height,matrix,true);
		//从源位图bmp2的指定坐标点（0，0）开始，从中挖取宽高widthheight的一块出来创建新的bitmap对象 并且按照matrix制定的规则进行变换
		return bmResult;
	}

	private void init() {
		// TODO Auto-generated method stub
		iv=(ImageView)this.findViewById(R.id.iv);
		btAmplify=(Button)this.findViewById(R.id.bt_amplify);
		btReduce=(Button)this.findViewById(R.id.bt_reduce);
		btLeftRoat=(Button)this.findViewById(R.id.bt_leftRoate);
		btRightRoat=(Button)this.findViewById(R.id.bt_rightRoate);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
