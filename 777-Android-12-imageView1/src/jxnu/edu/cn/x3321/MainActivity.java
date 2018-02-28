package jxnu.edu.cn.x3321;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	//1.����ͼƬ����
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//2.���idΪll�����Բ���
		LinearLayout layout=(LinearLayout)this.findViewById(R.id.ll);
		
		//����imageView�����
		final ImageView imge=new ImageView(MainActivity.this);
		imge.setImageResource(images[currentImage]);
		
		//��imageView�����ӵ�id=ll�����Բ���layout��ȥ
		layout.addView(imge);
		
		//ע�ᶨ���¼�������
		imge.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//�ı�imageView�е�ͼƬ
				currentImage=(currentImage+1)%images.length;
				imge.setImageResource(images[currentImage]);//�ı�imageview���ص�ͼƬ Ҳ���г�������ž������˼
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
