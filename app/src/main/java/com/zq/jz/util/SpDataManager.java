package com.zq.jz.util;


public class SpDataManager {
    public static void setHomeCardType(int type) {
        SPUtil.putData("home_card_type", type);
    }

    public static int getHomeCardType() {
        return (int) SPUtil.getData("home_card_type", 0);
    }
}
