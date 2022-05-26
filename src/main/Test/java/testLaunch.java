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

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.util.DebugUtils.informedErrorMessage;
import static org.testfx.util.NodeQueryUtils.hasText;

public class testLaunch extends Application {
    static Button button;

    @Override
    public void start(Stage stage) {
        FxRobot fxRobot = new FxRobot();
        button = new Button("click me!");
        button.setOnAction(actionEvent -> button.setText("clicked!"));
        stage.setScene(new Scene(new StackPane(button), 100, 100));
        stage.show();
        stage.setAlwaysOnTop(true);
    }



    @BeforeEach
    void setup() throws Exception {
        ApplicationTest.launch(testLaunch.class);
    }

    @AfterEach
    void cleanup() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    void should_contain_button() {
        FxRobot fxRobot = new FxRobot();
        assertThat(fxRobot.lookup(".button").queryButton()).hasText("click me!");
        assertThat(button).hasText("click me!");
        verifyThat(".button",  hasText("click me!"), informedErrorMessage(fxRobot));
    }

    @Test
    void should_click_on_button() {
        FxRobot fxRobot = new FxRobot();
        fxRobot.clickOn(".button");

        // then:
        assertThat(fxRobot.lookup(".button").queryButton()).hasText("clicked!");
        assertThat(button).hasText("clicked!");
        verifyThat(".button", hasText("clicked!"), informedErrorMessage(fxRobot));
    }

}
