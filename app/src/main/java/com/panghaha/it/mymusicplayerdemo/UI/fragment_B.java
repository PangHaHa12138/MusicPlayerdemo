package com.panghaha.it.mymusicplayerdemo.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

import com.panghaha.it.mymusicplayerdemo.R;
import com.panghaha.it.mymusicplayerdemo.widget.CenterDialog;

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
 * Created by PangHaHa12138 on 2017/6/25.
 */
public class fragment_B extends Fragment implements View.OnClickListener{

    private View mview;
    private RelativeLayout bd,zj,ilike,likefm,ytfm,wd;
    private CircleImageView xz;
    private RotateAnimation rotateAnimation;
    private CenterDialog centerDialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mview = inflater.inflate(R.layout.fragment_b,container,false);

        bd = (RelativeLayout) mview.findViewById(R.id.bdmusic);
        zj = (RelativeLayout) mview.findViewById(R.id.zjmusic);
        ilike = (RelativeLayout) mview.findViewById(R.id.ilike);
        likefm = (RelativeLayout) mview.findViewById(R.id.likefm);
        ytfm = (RelativeLayout) mview.findViewById(R.id.ytmusic);
        wd = (RelativeLayout) mview.findViewById(R.id.wdmusic);
        xz = (CircleImageView) mview.findViewById(R.id.xz);

        centerDialog = new CenterDialog(getActivity(),R.layout.centerdilog,new int[]{R.id.loading});

        init();

        return mview;
    }

    private void init() {

         rotateAnimation = new RotateAnimation(0f,360f, Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotateAnimation.setInterpolator(lin);//匀速
        rotateAnimation.setDuration(8000);//设置动画持续时间
        rotateAnimation.setRepeatCount(-1);//设置重复次数 -1不停
        rotateAnimation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        rotateAnimation.setStartOffset(10);//执行前的等待时间
        xz.setAnimation(rotateAnimation);
        rotateAnimation.startNow();

        bd.setOnClickListener(this);
        zj.setOnClickListener(this);
        ilike.setOnClickListener(this);
        likefm.setOnClickListener(this);
        ytfm.setOnClickListener(this);
        wd.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bdmusic:

//                Toast.makeText(getActivity(),"本地音乐",Toast.LENGTH_SHORT).show();
                centerDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        centerDialog.dismiss();
                        Intent intent = new Intent(getActivity(),Musiclist.class);
                        startActivity(intent);
                    }
                },2000);


                break;

            case R.id.zjmusic:
                Intent intent2 = new Intent(getActivity(),YTFM.class);
                startActivity(intent2);
//                Toast.makeText(getActivity(),"最近播放",Toast.LENGTH_SHORT).show();
                break;

            case R.id.ytmusic:
                Intent intent3 = new Intent(getActivity(),YTFM.class);
                startActivity(intent3);
//                Toast.makeText(getActivity(),"语瞳FM",Toast.LENGTH_SHORT).show();
                break;

            case R.id.wdmusic:
                Intent intent4 = new Intent(getActivity(),ILikeMusic.class);
                startActivity(intent4);
//                Toast.makeText(getActivity(),"我的收藏",Toast.LENGTH_SHORT).show();

                break;

            case R.id.ilike:
                Intent intent5 = new Intent(getActivity(),ILikeMusic.class);
                startActivity(intent5);
//                Toast.makeText(getActivity(),"我喜欢的音乐",Toast.LENGTH_SHORT).show();
                break;

            case R.id.likefm:
                Intent intent6 = new Intent(getActivity(),YTFM.class);
                startActivity(intent6);
//                Toast.makeText(getActivity(),"我喜欢的FM",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
