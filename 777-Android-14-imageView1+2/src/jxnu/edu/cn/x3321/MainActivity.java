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
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	int []images=new int[] {
			R.drawable.list1,//id��һ����ַ���Կ��Դ�����������
			R.drawable.list2,
			R.drawable.list3,
			R.drawable.list4,
			R.drawable.list5,
			R.drawable.list6,
			R.drawable.list7,
			R.drawable.list8,
	};
	int currentImage=0;
	Button btLast,btNext,btAmplify,btReduce,btLeft,btRight;
	Bitmap bmp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//1.��ʼ��
		init();
		
		//2.���idΪll�����Բ���
		LinearLayout layout=(LinearLayout)this.findViewById(R.id.ll);
		
		//����imageView�����
		final ImageView imge=new ImageView(MainActivity.this);
		imge.setImageResource(images[currentImage]);
		
		//��imageView�����ӵ�id=ll�����Բ�����ȥ
		layout.addView(imge);
		
		//3.����bitmap���󲢷ŵ�imageView��  
		bmp=BitmapFactory.decodeResource(getResources(), images[currentImage]);
		imge.setImageBitmap(bmp);
		
				//ע���¼�������
				btLast.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(currentImage!=0) {
							currentImage--;
							imge.setImageResource(images[currentImage]);
							bmp=BitmapFactory.decodeResource(getResources(), images[currentImage]);
						}
						else
						{
							currentImage=images.length-1;
							imge.setImageResource(images[currentImage]);
							bmp=BitmapFactory.decodeResource(getResources(), images[currentImage]);
							currentImage--;
						}
					}
				});
				btNext.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(currentImage!=images.length-1) {
							currentImage++;
							imge.setImageResource(images[currentImage]);
							bmp=BitmapFactory.decodeResource(getResources(), images[currentImage]);
						}
						else
						{
							currentImage=0;
							imge.setImageResource(images[currentImage]);
							bmp=BitmapFactory.decodeResource(getResources(), images[currentImage]);
							
						}
					}
				});
				btAmplify.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//bmp=BitmapFactory.decodeResource(getResources(), images[currentImage]);
						bmp=scaleToFit(bmp,1.2f);//���µ�ǰbmp�Ĵ�С
						imge.setImageBitmap(bmp);//��bmp����ŵ�imageView��
					}
				});
				btReduce.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						bmp=scaleToFit(bmp,0.8f);
						imge.setImageBitmap(bmp);
					}
				});
				btLeft.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						bmp=RotateToFit(bmp,-90f);
						imge.setImageBitmap(bmp);
					}
				});
				btRight.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						bmp=RotateToFit(bmp,+90f);
						imge.setImageBitmap(bmp);
					}
				});
	}

	protected Bitmap RotateToFit(Bitmap bmp2, float f) {
		// TODO Auto-generated method stub
		int width=bmp2.getWidth();
		int height=bmp2.getHeight();
		Matrix matrix=new Matrix();
		matrix.postRotate(f);
		Bitmap bmResult=Bitmap.createBitmap(bmp2,0,0,width,height,matrix,true);
		//��Դλͼbmp2��ָ������㣨0��0����ʼ��������ȡ���widthheight��һ����������µ�bitmap���� ���Ұ���matrix�ƶ��Ĺ�����б任
		return bmResult;
	}

	protected Bitmap scaleToFit(Bitmap bmp2, float f) {
		// TODO Auto-generated method stub
		int width=bmp2.getWidth();
		int height=bmp2.getHeight();
		Matrix matrix=new Matrix();
		matrix.postScale(f,f);//������һ������
		Bitmap bmResult=Bitmap.createBitmap(bmp2,0,0, width,height,matrix,true);
		//��Դλͼbmp2��ָ������㣨0��0����ʼ��������ȡ���widthheight��һ����������µ�bitmap���� ���Ұ���matrix�ƶ��Ĺ�����б任
		return bmResult;
	}

	private void init() {
		// TODO Auto-generated method stub
		btLast=(Button)this.findViewById(R.id.bt_last);
		btNext=(Button)this.findViewById(R.id.bt_next);
		btAmplify=(Button)this.findViewById(R.id.bt_amplify);
		btReduce=(Button)this.findViewById(R.id.bt_reduce);
		btLeft=(Button)this.findViewById(R.id.bt_left);
		btRight=(Button)this.findViewById(R.id.bt_right);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
