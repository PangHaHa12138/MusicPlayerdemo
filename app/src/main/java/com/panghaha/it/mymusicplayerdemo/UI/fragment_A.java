package com.panghaha.it.mymusicplayerdemo.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
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
 * Created by PangHaHa12138 on 2017/6/25.
 */
public class fragment_A extends Fragment implements BaseSliderView.OnSliderClickListener,ViewPagerEx.OnPageChangeListener{

    private View mview;
    private SliderLayout mDemoSlider;
    private RecyclerView recyclerView;
    private List<meinv> list = new ArrayList<>();
    private View headview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mview = inflater.inflate(R.layout.fragmenta,container,false);
        recyclerView = (RecyclerView) mview.findViewById(R.id.recycleview);
        StaggeredGridLayoutManager layoutManager =
                //这里 3 代表三列 后面代表瀑布流朝向
                new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        initlist();//初始化模拟数据

        mDemoSlider = (SliderLayout) mview.findViewById(R.id.slider);

        MeiziAdapter adapter = new MeiziAdapter(list,getActivity());
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
                Toast.makeText(getActivity(),"我被点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });

        setloopconfig();

        return mview;
    }

    private void initlist() {
        for (int i = 0; i < 5; i++) {
            meinv meinv = new meinv(R.drawable.yutong1,"天王盖地虎");
            list.add(meinv);
            meinv meinv2 = new meinv(R.drawable.yutong2,"语瞳一米五");
            list.add(meinv2);
            meinv meinv3 = new meinv(R.drawable.yut1,"哈哈");
            list.add(meinv3);
            meinv meinv4 = new meinv(R.drawable.yut2,"还有这种操作");
            list.add(meinv4);
            meinv meinv5 = new meinv(R.drawable.yut3,"你有freestyle吗");
            list.add(meinv5);
            meinv meinv6 = new meinv(R.drawable.yut4,"恍恍惚惚");
            list.add(meinv6);
        }


    }

    private void setloopconfig() {


        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("美丽又可爱",R.drawable.yut1);
        file_maps.put("语瞳小仙女",R.drawable.yut2);
        file_maps.put("活好还不黏",R.drawable.yut3);
        file_maps.put("人美声音甜", R.drawable.yut4);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);




//        loopViewPager.setLoop_ms(2000);//轮播的速度(毫秒)
//        loopViewPager.setLoop_duration(1000);//滑动的速率(毫秒)
//        loopViewPager.setLoop_style(LoopStyle.Empty);//轮播的样式-默认empty
//        loopViewPager.setIndicatorLocation(IndicatorLocation.Center);//指示器位置-中Center
//        loopViewPager.setNormalBackground(R.color.white);//默认指示器颜色
//        loopViewPager.setSelectedBackground(R.color.red);//选中指示器颜色
//
//        loopViewPager.initializeData(getActivity());//初始化数据
//        ArrayList<BannerInfo> bannerInfos = new ArrayList<>();
//        bannerInfos.add(new BannerInfo<>(R.drawable.yut1, "第一张图片"));
//        bannerInfos.add(new BannerInfo<>(R.drawable.yut2, "第三张图片"));
//        bannerInfos.add(new BannerInfo<>(R.drawable.yut3, "第四张图片"));
//        bannerInfos.add(new BannerInfo<>(R.drawable.yut4, "第五张图片"));
//        loopViewPager.setOnLoadImageViewListener(new OnDefaultImageViewLoader() {
//            @Override
//            public void onLoadImageView(ImageView imageView, Object parameter) {
//
//            }
//        });//设置图片加载&自定义图片监听
//        loopViewPager.setOnBannerItemClickListener(new OnBannerItemClickListener() {
//            @Override
//            public void onBannerClick(int index, ArrayList<BannerInfo> banner) {
//                Toast.makeText(getActivity(),"语瞳真是可爱呢",Toast.LENGTH_SHORT).show();
//            }
//        });//设置监听
//        loopViewPager.setLoopData(bannerInfos);//设置数据
//
//        loopViewPager.startLoop();

    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
