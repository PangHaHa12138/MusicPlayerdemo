package com.panghaha.it.mymusicplayerdemo.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.panghaha.it.mymusicplayerdemo.R;
import com.panghaha.it.mymusicplayerdemo.widget.CenterDialog;

import java.util.ArrayList;
import java.util.HashMap;
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
 * Created by PangHaHa12138 on 2017/7/6.
 */
public class YTFM extends AppCompatActivity {

    private ListView ytfmlistview;
    private View headview;
    private SliderLayout sliderLayout;
    private List<Song2> mlist;
    private CenterDialog centerDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ytfm);
        initview();
        initslid();
    }

    private void initview() {

        ytfmlistview = (ListView) findViewById(R.id.ytmusiclist);
        headview = LayoutInflater.from(this).inflate(R.layout.musiclisthead,ytfmlistview,false);
        sliderLayout = (SliderLayout)headview.findViewById(R.id.slider2);

        centerDialog = new CenterDialog(YTFM.this,R.layout.centerdilog,new int[]{R.id.loading});

        mlist = new ArrayList<>();
        initdata();
        YtAdapter ytAdapter = new YtAdapter(this,mlist);
        ytfmlistview.addHeaderView(headview);
        ytfmlistview.setAdapter(ytAdapter);

        ytfmlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                centerDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        centerDialog.dismiss();
                        Intent intent = new Intent(YTFM.this, MusicPlayActivityYTFM.class);
                        startActivity(intent);
                    }
                },2000);

            }
        });
    }

    private void initdata() {

        mlist.add(new Song2("NJ语瞳","【开心一刻】去年反手摸肚脐，今年A4腰风靡",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】我终于要嫁出去了!",23));
        mlist.add(new Song2("NJ语瞳","真爱粉-人气主播赛",22));
        mlist.add(new Song2("全体百思女神","西游记之女儿国奇遇记",25));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",20));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",20));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","你家里缺宠物么，就是读过大学会说中国话的那种",22));
        mlist.add(new Song2("NJ语瞳","教你一秒制服冷战中的老婆",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","人生四大经典骗局，你至少上过一次当",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",21));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",21));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",18));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",20));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",21));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));
        mlist.add(new Song2("NJ语瞳","【开心一刻】",22));


    }

    private void initslid() {

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("人美声音甜",R.drawable.mmv1);
        file_maps.put("语瞳小仙女",R.drawable.mmv3);
        file_maps.put("人美声音甜",R.drawable.mmv2);
        file_maps.put("活好还不黏", R.drawable.mmm3);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(YTFM.this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
//                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);

    }
}
