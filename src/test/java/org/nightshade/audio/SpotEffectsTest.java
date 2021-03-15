package org.nightshade.audio;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

@ExtendWith(ApplicationExtension.class)
public class SpotEffectsTest {

    private SpotEffects spotEffects;

    @Start
    public void start(Stage stage) {
        spotEffects = new SpotEffects();
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testPlaySound() {
        File soundFile = new File("src/test/resources/audio/jump_00.mp3");
        spotEffects.playSound(soundFile, true);
    }

    @Test
    public void testRandomPlaySound() {
        ArrayList<File> soundFiles = new ArrayList<>();
        soundFiles.add(new File("src/test/resources/audio/jump_01.mp3"));
        soundFiles.add(new File("src/test/resources/audio/jump_02.mp3"));
        soundFiles.add(new File("src/test/resources/audio/jump_03.mp3"));
        soundFiles.add(new File("src/test/resources/audio/jump_04.mp3"));
        soundFiles.add(new File("src/test/resources/audio/jump_05.mp3"));

        Random random = new Random();
        spotEffects.playSound(soundFiles.get(random.nextInt(5)), true);
    }
}
