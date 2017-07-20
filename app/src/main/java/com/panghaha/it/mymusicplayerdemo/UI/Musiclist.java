package com.panghaha.it.mymusicplayerdemo.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.panghaha.it.mymusicplayerdemo.R;

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
 * Created by PangHaHa12138 on 2017/7/4.
 */
public class Musiclist extends AppCompatActivity {

    private ListView musiclist;
    private SliderLayout sliderLayout;
    private List<Song> list;
    private View headview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musiclist);


        initview();
        initdata();
    }

    private void initview() {


        musiclist = (ListView) findViewById(R.id.musiclist);
        headview = LayoutInflater.from(this).inflate(R.layout.musiclisthead,musiclist,false);
        sliderLayout = (SliderLayout)headview.findViewById(R.id.slider2);
        list = new ArrayList<>();
        list = MusicUtils.getMusicData(this);

        MusiclistAdapter musiclistAdapter = new MusiclistAdapter(Musiclist.this,list);
        musiclist.addHeaderView(headview);
        musiclist.setAdapter(musiclistAdapter);

        musiclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Musiclist.this,MusicPlayActivity2.class);
                Log.d("Path===>",list.get(position-1).getPath()+"");
                Log.d("singer===>",list.get(position-1).getSinger()+"");
                Log.d("song===>",list.get(position-1).getSong()+"");

                intent.putExtra("path",list.get(position-1).getPath());
                intent.putExtra("singer",list.get(position-1).getSinger());
                intent.putExtra("song",list.get(position-1).getSong());
                startActivity(intent);
            }
        });

    }

    private void initdata() {

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("人美声音甜",R.drawable.mm1);
        file_maps.put("语瞳小仙女",R.drawable.mm2);
        file_maps.put("人美声音甜",R.drawable.amd3);
        file_maps.put("活好还不黏", R.drawable.mmm3);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(Musiclist.this);
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
//        sliderLayout.addOnPageChangeListener(this);
    }

    @Override
    protected void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();

    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//    @Override
//    public void onSliderClick(BaseSliderView slider) {
//        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
//    }
}
