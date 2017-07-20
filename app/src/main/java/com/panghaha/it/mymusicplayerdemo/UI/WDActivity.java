package com.panghaha.it.mymusicplayerdemo.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

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
 * Created by PangHaHa12138 on 2017/7/5.
 */
public class WDActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SliderLayout sliderLayout;
    StaggeredGridLayoutManager layoutManager;
    private List<meinv> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wdactivity);

        initdata();
        initview();
    }


    private void initview() {

        recyclerView = (RecyclerView) findViewById(R.id.recycleviewww);
        sliderLayout = (SliderLayout) findViewById(R.id.viewpagere);
         layoutManager =
                //这里 3 代表三列 后面代表瀑布流朝向
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        MeiziAdapter adapter = new MeiziAdapter(list,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        SpcaceDecoration sc = new SpcaceDecoration(1);//设置间距
        recyclerView.addItemDecoration(sc);

        recyclerView.setNestedScrollingEnabled(false);
        //NesterScrollView不允许滑动
        recyclerView.setHasFixedSize(true);
        //RecycleView固定大小会实现优化
        adapter.setOnItemClickListener(new MeiziAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(WDActivity.this,"我被点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("语瞳小仙女", R.drawable.mmv1);
        file_maps.put("人美声音甜", R.drawable.mmv2);
        file_maps.put("人美声音甜", R.drawable.mmv3);
//        file_maps.put("人美声音甜",R.drawable.mm1);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
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
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);
//        sliderLayout.addOnPageChangeListener(this);
    }

    private void initdata() {


               meinv meinv = new meinv(R.drawable.ol1,"小姐姐");
               list.add(meinv);
               meinv meinv2 = new meinv(R.drawable.ol2,"萝莉");
               list.add(meinv2);
               meinv meinv3 = new meinv(R.drawable.ol3,"御姐");
               list.add(meinv3);
               meinv meinv4 = new meinv(R.drawable.ol4,"萌妹子");
               list.add(meinv4);
               meinv meinv5 = new meinv(R.drawable.ol5,"小姐姐");
               list.add(meinv5);
               meinv meinv6 = new meinv(R.drawable.ol6,"小姐姐");
               list.add(meinv6);
               meinv meinv7 = new meinv(R.drawable.ol7,"小姐姐");
               list.add(meinv7);
               meinv meinv8 = new meinv(R.drawable.ol8,"萝莉");
               list.add(meinv8);
               meinv meinv9 = new meinv(R.drawable.ol9,"御姐");
               list.add(meinv9);
               meinv meinv10 = new meinv(R.drawable.ol10,"萌妹子");
               list.add(meinv10);
               meinv meinv11 = new meinv(R.drawable.ol11,"小姐姐");
               list.add(meinv11);
               meinv meinv12= new meinv(R.drawable.ol12,"小姐姐");
               list.add(meinv12);
               meinv meinv13 = new meinv(R.drawable.ol13,"小姐姐");
               list.add(meinv13);
               meinv meinv14 = new meinv(R.drawable.ol14,"萝莉");
               list.add(meinv14);
               meinv meinv15 = new meinv(R.drawable.ol15,"御姐");
               list.add(meinv15);
               meinv meinv16 = new meinv(R.drawable.ol16,"萌妹子");
               list.add(meinv16);
               meinv meinv17 = new meinv(R.drawable.ol17,"小姐姐");
               list.add(meinv17);
               meinv meinv18 = new meinv(R.drawable.ol18,"小姐姐");
               list.add(meinv18);
               meinv meinv19 = new meinv(R.drawable.ol19,"小姐姐");
               list.add(meinv19);
               meinv meinv20 = new meinv(R.drawable.ol20,"萝莉");
               list.add(meinv20);
               meinv meinv21 = new meinv(R.drawable.ol21,"御姐");
               list.add(meinv21);
               meinv meinv22 = new meinv(R.drawable.ol22,"萌妹子");
               list.add(meinv22);
               meinv meinv23 = new meinv(R.drawable.ol23,"小姐姐");
               list.add(meinv23);
               meinv meinv24 = new meinv(R.drawable.ol24,"小姐姐");
               list.add(meinv24);
               meinv meinv25 = new meinv(R.drawable.ol25,"小姐姐");
               list.add(meinv25);
               meinv meinv26 = new meinv(R.drawable.ol26,"萝莉");
               list.add(meinv26);
               meinv meinv27 = new meinv(R.drawable.ol27,"御姐");
               list.add(meinv27);
               meinv meinv28 = new meinv(R.drawable.ol28,"萌妹子");
               list.add(meinv28);
               meinv meinv29 = new meinv(R.drawable.ol29,"小姐姐");
               list.add(meinv28);
               meinv meinv30 = new meinv(R.drawable.ol30,"小姐姐");
               list.add(meinv30);
               meinv meinv31 = new meinv(R.drawable.ol31,"小姐姐");
               list.add(meinv31);
               meinv meinv32 = new meinv(R.drawable.ol32,"萝莉");
               list.add(meinv32);
               meinv meinv33 = new meinv(R.drawable.ol33,"御姐");
               list.add(meinv33);
               meinv meinv34 = new meinv(R.drawable.ol34,"萌妹子");
               list.add(meinv34);
               meinv meinv35 = new meinv(R.drawable.ol35,"小姐姐");
               list.add(meinv35);
               meinv meinv36 = new meinv(R.drawable.ol36,"小姐姐");
               list.add(meinv36);
               meinv meinv37 = new meinv(R.drawable.ol37,"小姐姐");
               list.add(meinv37);
               meinv meinv38 = new meinv(R.drawable.ol38,"萝莉");
               list.add(meinv38);
               meinv meinv39 = new meinv(R.drawable.ol40,"御姐");
               list.add(meinv39);
               meinv meinv40 = new meinv(R.drawable.ol41,"萌妹子");
               list.add(meinv40);
               meinv meinv41 = new meinv(R.drawable.ol42,"小姐姐");
               list.add(meinv41);
               meinv meinv42= new meinv(R.drawable.ol43,"小姐姐");
               list.add(meinv42);
               meinv meinv43 = new meinv(R.drawable.ol44,"小姐姐");
               list.add(meinv43);
               meinv meinv44 = new meinv(R.drawable.ol45,"萝莉");
               list.add(meinv44);
               meinv meinv45 = new meinv(R.drawable.ol49,"御姐");
               list.add(meinv45);
               meinv meinv46 = new meinv(R.drawable.ol46,"萌妹子");
               list.add(meinv46);
               meinv meinv47 = new meinv(R.drawable.ol47,"小姐姐");
               list.add(meinv47);
               meinv meinv48 = new meinv(R.drawable.ol48,"小姐姐");
               list.add(meinv48);
               meinv meinv49 = new meinv(R.drawable.ol50,"小姐姐");
               list.add(meinv49);
               meinv meinv50 = new meinv(R.drawable.ol51,"萝莉");
               list.add(meinv50);
               meinv meinv51 = new meinv(R.drawable.ol52,"御姐");
               list.add(meinv51);
               meinv meinv52 = new meinv(R.drawable.ol53,"萌妹子");
               list.add(meinv52);
               meinv meinv53 = new meinv(R.drawable.ol54,"小姐姐");
               list.add(meinv53);
               meinv meinv54 = new meinv(R.drawable.ol55,"小姐姐");
               list.add(meinv54);
               meinv meinv55 = new meinv(R.drawable.ol56,"小姐姐");
               list.add(meinv55);
               meinv meinv56 = new meinv(R.drawable.ol57,"萝莉");
               list.add(meinv56);
               meinv meinv57 = new meinv(R.drawable.ol39,"萝莉");
               list.add(meinv57);



    }
    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        sliderLayout.stopAutoCycle();
        super.onStop();
    }


}
