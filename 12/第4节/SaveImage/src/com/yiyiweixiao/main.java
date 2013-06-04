package com.yiyiweixiao;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class main extends Activity implements OnClickListener {

	private Button btnsave1, btnsave2, btnquery1, btnquery2;
	private ImageView iv1, iv2;
	private MySQLiteOpenHelper mySQLiteOpenHelper = null;
	private SQLiteDatabase mydb = null;
	        
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// �����������ʵ��
		// CursorFactory��ֵΪnull,��ʾ����Ĭ�ϵĹ�����
		mySQLiteOpenHelper = new MySQLiteOpenHelper(this, "saveimage.db", null,
				1);
		// ����һ���ɶ�д�����ݿ�
		mydb = mySQLiteOpenHelper.getWritableDatabase();
		// ������ť��ʵ��
		btnsave1 = (Button) findViewById(R.id.button1);
		btnsave2 = (Button) findViewById(R.id.button2);
		btnquery1 = (Button) findViewById(R.id.button3);
		btnquery2 = (Button) findViewById(R.id.button4);
		// ������������
		btnsave1.setOnClickListener(this);
		btnsave2.setOnClickListener(this);
		btnquery1.setOnClickListener(this);
		btnquery2.setOnClickListener(this);

	}

	public void onClick(View v) {
		if (v == btnsave1) {//����ͼƬ
			try {
        //��ͼƬת��Ϊλͼ
			Bitmap bitmap1=BitmapFactory.decodeResource(getResources(), R.drawable.erweima);	
		    int size=bitmap1.getWidth()*bitmap1.getHeight()*4;		
		//����һ���ֽ����������,���Ĵ�СΪsize
		    ByteArrayOutputStream baos=new ByteArrayOutputStream(size);    
		//����λͼ��ѹ����ʽ������Ϊ100%���������ֽ������������    
		    bitmap1.compress(Bitmap.CompressFormat.PNG, 100, baos);
		//���ֽ����������ת��Ϊ�ֽ�����byte[]    
		    byte[] imagedata1=baos.toByteArray(); 
		//���ֽ����鱣�浽���ݿ���    
		ContentValues cv=new ContentValues();
		 cv.put("_id", 1);   
		 cv.put("image", imagedata1);   
		 mydb.insert("imagetable", null, cv);
		//�ر��ֽ����������
		 baos.close();
		 
			} catch (Exception e) {
              e.printStackTrace();
			}

		} else if (v == btnsave2) {// ����ͼƬ2
          try{
        	Bitmap bitmap2=BitmapFactory.decodeResource(getResources(), R.drawable.taohua);  
        	int    size=bitmap2.getWidth()*bitmap2.getHeight()*4;  
        	ByteArrayOutputStream baos=new ByteArrayOutputStream(size);  
        	bitmap2.compress(Bitmap.CompressFormat.PNG, 100, baos);  
        	byte[] imagedata2=baos.toByteArray();
        	ContentValues cv=new ContentValues();
        	cv.put("_id", 2);
        	cv.put("image", imagedata2);
        	mydb.insert("imagetable", null, cv);
        	baos.close();
        	
          }
			catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			
			
		} else if (v == btnquery1) {// ��ѯͼƬ1
        //����һ��ָ��
			Cursor cur=mydb.query("imagetable", new String[]{"_id","image"}, null, null, null, null, null);
			byte[] imagequery=null;
			if(cur.moveToNext()){
				//��Blob����ת��Ϊ�ֽ�����
				imagequery=cur.getBlob(cur.getColumnIndex("image"));
			}
			//���ֽ�����ת��Ϊλͼ
			Bitmap imagebitmap=BitmapFactory.decodeByteArray(imagequery, 0, imagequery.length);
			iv1=(ImageView) findViewById(R.id.imageView1);
			//��λͼ��ʾΪͼƬ
			iv1.setImageBitmap(imagebitmap);
			
		} else if (v == btnquery2) {// ��ѯͼƬ2

		Cursor cur=mydb.query("imagetable", new String[]{"_id","image"}, null, null, null, null, null);	
		byte[] imagequery=null;	
		while(cur.moveToNext()){
		
			imagequery=cur.getBlob(cur.getColumnIndex("image"));
			Bitmap imagebitmap=BitmapFactory.decodeByteArray(imagequery,0,imagequery.length);	
			iv2=(ImageView) findViewById(R.id.imageView2);
			iv2.setImageBitmap(imagebitmap);	
		}
		    
			
		}

	}
	public void onDestroy(){
		super.onDestroy();
		//�˳�����ʱ������ر����ݿ⡣
		mydb.close();
		
	}
	
	
}