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

    // 创建敌方Boss
    private Boss boss = new Boss();
    private Boss2 boss2 = new Boss2();
    //创建我方子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();
    public final List<Bullet> bulletList2 = new CopyOnWriteArrayList<>();

    // 创建敌方飞机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();
    private int count;

    // 创建敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    // 创建道具集合
    public final List<Prop> propList = new CopyOnWriteArrayList<>();

    //计分
    public int score = 0;

    // 判断游戏是否结束
    public boolean gameOver = false;
    public boolean suspend = false;

    // 获取道具类型
    public boolean stop = false;

    public int x = 500;

    @Override
    public void paint(Graphics g) {
        if (!gameOver) {
                background.draw(g);
                plane.draw(g);

                if (score >= 500 && boss.Hp >= 0) {
                    boss.draw(g);
                    for (Bullet bullet : bulletList2) {
                        bullet.collisionTesting(boss);
                    }

                    for (Bullet bullet : bulletList) {
                        bullet.collisionTesting(boss);
                    }
                }
//           boss.draw(g);

                // 画 我方子弹
                for (Bullet bullet : bulletList) {
                    bullet.draw(g);
                }
                // 我方第二种子弹
                for (Bullet bullet : bulletList2) {
                    bullet.draw(g);
                }
                // 敌方飞机
                for (EnemyPlane enemyPlane : enemyPlaneList) {
                    enemyPlane.draw(g);
                }
                // 敌方子弹
                for (EnemyBullet enemyBullet : enemyBulletList) {
                    enemyBullet.draw(g);
                }
                // 画 道具
                for (Prop prop : propList) {
                    prop.draw(g);
                }

                // 碰撞判断
                for (Bullet bullet : bulletList) {
                    bullet.collisionTesting(enemyPlaneList);
                }
                for (Bullet bullet : bulletList2) {
                    bullet.collisionTesting(enemyPlaneList);
                }
                for (Bullet bullet : bulletList2) {
                    bullet.collisionTesting2(enemyBulletList);
                }

//           for (Bullet bullet : bulletList2) {
//               bullet.collisionTesting(boss);
//           }

//
//           for (Bullet bullet : bulletList) {
//               bullet.collisionTesting(boss);
//           }

            }

            for (Prop prop : propList) {
                prop.collisionTesting(plane);
            }

            if (!stop) {
                for (EnemyBullet enemyBullet : enemyBulletList) {
                    enemyBullet.collisionTesting(plane);
                }
                for (EnemyPlane enemyPlane : enemyPlaneList) {
                    enemyPlane.collisionTesting(plane);
                }
            }
            x--;
            if (x < 0) {
                x = 500;
                stop = false;
            }

//        for (Prop prop : propList) {
//            prop.collisionTesting(plane);
//        }
//        for (EnemyBullet enemyBullet : enemyBulletList) {
//            enemyBullet.collisionTesting(plane);
//        }
//        for (EnemyPlane enemyPlane : enemyPlaneList) {
//            enemyPlane.collisionTesting(plane);
//        }
            if (boss.Hp <= 0 && plane.hp >= 0) {
                boss2.collisionTesting(plane);
                if (boss2.Hp > 0) boss2.draw(g);
//            for (Bullet bullet : bulletList) {
//                bullet.collisionTesting(boss);
//            }
                for (Bullet bullet : bulletList) {
                    bullet.collisionTesting(boss2);
                }
                for (Bullet bullet : bulletList) {
                    bullet.collisionTesting(boss2);
                }
            }

            g.setFont(new Font("楷体", 0, 20));
            g.setColor(Color.WHITE);
            g.drawString("得分：" + score, 70, 130);
            g.drawString("生命值：" + plane.hp, 70, 70);
            g.drawString("Boss生命值：" + boss.Hp, 600, 50);
            g.drawString("Boss2生命值：" + boss2.Hp, 600, 80);
            g.drawString("当前剩余道具数：" + propList.size(), 70, 100);
            g.drawString("无敌时间：", 100, 600);
//        g.drawString("当前剩余必杀数：：" + time, 100, 150);
//
            if (!stop) {
                g.setColor(Color.red);
                g.drawString("0", 210, 600);
            }
            if (stop) {
                g.setColor(Color.red);
                g.drawString("" + x/30, 210, 600);
            }
            /**
             *
             */
            if (suspend) {
                g.drawImage(ImageMap.get("GameOver"), 120, 280, null);
            }
            if (gameOver) {
                g.drawImage(ImageMap.get("GameOver"), 120, 280, null);
            }


//        g.setColor(Color.RED);
//        g.drawString(""+enemyBulletList.size(),110,110);
        }


    /**
     * 使用这个方法初始化窗口
     */
    public void init() {
        //设置尺寸
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
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

        new Thread() {
            @Override
            public void run() {
                while (true) {
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
//        addBoss();
        addProp();
//        enemyPlaneList.add(new EnemyPlane(random.nextInt(750), -random.nextInt(10),2));
//        enemyPlaneList.add(new EnemyPlane(300,100, ImageMap.get("ep_1")));
//        enemyPlaneList.add(new EnemyPlane(400,220, ImageMap.get("ep_1")));
//        enemyPlaneList.add(new EnemyPlane(450,0, ImageMap.get("ep_1")));
//        enemyPlaneList.add(new EnemyPlane(600,50, ImageMap.get("ep_1")));

        setVisible(true);

    }

    private Image offScreenImage = null;//创建缓冲区

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private void addEmeyPlane() {
        for (int i = 0; i < 100; i++) {
            enemyPlaneList.add(new EnemyPlane(random.nextInt(750), -random.nextInt(8000), random.nextInt(2)));
            enemyPlaneList.add(new EnemyPlane(random.nextInt(750), -random.nextInt(8000) - 2000, random.nextInt(3)));
        }
    }
//    private void addBoss(){
//        bossList.add(new Boss(100,-500,ImageMap.get("Boss")));
//    }

    private void addProp() {
        for (int i = 0; i < 30; i++) {
            propList.add(new Prop(random.nextInt(750), -random.nextInt(9000), random.nextInt(3)));
//            propList.add(new Prop(random.nextInt(750), -random.nextInt(800), 2));
//            propList.add(new Prop(random.nextInt(750), -random.nextInt(8000), 0));
        }
    }

}
