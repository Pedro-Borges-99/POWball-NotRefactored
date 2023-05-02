package org.academiadecodigo.bootcamp;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;

import static jdk.jfr.internal.JVM.flush;

public class MusicGen {


    static Clip backgroundMusic;
    static Clip oneballMusic;
    static Clip multipleBallMusic;
    private boolean playing= false;
    static boolean playBackGround;


    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }


    public static void muteBackGround() throws IOException {
        backgroundMusic.stop();
        playBackGround = false;
        AnimationGen.soundButton.load("buttonIsMuted.png");
        AnimationGen.soundButton.draw();

    }

    public static void playBackGround() throws IOException {
        playBackGround = true;

        try {
            InputStream is = MusicGen.class.getClassLoader().getResourceAsStream("shortMusic.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));

            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(ais);
            backgroundMusic.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        AnimationGen.soundButton.load("audiobutton.png");
        AnimationGen.soundButton.draw();
    }


    public static void ballHit() {
        try {
            InputStream is = MusicGen.class.getClassLoader().getResourceAsStream("ballhit.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            oneballMusic = AudioSystem.getClip();
            oneballMusic.open(ais);
            oneballMusic.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void multipleBallHit() {
        try {
            InputStream is = MusicGen.class.getClassLoader().getResourceAsStream("multipleballhit.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            multipleBallMusic = AudioSystem.getClip();
            multipleBallMusic.open(ais);
            multipleBallMusic.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isPlayBackGround() {
        return playBackGround;
    }

    public static void setPlayBackGround(boolean playBackGround) {
        MusicGen.playBackGround = playBackGround;
    }


}


