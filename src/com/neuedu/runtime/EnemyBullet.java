package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;

import java.awt.*;

public class EnemyBullet extends BaseSprite implements Drawable, Moveable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED *4;

    public EnemyBullet() {
    }

    public EnemyBullet(int x, int y, Image image) {
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
        borderTesting();

    }

    @Override
    public void move() {
        setY(getY() + speed);

    }
    public void borderTesting(){
        if (getY()> FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.enemyBulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null)/2,image.getHeight(null)/2);
    }

    public void collisionTesting(Plane plane){
        if (plane.getRectangle().intersects(this.getRectangle())){
            GameFrame gameFrame = DateStore.get("gameFrame");
            plane.hp--;
            gameFrame.enemyBulletList.remove(this);
            if (plane.hp == 0) {
                gameFrame.gameOver = true;
            }
        }

    }




}
