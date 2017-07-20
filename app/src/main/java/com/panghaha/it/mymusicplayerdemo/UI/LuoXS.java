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
public class LuoXS extends AppCompatActivity {

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

        centerDialog = new CenterDialog(LuoXS.this,R.layout.centerdilog,new int[]{R.id.loading});

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
                        Intent intent = new Intent(LuoXS.this, MusicPlayActivity.class);
                        startActivity(intent);
                    }
                },2000);

            }
        });
    }

    private void initdata() {

        mlist.add(new Song2("罗永浩","没有双截棍，依然一身正气",22));
        mlist.add(new Song2("罗永浩","中药的秘方是怎么来的",23));
        mlist.add(new Song2("罗永浩","学生跟老罗较劲的怪问题",22));
        mlist.add(new Song2("罗永浩","老罗被问到什么是牛奶路",25));
        mlist.add(new Song2("罗永浩","老罗为何做火车扔鞋",20));
        mlist.add(new Song2("罗永浩","老外用含笑九泉表示自己开心",20));
        mlist.add(new Song2("罗永浩","禁止携带西红柿和鸡蛋",22));
        mlist.add(new Song2("罗永浩","听说黎明要来了，北京的小姑娘从8岁等到18岁",22));
        mlist.add(new Song2("罗永浩","新东方靠的什么",22));
        mlist.add(new Song2("罗永浩","一夫多妻制",22));
        mlist.add(new Song2("罗永浩","哥哥为何不同意父亲买沙袋",22));
        mlist.add(new Song2("罗永浩","学生被老罗说的抱头鼠窜",21));
        mlist.add(new Song2("罗永浩","手语不是全球通用的",22));
        mlist.add(new Song2("罗永浩","不同种族对募捐的态度",21));
        mlist.add(new Song2("罗永浩","愤世嫉俗不等于犬儒主义者",22));
        mlist.add(new Song2("罗永浩","小鸟是怎么认识妈妈的",22));
        mlist.add(new Song2("罗永浩","法律健全也会被敲诈",18));
        mlist.add(new Song2("罗永浩","如何对待叛逆少年，老罗给你支招",22));
        mlist.add(new Song2("罗永浩","如果你有罪上帝会勒死你",22));
        mlist.add(new Song2("罗永浩","过年看电视春晚的无奈",22));
        mlist.add(new Song2("罗永浩","一咬牙，沦为教师",22));
        mlist.add(new Song2("罗永浩","猫为何爱抓毛线团",22));
        mlist.add(new Song2("罗永浩","高山族是怎么来的",22));
        mlist.add(new Song2("罗永浩","这小子不是恶心我吧",20));
        mlist.add(new Song2("罗永浩","【仁者见仁，淫者见淫】",22));
        mlist.add(new Song2("罗永浩","【令人愉悦的忧伤】",22));
        mlist.add(new Song2("罗永浩","【彪悍的人生不需要解释】",22));
        mlist.add(new Song2("罗永浩","【再好好想想，感情还没破裂吗】",21));
        mlist.add(new Song2("罗永浩","【刘欢给女儿起名字】",22));
        mlist.add(new Song2("罗永浩","【每一本被焚毁的书都照亮了这个世界】",22));

    }

    private void initslid() {

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("人美声音甜",R.drawable.mmv1);
        file_maps.put("语瞳小仙女",R.drawable.mmv3);
        file_maps.put("人美声音甜",R.drawable.mmv2);
        file_maps.put("活好还不黏", R.drawable.mmm3);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(LuoXS.this);
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
