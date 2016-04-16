package com.rottygames.game.state;

/**
 * Created by stephen on 08/01/16.
 */
import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import com.rottygames.framework.util.Painter;
import com.rottygames.game.model.Cloud;
import com.rottygames.game.model.Player;
import com.rottygames.gdf.Assets;
import com.rottygames.gdf.GameMainActivity;

public class PlayState extends State {
    private Player player;
    private Cloud cloud, cloud2;
    private int playerScore = 0;
    private static final int PLAYER_WIDTH = 100;
    private static final int PLAYER_HEIGHT = 150;

    private float recentTouchY;
    private float recentTouchX;

    @Override
    public void init() {
        player = new Player("main",160, GameMainActivity.GAME_HEIGHT - 45
                - PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);

        cloud = new Cloud(100, 100);
        cloud2 = new Cloud(500, 50);
    }

    @Override
    public void update(float delta) {
        if (!player.isAlive()) {
            setCurrentState(new GameOverState(player.getScore() / 100));
        }


        cloud.update(delta);
        cloud2.update(delta);
        Assets.runAnim.update(delta);
        player.update(delta);
    }



    @Override
    public void render(Painter g) {
        //g.setColor(Color.rgb(208, 244, 247));
        g.setColor(Color.rgb(37, 81, 114));
        g.fillRect(0, 0, GameMainActivity.GAME_WIDTH,
                GameMainActivity.GAME_HEIGHT);
        renderClouds(g);
        renderPlayer(g);
        g.drawImage(Assets.grass, 0, 405);
        renderScore(g);
    }

    private void renderScore(Painter g) {
        g.setFont(Typeface.SANS_SERIF, 25);
        g.setColor(Color.GRAY);
        g.drawString("Money: $" + player.getScore(), 20, 30);
    }

    private void renderPlayer(Painter g) {
        if (player.isGrounded()) {
            if(player.isRunning()) {
                 Assets.runAnim.render(g, (int) player.getX(),
                        (int) player.getY(), player.getWidth(),
                        player.getHeight());
            }
             else {

                g.drawImage(Assets.idle, (int) player.getX(), (int) player.getY(),
                        player.getWidth(), player.getHeight());
            }
        } else {
            if(player.onFirstJump()) {
            g.drawImage(Assets.jump, (int) player.getX(), (int) player.getY(),
                    player.getWidth(), player.getHeight());
        } else {
                g.drawImage(Assets.jumpSecond, (int) player.getX(), (int) player.getY(),
                        player.getWidth(), player.getHeight());
            }


        }
    }



    private void renderClouds(Painter g) {
        g.drawImage(Assets.cloud1, (int) cloud.getX(), (int) cloud.getY(), 100,
                60);
        g.drawImage(Assets.cloud2, (int) cloud2.getX(), (int) cloud2.getY(),
                100, 60);
    }


    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            recentTouchY = scaledY;
            recentTouchX = scaledX;
            player.run(true);

        }
        else if (e.getAction() == MotionEvent.ACTION_UP) {
            player.run(false);
            if (scaledY - recentTouchY < -50) {
                player.jump();
            } else if (scaledY - recentTouchY > 50) {
                player.duck();
            }
        }
        return true;
    }
}
