package com.company;


import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SoundTools {
    private static Clip bgm;
    private static Clip win;
    private static Clip lose;
    private static Clip current;

    private static AudioInputStream ais;

    public static void playBgm(){
        stop();
        try {
            bgm= AudioSystem.getClip();
            InputStream is=SoundTools.class.getClassLoader().getResourceAsStream("game.wav");
            BufferedInputStream bufferedInputStream=new BufferedInputStream(is);
            ais=AudioSystem.getAudioInputStream(bufferedInputStream);
            bgm.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
        current=bgm;
        current.start();
        current.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void playWin(){
        stop();
        try {
            win= AudioSystem.getClip();
            InputStream is=SoundTools.class.getClassLoader().getResourceAsStream("win.wav");
            BufferedInputStream bufferedInputStream=new BufferedInputStream(is);
            ais=AudioSystem.getAudioInputStream(bufferedInputStream);
            win.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
        current=win;
        current.start();
    }
    public static void playLose(){
        stop();
        try {
            lose= AudioSystem.getClip();
            InputStream is=SoundTools.class.getClassLoader().getResourceAsStream("lose.wav");
            BufferedInputStream bufferedInputStream=new BufferedInputStream(is);
            ais=AudioSystem.getAudioInputStream(bufferedInputStream);
            lose.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
        current=lose;
        current.start();
    }
    public static void stop()
    {
        if(ais!=null)
            current.close();
    }
}
