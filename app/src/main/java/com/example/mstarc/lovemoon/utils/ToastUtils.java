package com.example.mstarc.lovemoon.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Mstarc on 2016-12-22.
 */

public class ToastUtils{

    public static void showInfo(Context context,String info){
        Toast.makeText(context,info,Toast.LENGTH_SHORT).show();

    }
}
