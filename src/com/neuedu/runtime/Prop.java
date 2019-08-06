package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class Prop extends BaseSprite implements Drawable, Moveable {

    private Image image;
    private Image image2;
    private Image image3;

    private int type;

    private int speed = FrameConstant.GAME_SPEED * 2;


    public int getType() {
        return type;
    }

    public Prop() {
    }

    public Prop(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image = ImageMap.get("Hp");
        this.image2 = ImageMap.get("Speed");
        this.image3 = ImageMap.get("Shield");
    }


    @Override
    public void draw(Graphics g) {
        if (type == 0) {
            move();
            borderTesting();
            g.drawImage(image, getX(), getY(),
                    image.getWidth(null) / 2,
                    image.getHeight(null) / 2,
                    null);
        }
        if (type == 1) {
            move();
            borderTesting();
            g.drawImage(image2, getX(), getY(),
                    image2.getWidth(null) / 2,
                    image2.getHeight(null) / 2,
                    null);
        }
        if (type == 2) {
            move();
            borderTesting();
            g.drawImage(image3, getX(), getY(),
                    image3.getWidth(null) / 2,
                    image3.getHeight(null) / 2,
                    null);
        }
    }

    private boolean right = false;

    @Override
    public void move() {
        if (type == 0) {
            if (right) {
                setX(getX() + speed * 3 / 2);
                setY(getY() + speed / 2);
            } else {
                setX(getX() - speed * 3 / 2);
                setY(getY() + speed / 2);
            }
        }
        if (type == 1) {
            if (right) {
                setX(getX() + speed / 2);
                setY(getY() + speed / 2);
            } else {
                setX(getX() - speed / 2);
                setY(getY() + speed / 2);
            }
        }
        if (type == 2) {
            if (right) {
                setX(getX() + speed );
                setY(getY() + speed / 2);
            } else {
                setX(getX() - speed );
                setY(getY() + speed / 2);
            }
        }
    }

    public void borderTesting() {
        if (getX() > FrameConstant.FRAME_HEIGHT) {
            right = false;
        }
        if (getX() < 0) {
            right = true;
        }
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.propList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null) / 3, image.getHeight(null) / 3);
    }

    public void collisionTesting(Plane plane) {
        if (plane.getRectangle().intersects(this.getRectangle())) {
            GameFrame gameFrame = DateStore.get("gameFrame");

            if (type == 0) {
                plane.hp++;
                gameFrame.propList.remove(this);
            }
            if (type == 1) {
                plane.speed = plane.speed + 1;
                gameFrame.propList.remove(this);
            }
            if (type == 2) {
                gameFrame.stop = true;
                gameFrame.x = 500;
                gameFrame.propList.remove(this);
            }


        }
    }


}
