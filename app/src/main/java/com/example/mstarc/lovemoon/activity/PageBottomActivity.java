package com.example.mstarc.lovemoon.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.mstarc.lovemoon.fragment.PageBottomFragment;
import com.example.mstarc.lovemoon.swipebacklayout.swipbacklayout.app.SwipeBackActivity;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabItemBuilder;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

public class PageBottomActivity extends SwipeBackActivity {

    Controller controller;
    List<Fragment> mFragments;

//      int[] testColors = {0xFF7BA3A8,0xFFF4F3DE,0xFFBEAD92,0xFFF35A4A,0xFF5B4947};
//    int[] testColors = {0xFF00796B,0xFF8D6E63,0xFF2196F3,0xFF607D8B,0xFFF57C00};
    int[] testColors = {0xFF00796B,0xFF5B4947,0xFF607D8B,0xFFF57C00,0xFFF57C00};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_bottom);

        initFragment();
        
        initBottomTab();
    }
    private void initFragment() {
        mFragments = new ArrayList<>();

        mFragments.add(createFragment("信息"));
        mFragments.add(createFragment("位置"));
        mFragments.add(createFragment("搜索"));
        mFragments.add(createFragment("帮助"));
        mFragments.add(createFragment("添加"));

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,mFragments.get(0)).commit();
    }


  private void initBottomTab(){
      PagerBottomTabLayout mPagerBottomTabLayout = (me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout) findViewById(R.id.tab);

      //用TabItemBuilder构建一个导航按钮
      TabItemBuilder tabItemBuilder = new TabItemBuilder(this).create()
              .setDefaultIcon(android.R.drawable.ic_menu_send)
              .setText("信息")
              .setSelectedColor(testColors[0])
              .setTag("A")
              .build();

      //构建导航栏,得到Controller进行后续控制
      controller = mPagerBottomTabLayout.builder()
              .addTabItem(tabItemBuilder)
              .addTabItem(android.R.drawable.ic_menu_compass, "位置",testColors[1])
              .addTabItem(android.R.drawable.ic_menu_search, "搜索",testColors[2])
              .addTabItem(android.R.drawable.ic_menu_help, "帮助",testColors[3])
              .addTabItem(android.R.drawable.ic_menu_add,"删除",testColors[4])
//                .setMode(TabLayoutMode.HIDE_TEXT)
              .setMode(TabLayoutMode.CHANGE_BACKGROUND_COLOR)
//                .setMode(TabLayoutMode.HIDE_TEXT| TabLayoutMode.CHANGE_BACKGROUND_COLOR)
              .build();
//      controller.setMessageNumber("A",2);
//      controller.setDisplayOval(0,true);

      controller.addTabItemClickListener(listener);

  }
    OnTabItemSelectListener listener = new OnTabItemSelectListener() {
        @Override
        public void onSelected(int index, Object tag) {
            Log.i("asd","onSelected:"+index+"   TAG: "+tag.toString());
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,mFragments.get(index)).commit();
        }

        @Override
        public void onRepeatClick(int index, Object tag) {
            Log.i("asd","onSelected:"+index+"   TAG: "+tag.toString());

        }
    };

    private Fragment createFragment(String content){
        PageBottomFragment fragment = new PageBottomFragment();
        Bundle bundle = new Bundle();
        bundle.putString("content",content);
        fragment.setArguments(bundle);

        return fragment;
    }
}
