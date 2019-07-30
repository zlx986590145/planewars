package com.neuedu.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg1",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg1.jpg"));
        map.put("bg2",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg2.jpg"));

    }

    public static Image get(String key){
        return map.get(key);
    }
}
