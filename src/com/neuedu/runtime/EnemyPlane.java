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

public class EnemyPlane extends BaseSprite implements Drawable , Moveable {

    private Image image ;
    private Image image2 ;
    private Image image3 ;

    private int speed = FrameConstant.GAME_SPEED * 2;

    private Random random = new Random();

    private int type;

    public  int enemyPlaneHP;

    public int getEnemyPlaneHP() {
        return enemyPlaneHP;
    }

    public void setEnemyPlaneHP(int enemyPlaneHP) {
        this.enemyPlaneHP = enemyPlaneHP;
    }

    public EnemyPlane() {
        this(0,0,1);
    }

    public int getType() {
        return type;
    }

    public EnemyPlane(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image = ImageMap.get("ep_1");
        this.image2 = ImageMap.get("ep_2");
        this.image3 = ImageMap.get("ep_3");
        if (type == 0){
            enemyPlaneHP = 1;
        }if (type == 1){
            enemyPlaneHP = 2;
        }if (type == 2){
            enemyPlaneHP = 3;
        }

    }



    @Override
    public void draw(Graphics g) {
        if (type == 0) {
            g.drawImage(image, getX(), getY(),
                    image.getWidth(null) / 2,
                    image.getHeight(null) / 2,
                    null);
        }else if(type == 1 ){
            g.drawImage(image2, getX(), getY(),
                    image.getWidth(null) / 2,
                    image.getHeight(null) / 2,
                    null);
        }
        else if(type == 2 ){
            g.drawImage(image3, getX(), getY(),
                    image.getWidth(null) / 2,
                    image.getHeight(null) / 2,
                    null);
        }
        move();
        fire();
        borderTesting();

    }


    public void fire(){
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (type == 2) {
            if (random.nextInt(1000) > 985 && getY() > 0) {
                gameFrame.enemyBulletList.add(new EnemyBullet(
                        getX() + (image.getWidth(null) / 4 - ImageMap.get("epb_1").getWidth(null) / 4),
                        getY() + image.getHeight(null) / 2,
                        ImageMap.get("epb_2")));
            }
        }else {
        if (random.nextInt(1000) > 985 && getY()>0){
            gameFrame.enemyBulletList.add(new EnemyBullet(
                    getX() + (image.getWidth(null)/4-ImageMap.get("epb_1").getWidth(null)/4),
                    getY()+image.getHeight(null)/2,
                    ImageMap.get("epb_1")));
        }
        }
    }

    private boolean right = true;
    @Override
    public void move() {
        if (type == 0){
            setY(getY() + speed/2);
        }
        if (type == 1 ) {
            if (right) {
                setX(getX() + speed);
                setY(getY() + speed/2);
            }else {
                setX(getX() - speed);
                setY(getY() + speed/2);
            }
        }
        if (type == 2 ) {
            if (right) {
                setX(getX() + speed*2);
                setY(getY() + speed);
            }else {
                setX(getX() - speed*2);
                setY(getY() + speed);
            }
        }
    }
    public void borderTesting(){
        if (type == 0){
            if (getY()> FrameConstant.FRAME_HEIGHT){
                GameFrame gameFrame = DateStore.get("gameFrame");
                gameFrame.enemyPlaneList.remove(this);
            }
        }
        if (type == 1){
            if (getX()> FrameConstant.FRAME_HEIGHT){
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
        if (type == 2){
            if (getX()> FrameConstant.FRAME_HEIGHT){
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

    }


    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null)/3,image.getHeight(null)/3);
    }
    public void collisionTesting(Plane plane){
        if (plane.getRectangle().intersects(this.getRectangle())) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.enemyPlaneList.remove(this);
            plane.hp--;
            if (plane.hp == 0) {
                gameFrame.gameOver = true;
            }
        }

    }
}
