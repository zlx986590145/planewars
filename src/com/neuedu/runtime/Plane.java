package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends BaseSprite implements Drawable, Moveable {

    private Image image;

    public int hp = 5;

    private boolean fire;
    private boolean fire2;


    public int speed = FrameConstant.GAME_SPEED*4;

    private boolean up, right, down, left;

    public Plane() {
        this((FrameConstant.FRAME_WIDTH - ImageMap.get("my01").getWidth(null)) / 2,
                FrameConstant.FRAME_HEIGHT - ImageMap.get("my01").getHeight(null),
                ImageMap.get("my01"));
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    public Plane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(),
                image.getWidth(null)/2 ,
                image.getHeight(null)/2 ,
                null);
        move();
        fire();
        fire2();
        if (fire){
            index++;
            if (index>=5){
                index = 0;
            }
        }
        if (fire2){
            index++;
            if (index>=25){
                index = 0;
            }
        }
    }
    private int index ;
    /**
     * 开火方法
     * 判断开关是否打开
     * 创建一个子弹对象放入到gameFrame里的子弹集合中，
     */
    public  void  fire(){
        if (fire && index ==0) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bulletList.add(new Bullet(
                    getX() + (image.getWidth(null)/4) -ImageMap.get("myb_3").getWidth(null)/4,
                    getY() - image.getHeight(null)/4,
                    ImageMap.get("myb_3")
            ));

        }
    }
    public  void  fire2() {
        if (fire2 && index == 0) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bulletList2.add(new Bullet(
                    getX() + (image.getWidth(null) / 4) - ImageMap.get("bisha").getWidth(null) / 4,
                    getY() - image.getHeight(null) / 4,
                    ImageMap.get("bisha")
            ));

        }
    }
    @Override
    public void move() {

        if (up) {
            setY(getY() - speed);
        }
        if (right) {
            setX(getX() + speed);
        }
        if (down) {
            setY(getY() + speed);
        }
        if (left) {
            setX(getX() - speed );
        }
        borderTesting();
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            fire = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_K){
            fire2 = true;
        }
    }
    public void borderTesting(){
        if (getX()<0){
            setX(0);
        }
        if (getX() > FrameConstant.FRAME_WIDTH - image.getWidth(null)/2){
            setX(FrameConstant.FRAME_WIDTH -image.getWidth(null)/2);
        }
        if (getY()  < 30){
            setY(30);
        }
        if (getY()> FrameConstant.FRAME_HEIGHT - image.getHeight(null)/2){
            setY(FrameConstant.FRAME_HEIGHT - image.getHeight(null)/2  );
        }


    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            fire = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_K){
            fire2 = false;
        }

    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null)/2,image.getHeight(null)/2);
    }






    }




