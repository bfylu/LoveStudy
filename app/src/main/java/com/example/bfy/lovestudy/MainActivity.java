package com.example.bfy.lovestudy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bfy.lovestudy.activity.LoginActivity;
import com.example.bfy.lovestudy.activity.SheZhiActivity;
import com.example.bfy.lovestudy.fragment.CourseFragment;
import com.example.bfy.lovestudy.fragment.DownloadFragment;
import com.example.bfy.lovestudy.fragment.HomeFragment;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private LinearLayout linehome,linecourse,linedownload;
    private ImageView homeiv,courseiv,downloadiv,main_tubiao,imageHead;
    private TextView dibu_wenzi,hometv,coursetv,downloadtv,name,zym;
    private ViewPager vp;
    private FragmentManager fragmentManager;
    private List<Fragment> list = new ArrayList<Fragment>();
    private SlideMenu slideMenu;
    private LinearLayout wodekecheng,guanyuwomen,yejianmoshi,shizhi;
    //Fragment事物
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        ImmersionBar.with(this).statusBarColor(R.color.colorBar).init();
        setContentView(R.layout.activity_main);
        find();
        danji();
        homeiv.setImageResource(R.mipmap.dubu12);
        hometv.setTextColor(Color.RED);
        //获取碎片的管理者对象
        fragmentManager = getSupportFragmentManager();
        HomeFragment home = new HomeFragment();
        list.add(home);

        CourseFragment course = new CourseFragment();
        list.add(course);
        DownloadFragment download = new DownloadFragment();
        list.add(download);



        //设置Viewpager的适配器
        vp.setAdapter(new FragmentPagerAdapter(fragmentManager) {

            @Override
            public int getCount() {
                // 返回Viewpager里面item的个数
                return list.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                // 返回碎片
                return list.get(arg0);
            }
        });
        //设置viewpager的切换监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                initTab();
                switch (arg0) {
                    case 0:
                        homeiv.setImageResource(R.mipmap.dubu12);
                        hometv.setTextColor(Color.RED);
                        dibu_wenzi.setText("首页");
                        vp.setCurrentItem(0,false);
                        break;
                    case 1:
                        courseiv.setImageResource(R.mipmap.dubu22);
                        coursetv.setTextColor(Color.RED);
                        dibu_wenzi.setText("课程");
                        vp.setCurrentItem(1,false);
                        break;
                    case 2:
                        downloadiv.setImageResource(R.mipmap.dubu32);
                        downloadtv.setTextColor(Color.RED);
                        dibu_wenzi.setText("下载");
                        vp.setCurrentItem(2,false);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }
    private void find(){
        vp = (ViewPager) findViewById(R.id.vp);

        linehome = (LinearLayout) findViewById(R.id.linehome);
        linecourse = (LinearLayout) findViewById(R.id.linecourse);
        linedownload = (LinearLayout) findViewById(R.id.linedownload);

        homeiv= (ImageView) findViewById(R.id.homeiv);
        courseiv = (ImageView) findViewById(R.id.courseiv);
        downloadiv = (ImageView) findViewById(R.id.downloadiv);

        dibu_wenzi = (TextView) findViewById(R.id.dibu_wenzi);
        hometv = (TextView) findViewById(R.id.hometv);
        coursetv = (TextView) findViewById(R.id.coursetv);
        downloadtv = (TextView) findViewById(R.id.downloadtv);

        main_tubiao = (ImageView) findViewById(R.id.layout_main_tubiao);

        slideMenu = (SlideMenu)findViewById(R.id.slideMenu);
        wodekecheng = (LinearLayout) findViewById(R.id.menu_linear_layout_wodekecheng);
        guanyuwomen = (LinearLayout) findViewById(R.id.menu_linear_layout_guanyuwomen);
        yejianmoshi = (LinearLayout) findViewById(R.id.menu_linear_layout_yejianmoshi);
        shizhi = (LinearLayout) findViewById(R.id.menu_linear_layout_shezhi);

        imageHead = (ImageView) findViewById(R.id.menu_linear_layout_image_head);
        name = (TextView) findViewById(R.id.menu_linear_layout_textview_name);
        zym = (TextView) findViewById(R.id.menu_linear_layout_textview_zym);
    }
    private void danji(){
        linehome.setOnClickListener(this);
        linecourse.setOnClickListener(this);
        linedownload.setOnClickListener(this);

        wodekecheng.setOnClickListener(onClickListener);
        guanyuwomen.setOnClickListener(onClickListener);
        yejianmoshi.setOnClickListener(onClickListener);
        shizhi.setOnClickListener(onClickListener);
        name.setOnClickListener(onClickListener);
        main_tubiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideMenu.switchMenu();
            }
        });
    }

    @Override
    public void onClick(View v) {
        initTab();
        switch (v.getId()){
            case R.id.linehome:
                // fragmentTransaction.replace(R.id.aishikan_content, new homeActivict());
                homeiv.setImageResource(R.mipmap.dubu12);
                hometv.setTextColor(Color.RED);
                dibu_wenzi.setText("首页");
                vp.setCurrentItem(0,false);
                break;
            case R.id.linecourse:
                // fragmentTransaction.replace(R.id.aishikan_content, new courseActivict());
                courseiv.setImageResource(R.mipmap.dubu22);
                coursetv.setTextColor(Color.RED);
                dibu_wenzi.setText("课程");
                vp.setCurrentItem(1,false);
                break;
            case R.id.linedownload:
                // fragmentTransaction.replace(R.id.aishikan_content, new downloadActivict());
                downloadiv.setImageResource(R.mipmap.dubu32);
                downloadtv.setTextColor(Color.RED);
                dibu_wenzi.setText("下载");
                vp.setCurrentItem(2,false);
                break;
        }
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.menu_linear_layout_textview_name:
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));

                case R.id.menu_linear_layout_wodekecheng:

                    break;
                case R.id.menu_linear_layout_guanyuwomen:

                    break;
                case R.id.menu_linear_layout_yejianmoshi:

                    break;
                case R.id.menu_linear_layout_shezhi:
                    Intent intent_shezhi = new Intent(MainActivity.this, SheZhiActivity.class);
                    startActivity(intent_shezhi);
                    break;
                default:
                    break;
            }
        }
    };
    private void initTab(){
        homeiv.setImageResource(R.mipmap.dubu11);
        hometv.setTextColor(Color.BLACK);
        courseiv.setImageResource(R.mipmap.dubu21);
        coursetv.setTextColor(Color.BLACK);
        downloadiv.setImageResource(R.mipmap.dubu31);
        downloadtv.setTextColor(Color.BLACK);
    }
}
