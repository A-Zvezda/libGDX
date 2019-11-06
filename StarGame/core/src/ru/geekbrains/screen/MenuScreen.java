package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture texture;
    private Texture starShip;
    private Vector2 currentPos;
    private Vector2 velocity;
    private Vector2 destinationPoint;

    @Override
    public void show() {
        super.show();
        texture = new Texture("space.jpg");
        starShip = new Texture( "whiteStar128x64.png");
        //starShip.w
        currentPos = new Vector2();
        velocity = new Vector2(0,0);
        destinationPoint = new Vector2(0,0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.draw(starShip, currentPos.x, currentPos.y, 128, 64);
        batch.end();
        if (Gdx.graphics.getHeight() >= currentPos.y + starShip.getHeight()
            && Gdx.graphics.getWidth() >= currentPos.x + starShip.getWidth()
                && currentPos.x >= 0 && currentPos.y >= 0) {
            if (velocity.x != 0 ||  velocity.y != 0) {
                currentPos.add(velocity);

            if (currentPos.x == destinationPoint.x  ) {
                velocity.x = 0;
            }
            if (currentPos.y == destinationPoint.y) {
                velocity.y = 0;
            }

                if (currentPos.x == destinationPoint.x && currentPos.y == destinationPoint.y ) {
                    velocity.set (0, 0);
                }
            if (Gdx.graphics.getHeight() <= currentPos.y + starShip.getHeight()) {
                currentPos.y = Gdx.graphics.getHeight()  - starShip.getHeight();
                velocity.y = 0;
            }
            if (Gdx.graphics.getWidth() <= currentPos.x + starShip.getWidth()) {
                currentPos.x =  Gdx.graphics.getWidth() - starShip.getWidth();
                velocity.x = 0;
            }
            if (currentPos.x < 0 ) {
                currentPos.x = 0;
                velocity.x = 0;
            }
            if (currentPos.y < 0 ) {
                currentPos.y = 0;
                velocity.y = 0;
            }
            }

        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        System.out.println(screenX + "; " + (Gdx.graphics.getHeight() - screenY));
        destinationPoint.set(Math.round(screenX) - starShip.getWidth()/2, Math.round(Gdx.graphics.getHeight() - screenY) - starShip.getHeight()/2);
        velocity = currentPos.cpy();
        velocity.sub(destinationPoint);
        velocity.scl(-1);
        velocity.nor();
        if (velocity.x < 0 ) {
            velocity.x = -1;
        } else if (velocity.x > 0 ) {
            velocity.x = 1;
        } else {
            velocity.x = 0;
        }
        if (velocity.y < 0 ) {
            velocity.y = -1;
        } else if (velocity.y > 0 ) {
            velocity.y = 1;
        } else {
            velocity.y = 0;
        }
       // velocity.y = Math.round(velocity.y);
        System.out.println( "velocity " + velocity.x + "; " + velocity.y);
        System.out.println( "CP " + currentPos.x + "; " + currentPos.y);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);
        if (keycode == 19) {
            velocity.set(0,1) ;
        }
        if (keycode == 20) {
            velocity.set(0,-1) ;
        }
        if (keycode == 21) {
            velocity.set(-1,0) ;
        }
        if (keycode == 22) {
            velocity.set(1,0) ;
        }

        return false;

    }

    @Override
    public boolean keyUp(int keycode) {
        super.keyUp(keycode);
        if (keycode == 19) {
            velocity.set(0,0) ;
        }
        if (keycode == 20) {
            velocity.set(0,0) ;
        }
        if (keycode == 21) {
            velocity.set(0,0) ;
        }
        if (keycode == 22) {
            velocity.set(0,0) ;
        }
        return false;
    }
}
