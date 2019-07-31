package com.neuedu.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg1",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg1.jpg"));
        map.put("bg2",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg2.jpg"));

        map.put("my01",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\my_1.png"));

        map.put("myb_1",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_1.png"));
        map.put("myb_3",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_3.png"));

        map.put("ep_1",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_1.png"));

        map.put("epb_1",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_1.png"));


    }

    public static Image get(String key){
        return map.get(key);
    }
}
