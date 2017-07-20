package com.panghaha.it.mymusicplayerdemo.UI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.panghaha.it.mymusicplayerdemo.R;
import com.panghaha.it.mymusicplayerdemo.model.MusicData;
import com.panghaha.it.mymusicplayerdemo.service.MusicService;
import com.panghaha.it.mymusicplayerdemo.utils.DisplayUtil;
import com.panghaha.it.mymusicplayerdemo.utils.FastBlurUtil;
import com.panghaha.it.mymusicplayerdemo.widget.BackgourndAnimationRelativeLayout;
import com.panghaha.it.mymusicplayerdemo.widget.DiscView;
import com.panghaha.it.mymusicplayerdemo.widget.DiscView.IPlayInfo;
import com.panghaha.it.mymusicplayerdemo.widget.DiscView.MusicChangedStatus;

import java.io.Serializable;
import java.util.ArrayList;
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
public class MusicPlayActivityYTFM extends AppCompatActivity implements IPlayInfo,View.OnClickListener{

    private DiscView mDisc;
    private Toolbar mToolbar;
    private SeekBar mSeekBar;
    private ImageView mIvPlayOrPause, mIvNext, mIvLast;
    private TextView mTvMusicDuration,mTvTotalMusicDuration;
    private BackgourndAnimationRelativeLayout mRootLayout;
    public static final int MUSIC_MESSAGE = 0;

    public static final String PARAM_MUSIC_LIST = "PARAM_MUSIC_LIST";

    private Handler mMusicHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSeekBar.setProgress(mSeekBar.getProgress() + 1000);
            mTvMusicDuration.setText(duration2Time(mSeekBar.getProgress()));
            startUpdateSeekBarProgress();
        }
    };

    private MusicReceiver mMusicReceiver = new MusicReceiver();
    private List<MusicData> mMusicDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.musicplaying);
        initMusicDatas();
        initView();
        initMusicReceiver();
        makeStatusBarTransparent();
    }

    private void initMusicReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicService.ACTION_STATUS_MUSIC_PLAY);
        intentFilter.addAction(MusicService.ACTION_STATUS_MUSIC_PAUSE);
        intentFilter.addAction(MusicService.ACTION_STATUS_MUSIC_DURATION);
        intentFilter.addAction(MusicService.ACTION_STATUS_MUSIC_COMPLETE);
        /*注册本地广播*/
        LocalBroadcastManager.getInstance(this).registerReceiver(mMusicReceiver,intentFilter);
    }

    private void initView() {
        mDisc = (DiscView) findViewById(R.id.discview);
        mIvNext = (ImageView) findViewById(R.id.ivNext);
        mIvLast = (ImageView) findViewById(R.id.ivLast);
        mIvPlayOrPause = (ImageView) findViewById(R.id.ivPlayOrPause);
        mTvMusicDuration = (TextView) findViewById(R.id.tvCurrentTime);
        mTvTotalMusicDuration = (TextView) findViewById(R.id.tvTotalTime);
        mSeekBar = (SeekBar) findViewById(R.id.musicSeekBar);
        mRootLayout = (BackgourndAnimationRelativeLayout) findViewById(R.id.rootLayout);

        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);

        mDisc.setPlayInfoListener(this);
        mIvLast.setOnClickListener(this);
        mIvNext.setOnClickListener(this);
        mIvPlayOrPause.setOnClickListener(this);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvMusicDuration.setText(duration2Time(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                stopUpdateSeekBarProgree();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekTo(seekBar.getProgress());
                startUpdateSeekBarProgress();
            }
        });

        mTvMusicDuration.setText(duration2Time(0));
        mTvTotalMusicDuration.setText(duration2Time(0));
        mDisc.setMusicDataList(mMusicDatas);
    }

    private void stopUpdateSeekBarProgree() {
        mMusicHandler.removeMessages(MUSIC_MESSAGE);
    }

    /*设置透明状态栏*/
    private void makeStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void initMusicDatas() {
//        MusicData musicData1 = new MusicData(R.raw.music1, R.raw.ic_music1, "寻", "三亩地");
//        MusicData musicData2 = new MusicData(R.raw.music2, R.raw.ic_music2, "Nightingale", "YANI");
//        MusicData musicData3 = new MusicData(R.raw.music3, R.raw.ic_music3, "Cornfield Chase", "Hans Zimmer");
//        mMusicDatas.add(musicData1);
//        mMusicDatas.add(musicData2);
//        mMusicDatas.add(musicData3);

//        mMusicDatas.add(new MusicData(R.raw.m2, R.drawable.pan2, "没有双截棍，依然一身正气", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m3, R.drawable.pan2, "中药的秘方是怎么来的", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m4, R.drawable.pan2, "学生跟老罗较劲的怪问题", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m11, R.drawable.pan2, "老罗被问到什么是牛奶路", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m14, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m18, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m21, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m26, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m28, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m29, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m31, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m35, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m39, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m40, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m45, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m46, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m49, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m52, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m55, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m58, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m59, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m60, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m61, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m64, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m65, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m70, R.drawable.pan2, "幸运的意外", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m71, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m72, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m74, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m78, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m79, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m80, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m82, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m83, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m84, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m86, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m87, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m91, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m92, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m94, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m97, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m99, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m100, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m105, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m106, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m107, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m111, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m112, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m116, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m117, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m120, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m123, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m125, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m129, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m130, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m135, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m137, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m143, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m146, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m147, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m149, R.drawable.pan2, "罗永浩语录", "罗永浩"));
//        mMusicDatas.add(new MusicData(R.raw.m153, R.drawable.pan2, "罗永浩语录", "罗永浩"));


        mMusicDatas.add(new MusicData(R.raw.m1, R.drawable.pan2, "【开心一刻】去年反手摸肚脐，今年A4腰风靡", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m5, R.drawable.yutong1, "【开心一刻】我终于要嫁出去了!", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m6, R.drawable.yutong1, "真爱粉-人气主播赛", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m7, R.drawable.mmpp, "西游记之女儿国奇遇记", "全体百思女神"));
        mMusicDatas.add(new MusicData(R.raw.m8, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m9, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m10, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m12, R.drawable.pan2, "【开心一刻】你家里缺宠物么，就是读过大学会说中国话的那种", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m13, R.drawable.mmpp, "【开心一刻】教你一秒制服冷战中的老婆", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m15, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m17, R.drawable.pan2, "【开心一刻】人生四大经典骗局，你至少上过一次当", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m16, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m19, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m20, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m22, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m23, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m24, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m25, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m27, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m30, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m33, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m34, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m36, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m37, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m38, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m41, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m42, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m43, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m44, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m47, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m48, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m50, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m51, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m53, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m54, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m56, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m57, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m62, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m63, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m66, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m67, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m68, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m69, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m73, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m75, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m76, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m77, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m81, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m85, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m88, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m89, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m90, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m93, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m95, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m96, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m98, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m101, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m103, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m104, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m108, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m109, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m110, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m113, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m114, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m115, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m118, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m119, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m121, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m122, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m124, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m126, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m127, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m128, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m131, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m132, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m133, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m134, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m136, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m138, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m139, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m140, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m141, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m142, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m145, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m148, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m150, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m151, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m152, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m154, R.drawable.mmpp, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m155, R.drawable.yutong1, "【开心一刻】", "NJ语瞳"));
        mMusicDatas.add(new MusicData(R.raw.m156, R.drawable.pan2, "【开心一刻】", "NJ语瞳"));

//        MusicService2 musicService2 = new MusicService2();
//        musicService2.stop();

        Intent intent = new Intent(this, MusicService.class);
        intent.putExtra(PARAM_MUSIC_LIST, (Serializable) mMusicDatas);
        startService(intent);
    }

    private void try2UpdateMusicPicBackground(final int musicPicRes) {
        if (mRootLayout.isNeed2UpdateBackground(musicPicRes)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Drawable foregroundDrawable = getForegroundDrawable(musicPicRes);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRootLayout.setForeground(foregroundDrawable);
                            mRootLayout.beginAnimation();
                        }
                    });
                }
            }).start();
        }
    }

    private Drawable getForegroundDrawable(int musicPicRes) {
        /*得到屏幕的宽高比，以便按比例切割图片一部分*/
        final float widthHeightSize = (float) (DisplayUtil.getScreenWidth(MusicPlayActivityYTFM.this)
                * 1.0 / DisplayUtil.getScreenHeight(this) * 1.0);

        Bitmap bitmap = getForegroundBitmap(musicPicRes);
        int cropBitmapWidth = (int) (widthHeightSize * bitmap.getHeight());
        int cropBitmapWidthX = (int) ((bitmap.getWidth() - cropBitmapWidth) / 2.0);

        /*切割部分图片*/
        Bitmap cropBitmap = Bitmap.createBitmap(bitmap, cropBitmapWidthX, 0, cropBitmapWidth,
                bitmap.getHeight());
        /*缩小图片*/
        Bitmap scaleBitmap = Bitmap.createScaledBitmap(cropBitmap, bitmap.getWidth() / 50, bitmap
                .getHeight() / 50, false);
        /*模糊化*/
        final Bitmap blurBitmap = FastBlurUtil.doBlur(scaleBitmap, 8, true);

        final Drawable foregroundDrawable = new BitmapDrawable(blurBitmap);
        /*加入灰色遮罩层，避免图片过亮影响其他控件*/
        foregroundDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        return foregroundDrawable;
    }

    private Bitmap getForegroundBitmap(int musicPicRes) {
        int screenWidth = DisplayUtil.getScreenWidth(this);
        int screenHeight = DisplayUtil.getScreenHeight(this);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(getResources(), musicPicRes, options);
        int imageWidth = options.outWidth;
        int imageHeight = options.outHeight;

        if (imageWidth < screenWidth && imageHeight < screenHeight) {
            return BitmapFactory.decodeResource(getResources(), musicPicRes);
        }

        int sample = 2;
        int sampleX = imageWidth / DisplayUtil.getScreenWidth(this);
        int sampleY = imageHeight / DisplayUtil.getScreenHeight(this);

        if (sampleX > sampleY && sampleY > 1) {
            sample = sampleX;
        } else if (sampleY > sampleX && sampleX > 1) {
            sample = sampleY;
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = sample;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        return BitmapFactory.decodeResource(getResources(), musicPicRes, options);
    }

    @Override
    public void onMusicInfoChanged(String musicName, String musicAuthor) {
        getSupportActionBar().setTitle(musicName);
        getSupportActionBar().setSubtitle(musicAuthor);
    }

    @Override
    public void onMusicPicChanged(int musicPicRes) {
        try2UpdateMusicPicBackground(musicPicRes);
    }

    @Override
    public void onMusicChanged(MusicChangedStatus musicChangedStatus) {
        switch (musicChangedStatus) {
            case PLAY:{
                play();
                break;
            }
            case PAUSE:{
                pause();
                break;
            }
            case NEXT:{
                next();
                break;
            }
            case LAST:{
                last();
                break;
            }
            case STOP:{
                stop();
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mIvPlayOrPause) {
            mDisc.playOrPause();
        } else if (v == mIvNext) {
            mDisc.next();
        } else if (v == mIvLast) {
            mDisc.last();
        }
    }

    private void play() {
        optMusic(MusicService.ACTION_OPT_MUSIC_PLAY);
        startUpdateSeekBarProgress();
    }

    private void pause() {
        optMusic(MusicService.ACTION_OPT_MUSIC_PAUSE);
        stopUpdateSeekBarProgree();
    }

    private void stop() {
        stopUpdateSeekBarProgree();
        mIvPlayOrPause.setImageResource(R.drawable.ic_play);
        mTvMusicDuration.setText(duration2Time(0));
        mTvTotalMusicDuration.setText(duration2Time(0));
        mSeekBar.setProgress(0);
    }

    private void next() {
        mRootLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                optMusic(MusicService.ACTION_OPT_MUSIC_NEXT);
            }
        }, 500);
        stopUpdateSeekBarProgree();
        mTvMusicDuration.setText(duration2Time(0));
        mTvTotalMusicDuration.setText(duration2Time(0));
    }

    private void last() {
        mRootLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                optMusic(MusicService.ACTION_OPT_MUSIC_LAST);
            }
        }, 500);
        stopUpdateSeekBarProgree();
        mTvMusicDuration.setText(duration2Time(0));
        mTvTotalMusicDuration.setText(duration2Time(0));
    }

    private void complete(boolean isOver) {
        if (isOver) {
            mDisc.stop();
        } else {
            mDisc.next();
        }
    }

    private void optMusic(final String action) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(action));
    }

    private void seekTo(int position) {
        Intent intent = new Intent(MusicService.ACTION_OPT_MUSIC_SEEK_TO);
        intent.putExtra(MusicService.PARAM_MUSIC_SEEK_TO,position);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void startUpdateSeekBarProgress() {
        /*避免重复发送Message*/
        stopUpdateSeekBarProgree();
        mMusicHandler.sendEmptyMessageDelayed(0,1000);
    }

    /*根据时长格式化称时间文本*/
    private String duration2Time(int duration) {
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;

        return (min < 10 ? "0" + min : min + "") + ":" + (sec < 10 ? "0" + sec : sec + "");
    }

    private void updateMusicDurationInfo(int totalDuration) {
        mSeekBar.setProgress(0);
        mSeekBar.setMax(totalDuration);
        mTvTotalMusicDuration.setText(duration2Time(totalDuration));
        mTvMusicDuration.setText(duration2Time(0));
        startUpdateSeekBarProgress();
    }

    class MusicReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(MusicService.ACTION_STATUS_MUSIC_PLAY)) {
                mIvPlayOrPause.setImageResource(R.drawable.ic_pause);
                int currentPosition = intent.getIntExtra(MusicService.PARAM_MUSIC_CURRENT_POSITION, 0);
                mSeekBar.setProgress(currentPosition);
                if(!mDisc.isPlaying()){
                    mDisc.playOrPause();
                }
            } else if (action.equals(MusicService.ACTION_STATUS_MUSIC_PAUSE)) {
                mIvPlayOrPause.setImageResource(R.drawable.ic_play);
                if (mDisc.isPlaying()) {
                    mDisc.playOrPause();
                }
            } else if (action.equals(MusicService.ACTION_STATUS_MUSIC_DURATION)) {
                int duration = intent.getIntExtra(MusicService.PARAM_MUSIC_DURATION, 0);
                updateMusicDurationInfo(duration);
            } else if (action.equals(MusicService.ACTION_STATUS_MUSIC_COMPLETE)) {
                boolean isOver = intent.getBooleanExtra(MusicService.PARAM_MUSIC_IS_OVER, true);
                complete(isOver);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMusicReceiver);
    }
}
