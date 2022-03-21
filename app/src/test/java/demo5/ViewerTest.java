package demo5;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;

import java.awt.*;

public class ViewerTest {

    @Test
    public static void RegisterSetupShowStage () throws Exception {
        beforeClass();

        before();
        test();
        after();

        before();
        test();
        after();

        before();
        test();
        after();

        before();
        test();
        after();

        afterClass();
        cleanup();
    }

    private static void beforeClass() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.hideStage();
    }

    private static void afterClass() {}



    private static void before() throws Exception {
        FxToolkit.registerStage(Stage::new);
        FxToolkit.setupStage(stage -> stage.setScene(new Scene(new Label("within custom stage"))));
        FxToolkit.showStage();
    }

    private static void after() throws Exception {
        FxToolkit.hideStage();
    }

    private static void test() throws Exception {
        Thread.sleep(500);
    }

    private static void cleanup() {
        Platform.setImplicitExit(true);
    }

}

