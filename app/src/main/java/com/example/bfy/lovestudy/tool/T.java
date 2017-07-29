package com.example.bfy.lovestudy.tool;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wls on 2017/3/13 13:51.
 */

public class T {
    private static Context context = null;
    public T(Context context){
        this.context = context;
    }
    /**
     * toast
     * @param mess
     */
    public static void show(String mess){
        if(context != null)
            Toast.makeText(context,mess, Toast.LENGTH_SHORT).show();
    }
}
