package mysharesdkdemo.weshpe.com.zoomtestcard;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends Activity {

    private  ViewPager viewPager;
    private  Animation animation;
    private boolean isFull = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        animation  = AnimationUtils.loadAnimation(this,R.anim.goup);

        this.findViewById(R.id.bt_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFull = !isFull;
                if(isFull){

                    WindowManager.LayoutParams params = getWindow().getAttributes();
                    params.flags|=WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    getWindow().setAttributes(params);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

                } else {

                    WindowManager.LayoutParams params = getWindow().getAttributes();
                    params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    getWindow().setAttributes(params);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

                }
            }
        });
    }
    private void initView(){

        viewPager = (ViewPager) findViewById(R.id.vp);
        final ArrayList<String> list = new ArrayList();
        list.add("第一页");
        list.add("第二页");
        list.add("第三页");
        list.add("第四页");

        final ArrayList<View> list1 = new ArrayList<View>();
        for(int i=0;i<list.size();i++){
            String s = list.get(i);
            TextView textView = new TextView(MainActivity.this);

            textView.setText(s);
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300,300);

            textView.setLayoutParams(params);
            textView.setTextColor(Color.BLUE);
            LayoutInflater inflater = this.getLayoutInflater();
            final View view =inflater.inflate(R.layout.card_display,null);

            if(i==0){

                textView.setBackgroundColor(Color.RED);
                textView.setBackgroundResource(R.drawable.p1);
                ((ImageView)view.findViewById(R.id.iv)).setImageResource(R.drawable.p1);

            }else if(i==1){

                textView.setBackgroundColor(Color.GREEN);
                textView.setBackgroundResource(R.drawable.p2);
                ((ImageView)view.findViewById(R.id.iv)).setImageResource(R.drawable.p2);
            }else if(i==2){

                textView.setBackgroundColor(Color.BLUE);
                textView.setBackgroundResource(R.drawable.p3);
                ((ImageView)view.findViewById(R.id.iv)).setImageResource(R.drawable.p3);
            }else  if(i==3){

                textView.setBackgroundColor(Color.YELLOW);
                textView.setBackgroundResource(R.drawable.p4);
                ((ImageView)view.findViewById(R.id.iv)).setImageResource(R.drawable.p4);
            }

            view.setAnimation(translateAnimation);
            view.findViewById(R.id.tv_detial).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"点击了",Toast.LENGTH_SHORT).show();
                }
            });
            list1.add(view);
        }
        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public int getCount() {
                return list.size();
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {

                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView(list1.get(position));
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(list1.get(position));
                return list1.get(position);
            }
        };

        viewPager.setOffscreenPageLimit(4);
        viewPager.setPageMargin(40);
        viewPager.setAdapter(pagerAdapter);

    }
    TranslateAnimation translateAnimation = new TranslateAnimation(0,1,0,1, Animation.RELATIVE_TO_PARENT,0,Animation.RELATIVE_TO_PARENT,0);
    /**
     * boolean dispathTouchEvent(MotionEvent event){
     *     int action = event.getAction();
     * //判断viewgroup是否拦截touch事件。
     *     当为action down或者能够接收到子view时，由OnterceptTouchEvent（event）决定是否拦截，其他情况，即ACTION_MOVE/ACTIONUP且没有找到接受动作的子view
     *     的时候就直接拦截
     *     boolean intercepted；
     *     if(action == MotionEvent.Action_DOWN||mFirstTouchTarget!=null){
     *         intercepted = onTnterceptTouchEvent(event)
     *     }else{
     *         intercepted = true;
     *     }
     * //如果viewgroup不拦截touch事件。在ACTION_DOWN时遍历所有子view，查找能够接收到touch事件的view如果找到则设置mFirstTouchTarget并跳出循环
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
    /**
     * java的线程池有四种
     *
     * 第一种 newCachedThreadPool 创建一个可缓存线程池  如果线程池长度超过处理需求  可灵活回收空闲线程 若无可回收 则创建新的线程  new FixedThreadPool创建定长的线程池 如果超过了限定的个数
     * 就会去线程队列 等待
     */
}
