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

	//1.�����Ա���ñ���
	ImageView iv;
	Button btAmplify,btReduce,btLeftRoat,btRightRoat;
	Bitmap bmp;
	int size=100;
	//��ʼ��͸���ȴ�С
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.��ʼ��
		init();
		
		//3.����bitmap���󲢷ŵ�imageView�� ����͸����
		bmp=BitmapFactory.decodeResource(getResources(), R.drawable.list1);
		//��仰��ͦ����⣬Bitmap��������list1����ͼƬ��Ȼ��浽��bmp��
		
		iv.setImageBitmap(bmp);//��bmp����Ϊ��ǰimageview��������
		iv.setAlpha(size);//���õ�ǰimageview��͸����
		
		//4.����ע���¼�������
		iv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				size=size+20;
				iv.setAlpha(size);//��һ��͸��һ���
			}
		});
		btAmplify.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bmp=scaleToFit(bmp,1.2f);//�Զ�������ŷ���
				iv.setImageBitmap(bmp);//���ųɹ������imageview��
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
		matrix.postRotate(f);//������һ������
		Bitmap bmResult=Bitmap.createBitmap(bmp2, 0, 0, width, height,matrix,true);//����P335
		//��Դλͼbmp2��ָ������㣨0��0����ʼ��������ȡ���widthheight��һ����������µ�bitmap���� ���Ұ���matrix�ƶ��Ĺ�����б任
		
		return bmResult;
	}

	protected Bitmap scaleToFit(Bitmap bmp2, float f) {
		// TODO Auto-generated method stub
		int width=bmp2.getWidth();
		int height=bmp2.getHeight();
		Matrix matrix=new Matrix();
		matrix.postScale(f,f);//�������βδ�Сf��һ������
		Bitmap bmResult=Bitmap.createBitmap(bmp2, 0, 0, width, height,matrix,true);
		//��Դλͼbmp2��ָ������㣨0��0����ʼ��������ȡ���widthheight��һ����������µ�bitmap���� ���Ұ���matrix�ƶ��Ĺ�����б任
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
