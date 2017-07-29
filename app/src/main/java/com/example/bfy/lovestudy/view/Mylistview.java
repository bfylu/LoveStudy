package com.example.bfy.lovestudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.bfy.lovestudy.R;


/**
 * Created by JOHN on 2017/5/15.
 */

public class Mylistview extends ListView implements AbsListView.OnScrollListener {
    private View footView,topView;
    private int footHeight;
    private int topHeight;
    private static final int pull_freash=0;//下拉刷新状态
    private static final int release_freash=1;//释放刷新状态
    private static final int freashing=2;//正在刷新状态
    private boolean refresh=false;//默认没有刷新
    private int currenState=pull_freash;//初始状态
    private int currentY=0;//按下的Y坐标
    public Mylistview(Context context) {
        this(context,null);
    }

    public Mylistview(Context context, AttributeSet attrs) {
        super(context, attrs);

        initFootView();//底部view
        initTopView();//头部view
        initListener();//listview监听滑动事件
    }
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        //设置为Integer.MAX_VALUE>>2 是listview全部展开
//        int measureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
//        //设置为400是设置listview的高度只能有400 不全部展开   实现可以滑动的效果
//        int measureSpec1 = MeasureSpec.makeMeasureSpec(400, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, measureSpec1);
//    }

    /**
     * listview监听滑动事件
     */
    private void initListener() {
        setOnScrollListener(this);
    }

    /**
     * 初始化头部view
     */
    private void initTopView() {
        topView=LayoutInflater.from(getContext()).inflate(R.layout.layout_fresh,null);
        topView.measure(0,0);//测量
        topHeight=topView.getMeasuredHeight();//获取测量的高度
        topView.setPadding(0,-topHeight,0,0);//默认设置topView不可见
        addHeaderView(topView);

    }

    /**
     * 初始化底部view
     */
    private void initFootView() {
        footView= LayoutInflater.from(getContext()).inflate(R.layout.layout_fresh,null);
        footView.measure(0,0);//测量
        footHeight=footView.getMeasuredHeight();//获取测量的高度
        footView.setPadding(0,-footHeight,0,0);//默认设置footView不可见
        addFooterView(footView);//添加底部view
    }

    /**
     * 触摸事件
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //按下
                currentY= (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //移动
                //判断当前状态正在刷新
                if (currenState==freashing) break;
                //手指移动的高度
                int moveHeight= (int) (ev.getY()-currentY);
                int paddingTop=moveHeight-topHeight;//计算topview距离顶部的高度
                //判断向下拉的距离 并且当前处于在第零个item
                if (paddingTop>-topHeight && getFirstVisiblePosition()==0){
                    topView.setPadding(0,paddingTop,0,0);
                    if (paddingTop>=0 && currenState==pull_freash){
                        currenState=release_freash;
                    }
                    else if (paddingTop<0 && currenState==release_freash){
                        currenState=pull_freash;
                    }

                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                //弹起
                if (currenState==release_freash){
                    topView.setPadding(0,0,0,0);//弹回到本来位置
                    currenState=freashing;
                    myinterface.loadMore();
                }
                else {
                    topView.setPadding(0,-topHeight,0,0);
                }

                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * SCROLL_STATE_IDLE  滑动停止
     * SCROLL_STATE_TOUCH_SCROLL  正在滑动
     * SCROLL_STATE_FLING 加速滑动
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //listview的滑动状态
        if (scrollState==OnScrollListener.SCROLL_STATE_IDLE &&
                getLastVisiblePosition()==getCount()-1
                && !refresh){
            //表示用户滑动到最后一个，显示下拉刷新布局
            footView.setPadding(0,0,0,0);//设置可见
            refresh=true;//正在刷新的标识
            if (myinterface!=null)
                myinterface.onFreshing();//回调到监听者
        }
    }

    /**
     * 刷新完成
     */
    public void finishView(){
        if (refresh){
            //上拉更多
            refresh=false;
            footView.setPadding(0,-footHeight,0,0);

        }
        else {
            //下拉刷新
            currenState=pull_freash;
            topView.setPadding(0,-topHeight,0,0);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //滚动时触发
    }

    public void setOnRefreshlistener(Myinterface myinterface){
        this.myinterface=myinterface;
    }
    private Myinterface myinterface;
    public interface Myinterface{
        void onFreshing();//上拉更多
        void loadMore();//下拉刷新
    }
}
