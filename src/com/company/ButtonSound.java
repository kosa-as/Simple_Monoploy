package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class ButtonSound {
    private static Clip button;
    private static Clip current;
    private static AudioInputStream ais;

    public static void playButton(){
        try {
            button= AudioSystem.getClip();
            InputStream is= ButtonSound.class.getClassLoader().getResourceAsStream("button.wav");
            BufferedInputStream bufferedInputStream=new BufferedInputStream(is);
            ais=AudioSystem.getAudioInputStream(bufferedInputStream);
            button.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
        current=button;
        current.start();
    }

    public static void stop()
    {
        if(ais!=null)
            current.close();
    }
}
