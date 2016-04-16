package com.rottygames.gdf;

/**
 * Created by stephen on 08/01/16.
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.AudioManager;
import android.media.SoundPool;

import com.rottygames.framework.animation.Animation;
import com.rottygames.framework.animation.Frame;


public class Assets {
    private static SoundPool soundPool;
    public static Bitmap welcome, block, building1, cloud1, cloud2, coin, duck, grass, jump, jumpSecond, idle,
            run1, run2, run3, run4, run5, run6, run7, run8, run9, run10, scoreDown, score, startDown, start, missile, cat;
    public static Animation runAnim, missileAnim, catAnim;
    public static int hitID, onJumpID, onJumpID2, coinID;
    public static ArrayList<Frame> m, c;


    public static void load() {
        welcome = loadBitmap("startScreen.png", false);
        block = loadBitmap("block.png", false);
        building1 = loadBitmap("Building1.png", false);
        cloud1 = loadBitmap("cloud1.png", true);
        cloud2 = loadBitmap("cloud2.png", true);
        coin = loadBitmap("dev_coin.png", true);
        missile = loadBitmap("missile.png", true);
        cat = loadBitmap("cat_sprite.png", true);
        duck = loadBitmap("duck.png", true);
        grass = loadBitmap("grass.png", false);
        jump = loadBitmap("Oisin_run10_150.png", true);
        jumpSecond = loadBitmap("Oisin_run1_150.png", true);
        idle = loadBitmap("OisinIdle-v2-small.png", true);
        run1 = loadBitmap("Oisin_run1_150.png", true);
        run2 = loadBitmap("Oisin_run2_150.png", true);
        run3 = loadBitmap("Oisin_run3_150.png", true);
        run4 = loadBitmap("Oisin_run4_150.png", true);
        run5 = loadBitmap("Oisin_run5_150.png", true);
        run6 = loadBitmap("Oisin_run6_150.png", true);
        run7 = loadBitmap("Oisin_run7_150.png", true);
        run8 = loadBitmap("Oisin_run8_150.png", true);
        run9 = loadBitmap("Oisin_run9_150.png", true);
        run10 = loadBitmap("Oisin_run10_150.png", true);
        scoreDown = loadBitmap("score_button_down.png", true);
        score = loadBitmap("score_button.png", true);
        startDown = loadBitmap("start_button_down.png", true);
        start = loadBitmap("start_button.png", true);

        Frame f1 = new Frame(run1, .1f);
        Frame f2 = new Frame(run2, .1f);
        Frame f3 = new Frame(run3, .1f);
        Frame f4 = new Frame(run4, .1f);
        Frame f5 = new Frame(run5, .1f);
        Frame f6 = new Frame(run6, .1f);
        Frame f7 = new Frame(run7, .1f);
        Frame f8 = new Frame(run8, .1f);
        Frame f9 = new Frame(run9, .1f);
        Frame f10 = new Frame(run10, .1f);

        int missileFrames = 13;
        int catFrames = 12;

        m = new ArrayList<Frame>();
        c = new ArrayList<Frame>();

        for(int i = 0; i<missileFrames; i++) {
            Bitmap cropped = Bitmap.createBitmap(missile, 0, i*15, 45, 15);
            Frame tempFrame = new Frame(cropped, .1f);
            m.add(tempFrame);
        }
        for(int i = 0; i<catFrames; i++) {
            Bitmap cropped = Bitmap.createBitmap(cat, 0, i*200, 400, 200);
            Frame tempFrame = new Frame(cropped, .05f);
            c.add(tempFrame);
        }

        runAnim = new Animation(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10);
        missileAnim = new Animation(m.get(0),m.get(1),m.get(2),m.get(3),m.get(4),m.get(5),m.get(6),m.get(7),m.get(8),m.get(9),m.get(10),m.get(11),m.get(12));
        catAnim = new Animation(c.get(0),c.get(1),c.get(2),c.get(3),c.get(4),c.get(5),c.get(6),c.get(7),c.get(8),c.get(9),c.get(10),c.get(11));
        hitID = loadSound("hit.wav");
        onJumpID = loadSound("onjump.wav");
        onJumpID2 = loadSound("fart-06.wav");
        coinID = loadSound("coin2.wav");
    }

    private static Bitmap loadBitmap(String filename, boolean transparency) {
        InputStream inputStream = null;
        try {
            inputStream = GameMainActivity.assets.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Options options = new Options();
        if (transparency) {
            options.inPreferredConfig = Config.ARGB_8888;
        } else {
            options.inPreferredConfig = Config.RGB_565;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null,
                options);
        return bitmap;
    }

    private static int loadSound(String filename) {
        int soundID = 0;
        if (soundPool == null) {
            soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC, 0);
        }
        try {
            soundID = soundPool.load(GameMainActivity.assets.openFd(filename),
                    1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soundID;
    }

    public static void playSound(int soundID) {
        soundPool.play(soundID, 1, 1, 1, 0, 1);
    }
}