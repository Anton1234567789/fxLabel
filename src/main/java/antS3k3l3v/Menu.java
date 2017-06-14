package antS3k3l3v;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by Антон on 01.06.2017.
 */
public class Menu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 500,500);
        Color foreground = Color.rgb(255,255,255,0.9);

        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);

        javafx.scene.control.Menu fileMenu = new javafx.scene.control.Menu("File");
        MenuItem menuItem = new MenuItem("New Project           Ctrl+Shift+N");
        MenuItem menuItem1 = new MenuItem("Open Project           Ctrl+Shift+O");
        MenuItem menuItem2 = new MenuItem("Exit");
        menuItem2.setOnAction(event -> Platform.exit());
        fileMenu.getItems().addAll(menuItem,menuItem1, new SeparatorMenuItem(), menuItem2);


        javafx.scene.control.Menu viewMenu = new javafx.scene.control.Menu("View");
        CheckMenuItem checkMenuItem = new CheckMenuItem("editor");
        checkMenuItem.setSelected(true);
        CheckMenuItem checkMenuItem1 = new CheckMenuItem("Line Number");
        checkMenuItem1.setSelected(true);
        viewMenu.getItems().addAll(checkMenuItem,checkMenuItem1, new SeparatorMenuItem());

        javafx.scene.control.Menu toolBar = new javafx.scene.control.Menu("ToolBar");
        toolBar.getItems().addAll(new CheckMenuItem("File"), new CheckMenuItem("Run"),new CheckMenuItem("Debug"));


        viewMenu.getItems().addAll(toolBar);

        javafx.scene.control.Menu mode = new javafx.scene.control.Menu("Mode");
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioMenuItem mode1 = new RadioMenuItem("Desktop");
       mode1.setToggleGroup(toggleGroup);
        RadioMenuItem mode2 = new RadioMenuItem("Tablet");
        mode2.setToggleGroup(toggleGroup);
        mode2.setSelected(true);

        mode.getItems().addAll(mode1,mode2);

        menuBar.getMenus().addAll(fileMenu, viewMenu, mode);

        primaryStage.setTitle("javaFX viewer query");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
