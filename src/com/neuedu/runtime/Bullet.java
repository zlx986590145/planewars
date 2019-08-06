package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.List;

public class Bullet extends BaseSprite implements Drawable, Moveable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED * 7;

    public Bullet() {
        this(0, 0, ImageMap.get("myb_3"));
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(),
                image.getWidth(null) / 2,
                image.getHeight(null) / 2,
                null);
        move();
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() - speed);
    }

    public void borderTesting() {
        if (getY() < 30) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
            gameFrame.bulletList2.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null) / 2, image.getHeight(null) / 2);
    }

    public void collisionTesting(List<EnemyPlane> enemyPlaneList) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
//                enemyPlane.enemyPlaneHP--;
//                enemyPlaneList.remove(enemyPlane);

                if (enemyPlane.getType()==0) {
                    enemyPlane.enemyPlaneHP--;
                    if (enemyPlane.enemyPlaneHP == 0) {
                        enemyPlaneList.remove(enemyPlane);
                    }
                }
                 if (enemyPlane.getType()==1) {
                     enemyPlane.enemyPlaneHP--;
                     if (enemyPlane.enemyPlaneHP == 0) {
                         enemyPlaneList.remove(enemyPlane);
                     }
                 }
                if (enemyPlane.getType()==2) {
                    enemyPlane.enemyPlaneHP--;
                    if (enemyPlane.enemyPlaneHP == 0) {
                        enemyPlaneList.remove(enemyPlane);
                    }
                }
                gameFrame.score += (enemyPlane.getType() + 1) * 5;
                gameFrame.bulletList.remove(this);
//                gameFrame.bulletList2.remove(this);

            }
        }

    }
    public void collisionTesting2(List<EnemyBullet> enemyBulletList){
        for (EnemyBullet enemyBullet : enemyBulletList) {
            if (enemyBullet.getRectangle().intersects(this.getRectangle())){
                GameFrame gameFrame = DateStore.get("gameFrame");
                gameFrame.enemyBulletList.remove(enemyBullet);
            }
        }
    }

    public void collisionTesting(Boss boss){
        if (boss.getRectangle().intersects(this.getRectangle())){
            GameFrame gameFrame = DateStore.get("gameFrame");
            boss.Hp--;
            gameFrame.bulletList.remove(this);
            if (boss.Hp == 0) {

            }
            gameFrame.score +=5;
        }

    }
    public void collisionTesting(Boss2 boss2){
        if (boss2.getRectangle().intersects(this.getRectangle())){
            GameFrame gameFrame = DateStore.get("gameFrame");
            boss2.Hp--;
            gameFrame.bulletList.remove(this);
            if (boss2.Hp == 0) {
                gameFrame.gameOver = true;
            }
            gameFrame.score +=10;
        }

    }
}
