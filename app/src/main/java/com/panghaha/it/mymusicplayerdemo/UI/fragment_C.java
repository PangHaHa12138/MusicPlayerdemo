package com.panghaha.it.mymusicplayerdemo.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.panghaha.it.mymusicplayerdemo.R;

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
public class fragment_C extends Fragment implements View.OnClickListener{

    private View mview;
    private RelativeLayout pyq,tantan,pbl,ofo,mobai;
    private CircleImageView xuanzhuan;
    private RotateAnimation rotateAnimation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mview = inflater.inflate(R.layout.fragment_c,container,false);

        pyq = (RelativeLayout) mview.findViewById(R.id.pengyouquan);
        tantan = (RelativeLayout) mview.findViewById(R.id.tantan6);
        pbl = (RelativeLayout) mview.findViewById(R.id.pubu);
        ofo = (RelativeLayout) mview.findViewById(R.id.ofoo);
        mobai = (RelativeLayout) mview.findViewById(R.id.mobai);
        xuanzhuan = (CircleImageView) mview.findViewById(R.id.xuanzhuan);

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
        xuanzhuan.setAnimation(rotateAnimation);
        rotateAnimation.startNow();

        pyq.setOnClickListener(this);
        tantan.setOnClickListener(this);
        pbl.setOnClickListener(this);
        ofo.setOnClickListener(this);
        mobai.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.pengyouquan:

//                Toast.makeText(getActivity(),"朋友圈",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),FishActivity.class);
                startActivity(intent);
                break;

            case R.id.tantan6:

//                Toast.makeText(getActivity(),"探探",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getActivity(),tantan1.class);
                startActivity(intent2);

                break;

            case R.id.pubu:

//                Toast.makeText(getActivity(),"瀑布流",Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getActivity(),WDActivity.class);
                startActivity(intent3);

                break;

            case R.id.ofoo:

//                Toast.makeText(getActivity(),"ofo",Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(getActivity(),OFOActivity.class);
                startActivity(intent4);

                break;

            case R.id.mobai:
                Intent intent5 = new Intent(getActivity(),MobikeDemo.class);
                startActivity(intent5);
//                Toast.makeText(getActivity(),"mobike",Toast.LENGTH_SHORT).show();

                break;


        }

    }
}
