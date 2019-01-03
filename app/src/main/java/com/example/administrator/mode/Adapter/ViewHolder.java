package com.example.administrator.mode.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mode.Utlis.GlideCircleTransform;

/**
 * Created by Administrator on 2018/5/12/012.
 */

public class ViewHolder {
        private final SparseArray<View> views;
        private View convertView;
        private Context context;

        public ViewHolder(Context context, ViewGroup parent, int itemLayoutId, int position) {
            this.context = context;
            this.views =new SparseArray<>();
            this.convertView = LayoutInflater.from(context).inflate(itemLayoutId,parent,false);
            convertView.setTag(this);
        }
        //拿到一个ViewHolder对象
        public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
            if (convertView == null) {
                return new ViewHolder(context,parent, layoutId, position);
            }
            return (ViewHolder) convertView.getTag();
        }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
    public View getConvertView() {
        return convertView;
    }

    //设置字符串
    public ViewHolder setText(int viewId, String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    //设置图片
    public ViewHolder setImageResource(int viewId, int drawableId){
        ImageView iv = getView(viewId);
        iv.setImageResource(drawableId);
        return this;
    }

    //设置图片
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap){
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    //设置图片
    public ViewHolder setImageByUrl(int viewId, String url){
        Glide.with(context).load(url).centerCrop().transform(new GlideCircleTransform(context)).into((ImageView) getView(viewId));
        return this;
    }
}
