package org.nightshade.audio;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.util.concurrent.TimeUnit;

@ExtendWith(ApplicationExtension.class)
public class BackgroundMusicTest {
    private BackgroundMusic backgroundMusic;

    @Start
    public void start(Stage stage) {
        backgroundMusic = new BackgroundMusic();
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testStartBackgroundMusic() throws InterruptedException {
        File soundFile = new File("src/test/resources/audio/testBackgroundMusic.mp3");
        backgroundMusic.startBackgroundMusic(soundFile, 1);
        TimeUnit.SECONDS.sleep(4);
        backgroundMusic.stopMusic();
    }

    @Test
    public void testFadeIntoNewTrack() throws InterruptedException {
        File soundFile = new File("src/test/resources/audio/testBackgroundMusic.mp3");
        backgroundMusic.startBackgroundMusic(soundFile, 1);
        TimeUnit.SECONDS.sleep(4);
        File newSoundFile = new File("src/test/resources/audio/testBackgroundMusic2.mp3");
        backgroundMusic.fadeIntoNewTrack(newSoundFile, 1);
        TimeUnit.SECONDS.sleep(4);
        backgroundMusic.stopMusic();
    }

    @Test
    public void testFadeIntoNewTrackDifferentVolume() throws InterruptedException {
        File soundFile = new File("src/test/resources/audio/testBackgroundMusic.mp3");
        backgroundMusic.startBackgroundMusic(soundFile, 0.5f);
        TimeUnit.SECONDS.sleep(4);
        File newSoundFile = new File("src/test/resources/audio/testBackgroundMusic2.mp3");
        backgroundMusic.fadeIntoNewTrack(newSoundFile, 0.5f);
        TimeUnit.SECONDS.sleep(4);
        backgroundMusic.stopMusic();
    }

    @Test
    public void testFadeOutOfTrack() throws InterruptedException {
        File soundFile = new File("src/test/resources/audio/testBackgroundMusic.mp3");
        backgroundMusic.startBackgroundMusic(soundFile, 1);
        TimeUnit.SECONDS.sleep(4);
        backgroundMusic.fadeOutOfTrack(1);
        backgroundMusic.stopMusic();
    }

}
