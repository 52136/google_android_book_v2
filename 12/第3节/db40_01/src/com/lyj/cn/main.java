package com.lyj.cn;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import android.app.Activity;
import android.os.Bundle;

public class main extends Activity {
    private ObjectContainer db;
	private People people;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
    db=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "/sdcard/db4o.data");
    
    //������װ�����ʵ�����󣬲�������ֵ
    people=new People(1,"С��",20);
    //�������
    db.store(people);
    //�����ύ
    db.commit();
    }
    
    public void querybyexample(){
    	//����ͬһ����װ�ഴ���˶����ͬ�Ķ���
    	ObjectSet<People>  myObjectSet=db.queryByExample(new People());
    	String string="";
    	while(myObjectSet.hasNext()){
    		People people=myObjectSet.next();
    		string=string+people.getId()+people.getName()+people.getAge()+"\n";
    	}
    	
    	
    	
    	
    	
    }
    
}