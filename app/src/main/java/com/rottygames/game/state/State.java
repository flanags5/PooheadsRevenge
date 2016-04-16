package com.rottygames.game.state;

/**
 * Created by stephen on 08/01/16.
 */
import android.graphics.Canvas;
import android.view.MotionEvent;
import com.rottygames.framework.util.Painter;
import com.rottygames.gdf.GameMainActivity;

public abstract class State {
    public void setCurrentState(State newState) {
        GameMainActivity.sGame.setCurrentState(newState);
    }

    public abstract void init();

    public abstract void update(float delta);

    public abstract void render(Painter g);

    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);
}
