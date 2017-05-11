package com.example.mstarc.lovemoon.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.mstarc.lovemoon.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_cube;
    private TextView tv_flip;
    private TextView tv_alpha;
    private TextView tv_depth;
    private TextView tv_accordion;
    private TextView tv_fade;
    private TextView tv_default;
    private TextView tv_zoomFade;
    private TextView tv_zoom;
    private TextView tv_zoomStack;
    private TextView tv_zoomCenter;
    private TextView tv_stack;
    private static final String LEIXING = "leixing";



    @Override
    public int contentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        tv_cube = (TextView) findViewById(R.id.tv_cube);
        tv_flip = (TextView) findViewById(R.id.tv_flip);
        tv_alpha = (TextView) findViewById(R.id.tv_alpha);
        tv_depth = (TextView) findViewById(R.id.tv_depth);
        tv_default = (TextView) findViewById(R.id.tv_default);
        tv_accordion = (TextView) findViewById(R.id.tv_accordion);
        tv_fade = (TextView) findViewById(R.id.tv_fade);
        tv_zoomFade = (TextView) findViewById(R.id.tv_zoomFade);
        tv_zoomCenter = (TextView) findViewById(R.id.tv_zoomCenter);
        tv_zoom = (TextView) findViewById(R.id.tv_zoom);
        tv_stack = (TextView) findViewById(R.id.tv_stack);
        tv_zoomStack = (TextView) findViewById(R.id.tv_zoomStack);


    }

    @Override
    public void setListener() {
        tv_cube.setOnClickListener(this);
        tv_flip.setOnClickListener(this);
        tv_alpha.setOnClickListener(this);
        tv_accordion.setOnClickListener(this);
        tv_default.setOnClickListener(this);
        tv_depth.setOnClickListener(this);
        tv_fade.setOnClickListener(this);
        tv_zoomFade.setOnClickListener(this);
        tv_zoomCenter.setOnClickListener(this);
        tv_zoom.setOnClickListener(this);
        tv_stack.setOnClickListener(this);
        tv_zoomStack.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = new Intent(this, BannerActivity.class);
        if (id == R.id.tv_cube) {
            intent.putExtra(LEIXING, "cube");
        } else if (id == R.id.tv_alpha) {
            intent.putExtra(LEIXING, "alpha");
        } else if (id == R.id.tv_flip) {
            intent.putExtra(LEIXING, "flip");
        } else if (id == R.id.tv_fade) {
            intent.putExtra(LEIXING, "fade");
        } else if (id == R.id.tv_default) {
            intent.putExtra(LEIXING, "default");
        } else if (id == R.id.tv_depth) {
            intent.putExtra(LEIXING, "depth");
        } else if (id == R.id.tv_accordion) {
            intent.putExtra(LEIXING, "accordion");
        } else if (id == R.id.tv_zoomFade) {
            intent.putExtra(LEIXING, "zoomFade");
        }else if(id == R.id.tv_zoom){
            intent.putExtra(LEIXING, "zoom");
        }else if(id == R.id.tv_zoomStack){
            intent.putExtra(LEIXING, "zoomStack");
        }else if(id == R.id.tv_stack){
            intent.putExtra(LEIXING, "stack");
        }else if(id == R.id.tv_zoomCenter){
            intent.putExtra(LEIXING, "zoomCenter");
        }

        startActivity(intent);

    }
}
