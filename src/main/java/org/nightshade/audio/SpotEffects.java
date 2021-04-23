package org.nightshade.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class SpotEffects {


    public void playSound(File file, boolean autoPlay) {
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(autoPlay);
    }

    public void playSoundUntilEnd(File file, boolean autoPlay) {
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
        });

        mediaPlayer.setAutoPlay(autoPlay);
    }
}
