package com.example.application.controller;

import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoaderBean;
import io.github.palexdev.materialfx.utils.ToggleUtils;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.application.ResourceLoader.loadURL;

public class MainController implements Initializable {
    private final Stage stage;
    private double xOffset;
    private double yOffset;
    private final ToggleGroup toggleGroup; // control with a Boolean indicating whether it is selected
                                            //icons passed through toggle

    @FXML
    private HBox windowHeader;

    //the root buttons located top right of gui
    @FXML private MFXFontIcon closeIcon;

    @FXML private MFXFontIcon minimizeIcon;

    @FXML private MFXFontIcon alwaysOnTopIcon;
    //parent backing frame
    @FXML private AnchorPane rootPane;

    @FXML
    private MFXScrollPane scrollPane;

    @FXML
    private VBox navBar;

    //stackpane layout area we are using for tableview, node attachments.
    @FXML
    private StackPane contentPane;

    //constructor for when sidepane buttons are activated. Pass root, stage, controller.
    public MainController(Stage stage) {
        this.stage = stage;
        this.toggleGroup = new ToggleGroup();
        ToggleUtils.addAlwaysOneSelectedSupport(toggleGroup);
    }

    //root = top level javafx container Node
    //FXMLLoader and null pointer exceptions will be thrown without specific ordering. Node root and Controller controller must be specified.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));
        alwaysOnTopIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> { //top of your view stack
            boolean newVal = !stage.isAlwaysOnTop();
            alwaysOnTopIcon.pseudoClassStateChanged(PseudoClass.getPseudoClass("always-on-top"), newVal);
            stage.setAlwaysOnTop(newVal);
        });

        //tracks mouse movements and actions within this node.
        windowHeader.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });
        windowHeader.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });

        initializeLoader();

        ScrollUtils.addSmoothScrolling(scrollPane);
    }

    //sidepane toggles:
    private void initializeLoader() {

        MFXLoader loader = new MFXLoader();

        loader.addView(MFXLoaderBean.of("Root", loadURL("Buttons.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-circle-dot", "Root")).setDefaultRoot(true).get());
        loader.addView(MFXLoaderBean.of("Atp Players", loadURL("AtpTour.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-toggle-on", "Atp Tour")).get());
        loader.addView(MFXLoaderBean.of("GrandSlams", loadURL("GrandSlam.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-list-dropdown", "GrandSlams")).get());
        loader.addView(MFXLoaderBean.of("WtaPlayer.fxml", loadURL("WtaTour.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-table", "Wta Tour")).get());
        loader.addView(MFXLoaderBean.of("Tournaments", loadURL("Tournaments.fxml")).setBeanToNodeMapper(() -> createToggle("mfx-square-list", "Tournaments")).get());

        loader.setOnLoadedAction(beans -> {
            //list of viewNodes that were collected from your tree
            List<ToggleButton> nodes = beans.stream()
                    .map(bean -> {
                        ToggleButton toggle = (ToggleButton) bean.getBeanToNodeMapper().get();
                        toggle.setOnAction(event -> contentPane.getChildren().setAll(bean.getRoot()));
                        if (bean.isDefaultView()) {
                            //the icon used as reference:  stackpane -> (row,column) tableview, tableColumns, rowCells, combo boxes, comboFilters, textfields, labels
                            contentPane.getChildren().setAll(bean.getRoot());
                            toggle.setSelected(true);
                        }
                        return toggle;
                    })
                    .toList();
            navBar.getChildren().setAll(nodes);
        });
        loader.start();
    }

    private ToggleButton createToggle(String icon, String text) {
        MFXIconWrapper wrapper = new MFXIconWrapper(icon, 24, 32);
        MFXRectangleToggleNode toggleNode = new MFXRectangleToggleNode(text, wrapper);
        toggleNode.setAlignment(Pos.CENTER_LEFT);
        toggleNode.setMaxWidth(Double.MAX_VALUE);
        toggleNode.setToggleGroup(toggleGroup);

        return toggleNode;
    }
}
