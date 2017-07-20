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
public class ILikeMusic extends AppCompatActivity {

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

        centerDialog = new CenterDialog(ILikeMusic.this,R.layout.centerdilog,new int[]{R.id.loading});

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
                        Intent intent = new Intent(ILikeMusic.this, MusicPlayActivity3.class);
                        startActivity(intent);
                    }
                },2000);

            }
        });
    }

    private void initdata() {

        mlist.add(new Song2("Justice Skolnik,Lost Kings,Tinashé","Quit You(Justice Skolnik Remix)",1));
        mlist.add(new Song2("Relient K","PTL",1));
        mlist.add(new Song2("WINNER","FOOL (傻瓜 KR Ver.)",1));
        mlist.add(new Song2("郭旭","不找了",1));
        mlist.add(new Song2("脸红的思春期","우주를 줄게 (给你宇宙)",1));
        mlist.add(new Song2("水晶男孩","세 단어 （THREE WORDS）",1));
        mlist.add(new Song2("宇宙少女","비밀이야 (Secret 秘密)",1));


    }

    private void initslid() {

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("人美声音甜",R.drawable.mmv1);
        file_maps.put("语瞳小仙女",R.drawable.mmv3);
        file_maps.put("人美声音甜",R.drawable.mmv2);
        file_maps.put("活好还不黏", R.drawable.mmm3);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(ILikeMusic.this);
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
