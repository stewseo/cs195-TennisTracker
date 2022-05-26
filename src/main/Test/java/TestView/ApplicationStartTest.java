package TestView;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javafx.beans.InvalidationListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.framework.junit.TestFXRule;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.fail;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.util.DebugUtils.informedErrorMessage;

public class ApplicationStartTest extends ApplicationTest {

    @Rule
    public TestFXRule testFXRule = new TestFXRule();
    CountDownLatch setButtonTextLatch;

    @Override
    public void init() throws Exception {
        FxToolkit.registerStage(Stage::new);
    }

    @Override
    public void start(Stage stage) {
        CountDownLatch setSceneLatch = new CountDownLatch(1);
        setButtonTextLatch = new CountDownLatch(1);
        InvalidationListener invalidationListener = observable -> setSceneLatch.countDown();
        stage.sceneProperty().addListener(observable -> {
            setSceneLatch.countDown();
            stage.sceneProperty().removeListener(invalidationListener);
        });
        Button button = new Button("click me!");
        button.setOnAction(actionEvent -> {
            button.setText("clicked!");
            setButtonTextLatch.countDown();
        });
        stage.setScene(new Scene(button, 100, 100));
        try {
            if (!setSceneLatch.await(10, TimeUnit.SECONDS)) {
                fail("Timeout while waiting for scene to be set on stage.");
            }
        }
        catch (Exception ex) {
            fail("Unexpected exception: " + ex);
        }
        stage.show();
    }

    @Override
    public void stop() throws TimeoutException {
        FxToolkit.hideStage();
    }

    @Test(timeout = 3000)
    public void should_contain_button() {
        // expect:
        verifyThat(".button", hasText("click me!"), informedErrorMessage(this));
    }

    @Test(timeout = 3000)
    public void should_click_on_button() {
        // when:
        moveTo(".button");
        press(MouseButton.PRIMARY);
        release(MouseButton.PRIMARY);

        // then:
        verifyThat(".button", hasText("clicked!"), informedErrorMessage(this));
    }

}