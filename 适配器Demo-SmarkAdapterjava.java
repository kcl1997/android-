package com.kcl.jwclnpu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kcl.jwclnpu.R;
import com.kcl.jwclnpu.entity.Smark;

import java.util.List;

/**
 * 项目名： JWCLNPU
 * 包名:    com.kcl.jwclnpu.adapter
 * 文件名   SmarkAdapter
 * 创建者
 * 创建时间: 2018/4/7 0007 0:04
 * 描述  ${TODO}
 */
public class SmarkAdapter extends BaseAdapter{
    private Context mContext;
    private List<Smark> mList;
    private Smark mSmark;
    private LayoutInflater mInflater;



    /**
     * 构造函数
     * 传入上下文，list实体数据
     * @return
     */

    public SmarkAdapter(Context context, List<Smark> list) {
        mContext = context;
        mList = list;
        //获取系统服务
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 填充视图
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        /**
         * 找到控件
         */
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_listview_activity_marks,null);
            viewHolder.tv_cno = (TextView) convertView.findViewById(R.id.tv_item_marks_cno);
            viewHolder.tv_cname = (TextView) convertView.findViewById(R.id.tv_item_marks_cname);
            viewHolder.tv_credit = (TextView) convertView.findViewById(R.id.tv_item_marks_credit);
            viewHolder.tv_jidian = (TextView) convertView.findViewById(R.id.tv_item_marks_jidian);
            viewHolder.tv_year = (TextView) convertView.findViewById(R.id.tv_item_marks_year);
            viewHolder.tv_mark = (TextView) convertView.findViewById(R.id.tv_item_marks_mark);
            viewHolder.tv_cmode = (TextView) convertView.findViewById(R.id.tv_item_marks_cmode);
            viewHolder.tv_ctime = (TextView) convertView.findViewById(R.id.tv_item_marks_time);

            //第一次加载缓存
            convertView.setTag(viewHolder);
        }else{
            //不是第一次加载，从缓存中取
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /**
         * 填充item数据
         */
        mSmark = mList.get(position);
        viewHolder.tv_cmode.setText("开课模式: "+mSmark.getCmode());
        viewHolder.tv_ctime.setText("学时: "+mSmark.getXueshi());
        viewHolder.tv_cno.setText("课程号: "+mSmark.getCno());
        viewHolder.tv_cname.setText("课程名: "+mSmark.getCname());
        viewHolder.tv_credit.setText("学分: "+mSmark.getCredit());
        viewHolder.tv_jidian.setText("绩点: "+mSmark.getJidian());
        viewHolder.tv_year.setText(mSmark.getYear());
        viewHolder.tv_mark.setText("分数: "+mSmark.getMark());

        return convertView;
    }


    /**
     * 设置缓存
     */

    class ViewHolder{
        private TextView tv_cno;
        private TextView tv_cname;
        private TextView tv_credit;
        private TextView tv_jidian;
        private TextView tv_year;
        private TextView tv_mark;
        private TextView tv_cmode;
        private TextView tv_ctime;


    }



}
