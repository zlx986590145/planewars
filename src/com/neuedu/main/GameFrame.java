package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.Background;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {

    private Background background = new Background();

    @Override
    public void paint(Graphics g) {
        background.draw(g);
    }

    /**
     * 使用这个方法初始化窗口
     */
    public void  init(){
        //设置尺寸
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);
        //设置居中
        setLocationRelativeTo(null);

        enableInputMethods(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        new Thread(){
            @Override
            public void run() {
                while (true){
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();



        setVisible(true);
    }

    private Image offScreenImage = null;//创建缓冲区

    @Override
    public void update(Graphics g){
        if (offScreenImage ==null){
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);

        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }

}
