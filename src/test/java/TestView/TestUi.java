package TestView;

import fr.brouillard.oss.cssfx.CSSFX;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class TestUi extends Application {
    private VBox container;

    public Parent getRootNode() {
        return container;
    }

    private Parent buildUI() {
        container = new VBox();
        container.getStyleClass().add("container");

        Label lblWelcome = new Label("Welcome");
        Label lblCSSFX = new Label("CSSFX");
        lblCSSFX.setId("cssfx");

        container.getChildren().addAll(lblWelcome, lblCSSFX);

        String defaultURI = TestUi.class.getResource("testDefault.css").toExternalForm();
        String basicURI = TestUi.class.getResource("testContainer.css").toExternalForm();
        container.getStylesheets().addAll(defaultURI, basicURI);
        return container;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent p = buildUI();
        Scene s = new Scene(p, 800, 600);
        stage.setScene(s);
        stage.show();
    }

    public Runnable startCSSFX() {
        try {
            // The CSS used by the UI
            URI basicCSS = TestUi.class.getResource("testContainer.css").toURI();
            // a resource containing the required changes we want to apply to the CSS
            URI changedBasicCSS = TestUi.class.getResource("basic-cssfx-blue.css").toURI();

            // start CSSFX
            return CSSFX.onlyFor(getRootNode())
                    .noDefaultConverters()
                    .addConverter((uri) -> {
                        try {
                            if (basicCSS.toURL().toExternalForm().equals(uri)) {
                                return Paths.get(changedBasicCSS);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }).start();
        } catch (URISyntaxException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
