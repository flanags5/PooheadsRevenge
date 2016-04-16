package com.rottygames.game.model;

/**
 * Created by stephen on 08/01/16.
 */
import android.graphics.Rect;
import com.rottygames.framework.util.RandomNumberGenerator;
import com.rottygames.gdf.Assets;

public class Coin {
    private float x, y;
    private int width, height;
    private Rect rect;
    private boolean visible;
    public Coin(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect = new Rect((int) x, (int) y, (int) x + width, (int) y + height);
        visible = false;
    }

    public void update(float delta, float velX) {
        x += velX * delta;
        updateRect();
        if (x <= -50) {
            reset();
        }
    }

    public void updateRect() {
        rect.set((int) x, (int) y, (int) x + width, (int) y + height);
    }

    public void reset() {
        visible = true;
        y = RandomNumberGenerator.getRandInt(400);

        x += 1000 + RandomNumberGenerator.getRandInt(500);
        updateRect();
    }

    public void onCollide(Player p) {
        visible = false;
        p.setScore(1);
        Assets.playSound(Assets.coinID);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public Rect getRect() {
        return rect;
    }
}
