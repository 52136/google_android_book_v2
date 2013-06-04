package com.eoeAndroid.Assistant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class MobileInfoAssistantActivity extends Activity implements OnItemClickListener{
    /** Called when the activity is first created. */
	
	private static final String TAG = "MobileInfoAssistantActivity";
	ListView itemlist = null;
	List<Map<String, Object>> list;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        itemlist = (ListView) findViewById(R.id.itemlist);
		refreshListItems();
    }
    
    private void refreshListItems() {
		list = buildListForSimpleAdapter();
		SimpleAdapter notes = new SimpleAdapter(this, list, R.layout.item_row,
				new String[] { "name", "desc", "img" }, new int[] { R.id.name,
						R.id.desc, R.id.img });
		itemlist.setAdapter(notes);
		itemlist.setOnItemClickListener(this);
		itemlist.setSelection(0);
	}
    
    private List<Map<String, Object>> buildListForSimpleAdapter() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(3);
		// Build a map for the attributes
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "ϵͳ��Ϣ");
		map.put("desc", "�鿴�豸ϵͳ�汾,��Ӫ�̼���ϵͳ��Ϣ.");
		map.put("img", R.drawable.system);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "Ӳ����Ϣ");
		map.put("desc", "�鿴����CPU,Ӳ��,�ڴ��Ӳ����Ϣ.");
		map.put("img", R.drawable.hardware);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "������Ϣ");
		map.put("desc", "�鿴�Ѿ���װ��������Ϣ.");
		map.put("img", R.drawable.software);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "����ʱ��Ϣ");
		map.put("desc", "�鿴�豸����ʱ����Ϣ.");
		map.put("img", R.drawable.running);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "�ļ������");
		map.put("desc", "����鿴�ļ�ϵͳ.");
		map.put("img", R.drawable.file_explorer);
		list.add(map);
		
		return list;
	}
    
    @Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Intent intent = new Intent();
		Log.i(TAG, "item clicked! [" + position + "]");
		switch (position) {
		case 0:
			intent.setClass(MobileInfoAssistantActivity.this, SystemActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent.setClass(MobileInfoAssistantActivity.this, HardwareActivity.class);
			startActivity(intent);
			break;
			case 2:
			intent.setClass(MobileInfoAssistantActivity.this, SoftwareActivity.class);
			startActivity(intent);
			break;
			case 3:
			intent.setClass(MobileInfoAssistantActivity.this, RuningActivity.class);
			startActivity(intent);
			break;
			case 4:
			intent.setClass(MobileInfoAssistantActivity.this, FSExplorerActivity.class);
			startActivity(intent);
			break;
		}
	}
}