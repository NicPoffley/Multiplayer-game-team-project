package org.nightshade.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BackgroundMusic {

    private MediaPlayer mediaPlayer;
    private int volumeDecrementTime = 30;
    private float volumeStepAmount = 100.0f;

    public BackgroundMusic() {
        File soundFile = new File("src/main/resources/audio/jump_01.mp3");
        Media media = new Media(soundFile.toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
    }

    public void startBackgroundMusic(File file, float volume) {
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(volume);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void fadeIntoNewTrack(File file, float currentVolume) throws InterruptedException {
        for (float i = currentVolume; i >= 0; i-=(currentVolume/volumeStepAmount)) {
            TimeUnit.MILLISECONDS.sleep(volumeDecrementTime);
            mediaPlayer.setVolume(i);
        }
        startBackgroundMusic(file, 0.0f);
        for (float i = 0; i <= currentVolume; i+=(currentVolume/volumeStepAmount)) {
            TimeUnit.MILLISECONDS.sleep(volumeDecrementTime);
            mediaPlayer.setVolume(i);
        }
        mediaPlayer.setVolume(currentVolume);
    }

    public void fadeOutOfTrack(float currentVolume) throws InterruptedException {
        for (float i = currentVolume; i >= 0; i-=(currentVolume/volumeStepAmount)) {
            TimeUnit.MILLISECONDS.sleep(volumeDecrementTime);
            mediaPlayer.setVolume(i);
        }
        mediaPlayer.stop();
    }

    public void stopMusic(){
        mediaPlayer.stop();
    }
}
