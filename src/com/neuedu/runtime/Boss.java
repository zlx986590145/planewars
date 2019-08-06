package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.Random;

public class Boss extends BaseSprite implements Drawable, Moveable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED *3;

    public int Hp = 300;
    private Random random = new Random();

    public int getHp() {
        return Hp;
    }

    public Boss() {
        this(-220, 100, ImageMap.get("Boss"));
    }

    public Boss(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {

        move();
        fire();
        borderTesting();
        g.drawImage(image, getX(), getY(),
                image.getWidth(null) ,
                image.getHeight(null),
                null);
    }

    private boolean right = true;
    @Override
    public void move() {
        if (right) {
            setX(getX() + speed);
//            setY(getY() + speed/3);
        }else {
            setX(getX() - speed);
//            setY(getY() + speed/3);
        }

    }

    public void fire() {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (random.nextInt(1000) > 975 && getY()>0){
            gameFrame.enemyBulletList.add(new EnemyBullet(
                    getX() + (image.getWidth(null)/2-ImageMap.get("epb_2").getWidth(null)/4),
                    getY()+image.getHeight(null)/6*5,
                    ImageMap.get("epb_2")));
        }

    }

    public void borderTesting(){
        if (getX()> FrameConstant.FRAME_HEIGHT-image.getWidth(null)/2){
            right = false;
        }
        if (getX() < 0){
            right = true;
        }
        if (getY()> FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.enemyPlaneList.remove(this);
        }
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null)/6*5,image.getHeight(null)/6*5);
    }
}
