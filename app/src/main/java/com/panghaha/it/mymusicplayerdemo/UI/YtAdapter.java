package com.panghaha.it.mymusicplayerdemo.UI;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.panghaha.it.mymusicplayerdemo.R;

import java.util.List;

/***
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　  ()
 * 　　  ( ) 　　　( )
 * 　　  ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the more protecting ━━━
 * <p/>
 * Created by PangHaHa12138 on 2017/7/7.
 */
public class YtAdapter extends BaseAdapter {

    private List<Song2> mlist;
    private Context mcontext;

    public YtAdapter(Context context,List<Song2> list){

        this.mcontext = context;
        this.mlist = list;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            //引入布局
            convertView = View.inflate(mcontext, R.layout.musiclist_item, null);
            //实例化对象
            holder.song = (TextView) convertView.findViewById(R.id.item_mymusic_song);
            holder.singer = (TextView) convertView.findViewById(R.id.item_mymusic_singer);
            holder.duration = (TextView) convertView.findViewById(R.id.item_mymusic_duration);
            holder.positio = (TextView) convertView.findViewById(R.id.item_mymusic_postion);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //给控件赋值
        holder.song.setText(mlist.get(position).getSong());
        holder.singer.setText(mlist.get(position).getSinger());
        //时间需要转换一下
//        int duration = mlist.get(position).duration;
//        String time = MusicUtils.formatTime(duration);
        holder.duration.setText(mlist.get(position).getDuration()+":02");
        holder.positio.setText(position+1+"");

        return convertView;
    }
    static class ViewHolder{

        TextView song;//歌曲名
        TextView singer;//歌手
        TextView duration;//时长
        TextView positio;//序号

    }
}
