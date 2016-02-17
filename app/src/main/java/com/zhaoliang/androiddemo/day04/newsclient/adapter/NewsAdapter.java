package com.zhaoliang.androiddemo.day04.newsclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhaoliang.androiddemo.R;
import com.zhaoliang.androiddemo.day04.newsclient.domain.News;
import com.zhaoliang.androiddemo.day04.openimageviewer.com.loopj.android.image.SmartImageView;

import java.util.List;

/**
 * Created by pro on 16/2/5.
 */
public class NewsAdapter extends BaseAdapter {

    private Context context;
    private List<News> data;

    public NewsAdapter(Context context, List<News> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public News getItem(int position) {
        return data.get(position);
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
            convertView = View.inflate(context, R.layout.news_list_item, null);
            viewHolder.image = (SmartImageView) convertView.findViewById(R.id.news_image);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.news_title);
            viewHolder.tvDetail = (TextView) convertView.findViewById(R.id.news_detail);
            viewHolder.tvCommont = (TextView) convertView.findViewById(R.id.news_comment);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        News item = getItem(position);
        viewHolder.image.setImageUrl(item.imageUrl);
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvDetail.setText(item.detail);
        viewHolder.tvCommont.setText(item.comment);
        return convertView;
    }

    class ViewHolder {
        SmartImageView image;
        TextView tvTitle;
        TextView tvDetail;
        TextView tvCommont;
    }
}
