package antS3k3l3v.TableWithExampleOfUser;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Starts extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {




        primaryStage.setTitle("javaFX");
        primaryStage.setWidth(450);
        primaryStage.setHeight(550);

        TableView<User> tableView = new TableView<>();
        final ObservableList<User> data = FXCollections.observableArrayList(
                new User("Anton","Sokolov", "google@com"),
                new User("Anton1","Sokolov1", "google1@com"),
                new User("Anton2","Sokolov2", "google2@com")
        );

        tableView.setItems(data);
        tableView.setEditable(true);

        Label label = new Label("User info");
        label.setFont(new Font("Times New Romans",20));
        label.setPadding(new Insets(10));

        TableColumn column1 = new TableColumn("First Name");
        column1.setMinWidth(100);
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn column2 = new TableColumn("Last Name");
        column2.setMinWidth(100);
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn column3 = new TableColumn("Email");
        column3.setMinWidth(100);
        column3.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.getColumns().addAll(column1,column2,column3);

        TextField firstNameTextField =  new TextField();
        firstNameTextField.setMaxWidth(column1.getPrefWidth());
        firstNameTextField.setPromptText("First Nams");

        TextField lastNameTextField =  new TextField();
        lastNameTextField.setMaxWidth(column2.getPrefWidth());
        lastNameTextField.setPromptText("Lasts Names");
        TextField emailTextField =  new TextField();
        emailTextField.setMaxWidth(column3.getPrefWidth());
        emailTextField.setPromptText("EMAIL");

        Button button = new Button("Add");
        button.setOnAction(event -> {
            data.add(new User(
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    emailTextField.getText())
            );
            firstNameTextField.clear();
            lastNameTextField.clear();
            emailTextField.clear();
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(firstNameTextField,lastNameTextField
        ,emailTextField, button);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, tableView, hBox);
        vBox.setPadding(new Insets(10,0,0,10));



        Scene scene = new Scene(new Group());
        ((Group)scene.getRoot()).getChildren().addAll(vBox);
//        Color foreground = Color.rgb(255,255,255,0.9);
        MenuBar menuBar = new MenuBar();
        ((Group)scene.getRoot()).getChildren().addAll(menuBar);

        javafx.scene.control.Menu fileMenu = new javafx.scene.control.Menu("File");
        MenuItem menuItem = new MenuItem("New Project           Ctrl+Shift+N");
        MenuItem menuItem1 = new MenuItem("Open Project           Ctrl+Shift+O");
        MenuItem menuItem2 = new MenuItem("Exit");
        menuItem2.setOnAction(event -> Platform.exit());
        fileMenu.getItems().addAll(menuItem,menuItem1, new SeparatorMenuItem(), menuItem2);

        menuBar.getMenus().addAll(fileMenu);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
