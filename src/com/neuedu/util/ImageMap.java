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
        map.put("myb_4",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\myb_4.png"));
        map.put("bisha",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\bisha.png"));

        map.put("ep_1",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_1.png"));
        map.put("ep_2",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_2.png"));
        map.put("ep_3",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_3.png"));


        map.put("Boss",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\Boss.png"));

        map.put("Boss2_01",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss2_01.png"));
        map.put("Boss2_02",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss2_02.png"));
        map.put("Boss2_03",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss2_03.png"));
        map.put("Boss2_04",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss2_04.png"));
        map.put("Boss2_05",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss2_05.png"));
        map.put("Boss2_06",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss2_06.png"));
        map.put("Boss2_07",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss2_07.png"));

        map.put("epb_1",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_1.png"));
        map.put("epb_2",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\epb_2.png"));

        map.put("Hp",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\Hp.png"));
        map.put("Speed",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\Speed.png"));
        map.put("Shield",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\Shield.png"));


        map.put("GameOver",ImageUtil.getImage("com\\neuedu\\imgs\\gameOver\\GameOver.png"));


    }

    public static Image get(String key){
        return map.get(key);
    }
}
