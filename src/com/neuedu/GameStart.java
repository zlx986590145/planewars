package com.neuedu;

import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;

public class GameStart {

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        DateStore.put("gameFrame", gameFrame);
        gameFrame.init();
    }


}
