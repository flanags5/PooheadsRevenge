package com.rottygames.game.state;

/**
 * Created by stephen on 08/01/16.
 */
import android.graphics.Canvas;
import android.view.MotionEvent;
import com.rottygames.framework.util.Painter;
import com.rottygames.gdf.Assets;

public class LoadState extends State {
    @Override
    public void init() {
        Assets.load();
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Painter g) {
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}