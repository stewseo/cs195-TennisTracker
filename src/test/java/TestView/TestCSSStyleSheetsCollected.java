package TestView;

import fr.brouillard.oss.cssfx.CSSFX;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static sun.nio.cs.Surrogate.is;

@ExtendWith(ApplicationExtension.class)
public class TestCSSStyleSheetsCollected {

    private TestUi testui;

    @Start
    public void init(Stage stage) throws Exception {
        TestUi testui = new TestUi();
        testui.start(stage);
    }

    @Test
    public void canRetrieveExpectedNodes(FxRobot robot) throws Exception {
        Runnable stopper = TestUi.startCSSFX();
        try {
            assertThat(robot.lookup(".label").queryAll().size(), is(2));
        } finally {
            stopper.run();
        }
    }

    @Test
    public void checkCSSIsApplied(FxRobot robot) throws Exception {
        Runnable stopper = TestUi.startCSSFX();

        try {
            Thread.sleep(1000);
            Label cssfxLabel = robot.lookup("#cssfx").queryAs(Label.class);

            Paint textColor = cssfxLabel.getTextFill();
            assertThat("retrieved color is not one of expected", getExpectedTextColor(), hasItem(textColor));
            assertThat(textColor, is(Color.BLUE));
        } finally {
            stopper.run();
        }
    }

    @Test
    public void checkCSSFXCanChangeTheLabelFontColor(FxRobot robot) throws Exception {
        URI basicCSS = TestUi.class.getResource("basic.css").toURI();
        String basicCSSUrl = basicCSS.toURL().toExternalForm();

        URI changedBasicCSSBlue = TestUi.class.getResource("basic-cssfx-blue.css").toURI();
        URI changedBasicCSSRed = TestUi.class.getResource("basic-cssfx-red.css").toURI();

        // The file we will tell CSSFX to map the CSS to
        Path mappedSourceFile = Files.createTempFile("tmp", ".css");
        mappedSourceFile.toFile().deleteOnExit();

        Files.copy(Paths.get(changedBasicCSSBlue), mappedSourceFile, StandardCopyOption.REPLACE_EXISTING);

        Runnable stopper = CSSFX.onlyFor(TestUi.getRootNode())
                .noDefaultConverters()
                .addConverter((uri) -> { //converter that will be used to map CSS resources to local file
                    if (basicCSSUrl.equals(uri)) {
                        return mappedSourceFile;
                    }
                    return null;
                }).start();
        try {
            // We need to let CSSFX some time to detect the file change
            Thread.sleep(1000);

            // Let's check that initial launch has used the mapped
            Label cssfxLabel = robot.lookup("#cssfx").queryAs(Label.class);
            Paint textColor = cssfxLabel.getTextFill();
            assertThat("retrieved color is not one of expected", getExpectedTextColor(), hasItem(textColor));
            assertThat(textColor, is(Color.BLUE));

            // Copy the modified version in to the "source" file
            try {
                Files.copy(Paths.get(changedBasicCSSRed), mappedSourceFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // We TestMemoryLeaksneed to let CSSFX some time to detect the file change
            Thread.sleep(1000);

            textColor = cssfxLabel.getTextFill();
            assertThat("retrieved color is not one of expected", getExpectedTextColor(), hasItem(textColor));
            assertThat(textColor, is(Color.RED));
        } finally {
            stopper.run();
        }
    }

    private static Collection<Paint> getExpectedTextColor() {
        return Arrays.asList(Color.WHITE, Color.RED, Color.BLUE);
    }
}