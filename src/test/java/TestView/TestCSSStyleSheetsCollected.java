package TestView;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

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
            Runnable stopper = basicUI.startCSSFX();

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
        @DisabledOnMac
        public void checkCSSFXCanChangeTheLabelFontColor(FxRobot robot) throws Exception {
            // The CSS used by the UI
            URI basicCSS = BasicUI.class.getResource("basic.css").toURI();
            String basicCSSUrl = basicCSS.toURL().toExternalForm();

            // Resources containing the color changes we want to apply to the CSS
            URI changedBasicCSSBlue = BasicUI.class.getResource("basic-cssfx-blue.css").toURI();
            URI changedBasicCSSRed = BasicUI.class.getResource("basic-cssfx-red.css").toURI();

            // The file we will tell CSSFX to map the CSS to
            Path mappedSourceFile = Files.createTempFile("tmp", ".css");
            mappedSourceFile.toFile().deleteOnExit();

            // Let's start with the normal content first
            Files.copy(Paths.get(changedBasicCSSBlue), mappedSourceFile, StandardCopyOption.REPLACE_EXISTING);

            // start CSSFX
            Runnable stopper = CSSFX.onlyFor(basicUI.getRootNode())
                    .noDefaultConverters()
                    .addConverter((uri) -> {
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