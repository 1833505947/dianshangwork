package com.example.day2_20181227.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day2_20181227.R;
import com.example.day2_20181227.entity.UserBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends BaseAdapter {
    private Context context;
    private List<UserBean.DataBean> list;

    public Myadapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<UserBean.DataBean> list) {
        if (list!=null) {
            this.list = list;

        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.ji_item,parent,false);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.bindData(getItem(position));
        return convertView;
    }
    class ViewHolder{
        ImageView img;
        TextView name,price;

        public ViewHolder(View convertView) {
            img = convertView.findViewById(R.id.img);
            name = convertView.findViewById(R.id.name);
            price = convertView.findViewById(R.id.price);
            convertView.setTag(this);
        }

        public void bindData(UserBean.DataBean item) {
            name.setText(item.getTitle());
            price.setText(item.getPrice());
            ImageLoader.getInstance().displayImage(item.getImages(),img);
        }
    }
}
