package TestView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.assertions.api.Assertions.assertThat;

public class TestLauncher extends FxRobot {
    static Button button = new Button();

    public static class DemoApplication extends Application {

        @Override
        public void start(Stage stage) {
            assertNotNull(stage);
            button = new Button("click me!");
            button.setOnAction(actionEvent -> button.setText("clicked!"));
            stage.setScene(new Scene(new StackPane(button), 100, 100));
            stage.show();
            stage.setAlwaysOnTop(true);
        }
    }

    @BeforeEach
    void setup() throws Exception {
        ApplicationTest.launch(DemoApplication.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }

    //test toggle button by searching and matching CSS selectors
    @Test
    void should_contain_rootButton() {
        //test that root buttons that control parent frame exists
        assertThat(lookup(".button").queryButton()).hasText("Button");
        assertThat(button).hasText("Button");
    }
    //test toggle button for GrandSlam controller and fxml change
    @Test
    void should_click_on_rootButton() {
        clickOn(".button");
        assertThat(lookup(".button").queryButton()).hasText("Button");
        assertThat(button).hasText("Button");
    }

    @Test
    void should_click_on_button(FxRobot robot) {
        robot.clickOn(".button");
    }

    }
