package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.ImageMap;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {

    private Random random = new Random();
    // 创建背景图
    private Background background = new Background();

    // 创建我方飞机
    private Plane plane = new Plane();

    //创建我方子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    // 创建敌方飞机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();
    private int count;

    // 创建敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    // 判断游戏是否结束
    public boolean gameOver = false;

    @Override
    public void paint(Graphics g) {
       if (!gameOver){
           background.draw(g);
           plane.draw(g);
           for (Bullet bullet : bulletList) {
               bullet.draw(g);
           }

           for (EnemyPlane enemyPlane : enemyPlaneList) {
               enemyPlane.draw(g);
           }

           for (EnemyBullet enemyBullet : enemyBulletList) {
               enemyBullet.draw(g);
           }
           for (Bullet bullet : bulletList) {
               bullet.collisionTesting(enemyPlaneList);
           }
           for (EnemyBullet enemyBullet : enemyBulletList) {
               enemyBullet.collisionTesting(plane);
           }
           for (EnemyPlane enemyPlane : enemyPlaneList) {
               enemyPlane.collisionTesting(plane);
           }
       }
//        g.setColor(Color.RED);
//        g.drawString(""+enemyBulletList.size(),110,110);
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

        // 添加键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
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

        // 在开始时添加敌方飞机

        addEmeyPlane();

//        enemyPlaneList.add(new EnemyPlane(300,100, ImageMap.get("ep_1")));
//        enemyPlaneList.add(new EnemyPlane(400,220, ImageMap.get("ep_1")));
//        enemyPlaneList.add(new EnemyPlane(450,0, ImageMap.get("ep_1")));
//        enemyPlaneList.add(new EnemyPlane(600,50, ImageMap.get("ep_1")));

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

    private void addEmeyPlane(){
        for (int i = 0; i < 100; i++) {
            enemyPlaneList.add(new EnemyPlane(random.nextInt(750), -random.nextInt(8000), ImageMap.get("ep_1")));
            }
    }
}
