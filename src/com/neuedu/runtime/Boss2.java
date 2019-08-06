package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss2 extends BaseSprite implements Drawable, Moveable {

    private List<Image> imageList = new ArrayList<>();

    private int speed = FrameConstant.GAME_SPEED*2;

    public int Hp = 300;

    public Boss2() {
        imageList.add(ImageMap.get("Boss2_01"));
        imageList.add(ImageMap.get("Boss2_02"));
        imageList.add(ImageMap.get("Boss2_03"));
        imageList.add(ImageMap.get("Boss2_04"));
        imageList.add(ImageMap.get("Boss2_05"));
        imageList.add(ImageMap.get("Boss2_06"));
        imageList.add(ImageMap.get("Boss2_07"));

    }

    public Boss2(int x, int y, List<Image> imageList) {
        super(x, y);

        this.imageList = imageList;
    }

    private  int index = 0;


    @Override
    public void draw(Graphics g) {
        g.drawImage(imageList.get(index++/5),getX(),getY(),
                imageList.get(0).getWidth(null),
                imageList.get(0).getHeight(null),
                null);
        if (index >=35){
            index = 0;
        }
        move();
        fire();
        borderTesting();
    }
    private boolean right = true;
    private boolean down = true;
    @Override
    public void move() {
        if (down) {
            if (right){
                setX(getX() + speed*3);
                setY(getY() + speed);
            }else {
                setX(getX() - speed*2);
                setY(getY() + speed);
            }
        }else {
            if (right){
                setX(getX() + speed*3);
                setY(getY() - speed);
            }else {
                setX(getX() - speed*2);
                setY(getY() - speed);
            }
        }


    }
    public void borderTesting(){
        if (getX()+imageList.get(1).getWidth(null)>=FrameConstant.FRAME_WIDTH){
            right = false;
        }
       if (getX() < 0){
            right = true;
        }
        if (getY()+imageList.get(1).getHeight(null)*2>= FrameConstant.FRAME_HEIGHT){
            down = false;
        }
        if (getY() <0){
            down =true;
        }
    }

    public void fire() {
        Random random = new Random();
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (random.nextInt(1000) > 975 && getY()>0){
            gameFrame.enemyBulletList.add(new EnemyBullet(
                    getX() + (imageList.get(1).getWidth(null)/2-ImageMap.get("epb_2").getWidth(null)/4),
                    getY()+imageList.get(1).getHeight(null)/6*5,
                    ImageMap.get("epb_2")));
        }

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),
                imageList.get(1).getWidth(null),
                imageList.get(1).getHeight(null));
    }

    public void collisionTesting(Plane plane){
        if (plane.getRectangle().intersects(this.getRectangle())){
            GameFrame gameFrame = DateStore.get("gameFrame");
            plane.hp--;
            if (plane.hp == 0) {
                gameFrame.gameOver = true;
            }
        }

    }



}



