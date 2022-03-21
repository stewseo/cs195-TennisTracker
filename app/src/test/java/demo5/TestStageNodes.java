package demo5;

import javafx.stage.Stage;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;

public class TestStageNodes {


        @AfterAll
        public static void cleanupSpec() throws Exception {
            FxToolkit.cleanupStages();
        }

        @Test
        public void registerPrimaryStageShouldBeCallableMultipleTimes() throws Exception {
            FxToolkit.registerPrimaryStage();
            FxToolkit.registerPrimaryStage();
            FxToolkit.registerPrimaryStage();
        }

        @Test
        public void registerPrimaryStage_should_return_the_primary_stage() throws Exception {

            Stage stage = FxToolkit.registerPrimaryStage();

            verifyThat(stage, instanceOf(Stage.class));
        }

        @Test
        public void registerPrimaryStage_should_update_the_registered_stage() throws Exception {

            FxToolkit.toolkitContext().setRegisteredStage(null);

            Stage stage = FxToolkit.registerPrimaryStage();

            verifyThat(FxToolkit.toolkitContext().getRegisteredStage(), CoreMatchers.is(stage));
        }

        @Test
        public void registerStage_should_return_the_stage() throws Exception {

            Stage stage = FxToolkit.registerStage(Stage::new);

            verifyThat(stage, instanceOf(Stage.class));
        }


        @Test
        public void registerStage_should_update_the_registered_stage() throws Exception {

            FxToolkit.toolkitContext().setRegisteredStage(null);

            Stage stage = FxToolkit.registerStage(Stage::new);
            assertNotNull(FxToolkit.toolkitContext().getRegisteredStage());
        }

        @Test
        public void showStage_should_show_the_registered_stage() throws Exception {

            Stage stage = FxToolkit.registerStage(Stage::new);
            FxToolkit.showStage();
            assertTrue(stage.isShowing());
        }

        @Test
        public void hideStage_should_hide_the_registered_stage() throws Exception {

            Stage stage = FxToolkit.registerStage(Stage::new);
            FxToolkit.showStage();
            FxToolkit.hideStage();
            assertFalse(stage.isShowing());
        }
}
