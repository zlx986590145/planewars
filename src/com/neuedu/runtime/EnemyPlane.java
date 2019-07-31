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

    private int speed = FrameConstant.GAME_SPEED * 2;

    private Random random = new Random();

    private boolean fire = false;

    public EnemyPlane() {
        this(0,0, ImageMap.get("ep_1"));
    }

    public EnemyPlane(int x, int y, Image image) {
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
        borderTesting();


    }


    public void fire(){
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (random.nextInt(1000) > 985 && fire){
            gameFrame.enemyBulletList.add(new EnemyBullet(
                    getX() + (image.getWidth(null)/4-ImageMap.get("epb_1").getWidth(null)/4),
                    getY()+image.getHeight(null)/2,
                    ImageMap.get("epb_1")));
        }
    }

    @Override
    public void move() {
        setY(getY() + speed);
        if (getY() > 0 ){
            fire = true;
        }

    }
    public void borderTesting(){
        if (getY()> FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.enemyPlaneList.remove(this);
        }
    }


    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null)/3,image.getHeight(null)/3);
    }
    public void collisionTesting(Plane plane){
        if (plane.getRectangle().intersects(this.getRectangle())){
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.gameOver = true;
        }

    }
}
