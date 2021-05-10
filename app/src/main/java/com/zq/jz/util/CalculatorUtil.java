package com.zq.jz.util;

import android.text.TextUtils;

public class CalculatorUtil {

    public static double cal(String result) {
        int add = result.indexOf("+");
        if(add!=-1){
            return cal(result.substring(0,add))+cal(result.substring(add+1));
        }

        int sub = result.indexOf("-");
        if(sub!=-1){
            return cal(result.substring(0,sub))-cal(result.substring(sub+1));
        }

        if(TextUtils.isEmpty(result)){
            return 0;
        }else {
            return Double.parseDouble(result);
        }
    }
}
