package demo5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testfx.api.FxRobot;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.util.WaitForAsyncUtils;

public class TestFXTestCaseBase extends FxRobot {
    protected Stage blockStage;

        @BeforeAll
        public void baseSetupSpec() throws Exception {
            FxToolkit.registerPrimaryStage();

            FxToolkit.setupStage(stage -> {
                Region region = new Region();
                String bg = "-fx-background-color: magenta;";
                region.setStyle(bg);

                VBox box = new VBox(region);
                box.setPadding(new Insets(10));
                box.setSpacing(10);
                VBox.setVgrow(region, Priority.ALWAYS);

                StackPane sceneRoot = new StackPane(box);
                Scene scene = new Scene(sceneRoot, 300, 100);
                stage.setScene(scene);
                stage.show();
            });
        }

        @AfterAll
        public final void tearDown() throws Throwable {
            if (blockStage != null) {
                interact(() -> blockStage.close());
            }
            release(new KeyCode[0]);
            release(new MouseButton[0]);
            FxToolkit.cleanupStages();
            WaitForAsyncUtils.checkException();
        }



    }

