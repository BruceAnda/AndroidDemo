package com.zhaoliang.androiddemo.day03.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zhaoliang.androiddemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends Activity {

    private ListView arrayadpater_list;
    private ListView simpleadapter_list;
    private ListView coustomadapter_list;

    private String[] names = {"张三", "李四", "王五", "赵六"};
    private int[] icons = {R.mipmap.ic_launcher, R.mipmap.head};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        arrayadpater_list = (ListView) findViewById(R.id.arrayadater_list);
        simpleadapter_list = (ListView) findViewById(R.id.simpleadater_list);
        coustomadapter_list = (ListView) findViewById(R.id.customadapter_list);

        arrayadpater_list.setAdapter(new ArrayAdapter<String>(this, R.layout.listview_item, R.id.tv_name, names));
        List<Map<String, Object>> data = new ArrayList<>();

        Map<String, Object> data1 = new HashMap<>();
        data1.put("icon", R.mipmap.head);
        data1.put("name", "张三");
        data.add(data1);

        Map<String, Object> data2 = new HashMap<>();
        data2.put("icon", R.mipmap.head);
        data2.put("name", "李四");
        data.add(data2);

        Map<String, Object> data3 = new HashMap<>();
        data3.put("icon", R.mipmap.ic_launcher);
        data3.put("name", "王五");
        data.add(data3);

        Map<String, Object> data4 = new HashMap<>();
        data4.put("icon", R.mipmap.ic_launcher);
        data4.put("name", "赵六");
        data.add(data4);


        simpleadapter_list.setAdapter(new SimpleAdapter(this, data, R.layout.listview_item, new String[]{"icon", "name"}, new int[]{R.id.iv_icon, R.id.tv_name}));

        coustomadapter_list.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public String getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(ListViewActivity.this, R.layout.listview_item, null);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.icon.setImageResource(icons[position % 2]);
            viewHolder.name.setText(names[position]);
            return convertView;
        }

        class ViewHolder {
            ImageView icon;
            TextView name;
        }
    }
}
