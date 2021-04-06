package com.cst2335.teamproject;

import android.content.Context;
import android.widget.Toast;

public class Massage {
    public static void message(Context context, String msg){
        Toast.makeText(context, msg,Toast.LENGTH_SHORT).show();
    }
}
