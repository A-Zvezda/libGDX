package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.math.Rect;
import ru.geekbrains.base.Sprite;

public class Logo extends Sprite {

    private final Vector2 v0 = new Vector2(0.005f, 0.0f);
    private Vector2 v1 = new Vector2();

    private final Vector2 velocity = new Vector2();
    private Vector2 destPoint = new  Vector2();
    private boolean pressedLeft;
    private boolean pressedRight;
    private boolean pressedUp;
    private boolean pressedDown;

    private final float V_LEN = 0.005f;


    public Logo(TextureRegion region) {
        super(region);

    }
    @Override
    public void update(float deltaTime) {
        if (destPoint.sub(pos).len() > V_LEN ) {
            pos.add(velocity);
        } else if  (pressedLeft || pressedRight || pressedUp ||pressedDown ) {
            pos.add(velocity);
        } else {
            pos.set(destPoint);
            stop();
        }

    }


    public void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
            case Input.Keys.W:
                pressedUp = true;
                moveUp();
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                pressedDown = true;
                moveDown();
                break;
        }
    }
    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);

    }
    public void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
            case Input.Keys.UP:
            case Input.Keys.W:
                pressedUp = false;
                if (pressedUp) {
                    moveUp();
                } else {
                    stop();
                }
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                pressedDown = false;
                if (pressedDown) {
                    moveDown();
                } else {
                    stop();
                }
                break;
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        velocity.set (touch.cpy().sub(pos).setLength(V_LEN));
        destPoint = touch.cpy();
        System.out.println(destPoint);
        System.out.println(velocity);
        return false;
    }
    private void moveRight() {
        velocity.set(v0);
    }

    private void moveLeft() {
        velocity.set(v0).rotate(180);
    }
    private void moveUp() {
        velocity.set(v0).rotate(90);
    }
    private void moveDown() {
        velocity.set(v0).rotate(270);
    }
    private void stop() {
        velocity.setZero();
    }

}
