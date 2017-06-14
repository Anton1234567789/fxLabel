package antS3k3l3v.DataBaseConnection;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StartConnDB extends Application{
    private Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    TextField DATE_OP, OLDNAME, NEWNAME, XTO;
    PasswordField TACHKA;
    DatePicker date;

    public void start(Stage primaryStage) throws Exception {
        checkConnectionDB();
//        primaryStage.setTitle("DBSQL");
//        Scene scene = new Scene(root, 400,400);
//        primaryStage.setScene(scene);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setTitle("JavaFX 8 Tutorial 17 - Switching Scenes");
//        primaryStage.setTitle("JavaFX 8 Tutorial 18 - Database");
//        primaryStage.setTitle("JavaFX 8 Tutorial 19 - Database");
        primaryStage.setTitle("JavaFX 8 Tutorial 20 - DataPicker Database");

        BorderPane layout = new BorderPane();
        Scene newscene = new Scene(layout, 400,400, Color.rgb(0,0,0,0));

        Group group = new Group();
        Scene scene = new Scene(group, 320,160, Color.rgb(0,0,0,0));
//        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        Color foreground = Color.rgb(255,255,255,0.9);

        Rectangle rectangle= new Rectangle(320,180);
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setArcWidth(15);
        rectangle.setArcHeight(15);
        rectangle.setFill(Color.rgb(0,0,0,0.55));
        rectangle.setStroke(foreground);
        rectangle.setStrokeWidth(1.5);

        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(10,0,0,10));
        Label label = new Label("Label");
//        label.setTextFill(Color.WHITESMOKE);
        label.setFont(new Font("SasSerif",18));

        TextField usernameFiel = new TextField();
        usernameFiel.setFont(Font.font("SanSerif",17));
        usernameFiel.setPromptText("Login");
        usernameFiel.getStyleClass().add("field-background");

        PasswordField passwordField = new PasswordField();
        passwordField.setFont(Font.font("SanSerif",17));
        passwordField.setPromptText("Password");

        Button button = new Button("Login");
        button.setFont(Font.font("SanSerif", 14));
        button.setOnAction(event -> {
            try {
                String query = "select * from GASTELLO.ZAEBALI WHERE OLDNAME=? AND NEWNAME=?";
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, usernameFiel.getText());
                preparedStatement.setString(2, passwordField.getText());
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
//                    label.setText("Login successful");
                    primaryStage.setScene(newscene);
                    primaryStage.show();
                }else {
                    label.setText("login fail");
                }
                usernameFiel.clear();
                passwordField.clear();

                preparedStatement.close();
                resultSet.close();
            }catch (Exception e){
                label.setText("SQL Error");
                System.err.print(e);
            }
        });

        Button logut = new Button("Logut");
        logut.setFont(Font.font("SanSerif",15));
        logut.setOnAction(event -> {
                primaryStage.setScene(scene);
                primaryStage.show();
        });
        layout.setTop(logut);
        BorderPane.setAlignment(logut, Pos.TOP_RIGHT);
        BorderPane.setMargin(logut,new Insets(10));

        vBox.getChildren().addAll(label, usernameFiel,
                passwordField, button);
        group.getChildren().addAll(rectangle,vBox);

        VBox fields = new VBox(5);
        Label label1 = new Label("Create New String");
        label1.setFont(new Font("SanSerif", 20));

        DATE_OP = new TextField();
        DATE_OP.setFont(Font.font("SanSerif", 15));
        DATE_OP.setPromptText("DATE_OP");
        DATE_OP.setMaxWidth(300);

        OLDNAME = new TextField();
        OLDNAME.setFont(Font.font("SanSerif", 15));
        OLDNAME.setPromptText("OLDNAME");
        OLDNAME.setMaxWidth(300);

        NEWNAME = new TextField();
        NEWNAME.setFont(Font.font("SanSerif", 15));
        NEWNAME.setPromptText("NEWNAME");
        NEWNAME.setMaxWidth(300);

        XTO = new TextField();
        XTO.setFont(Font.font("SanSerif", 15));
        XTO.setPromptText("XTO");
        XTO.setMaxWidth(300);

        date = new DatePicker();
        date.setPromptText("Date of Birth");
        date.setMaxHeight(300);
        date.setStyle("-fx-font-size: 20");

//        TextField un = new TextField();
//        un.setFont(Font.font("SanSerif", 20));
//        un.setPromptText("TACHKA");
//        un.setMaxWidth(300);

        TACHKA = new PasswordField();
        TACHKA.setFont(Font.font("SanSerif", 15));
        TACHKA.setPromptText("TACHKA");
        TACHKA.setMaxWidth(300);

        // ДОБАВЛЕНИЕ ДАННЫХ В ТАБЛИЦУ ПО НАЖАТИЮ КНОПКИ
        Button button1 = new Button("Save");
        button1.setFont(Font.font("SanSerif",16));
        button1.setOnAction(event -> {
            try {
                String query = "INSERT INTO ZAEBALI (DATE_OP, OLDNAME, NEWNAME, XTO, TACHKA) " +
                        "VALUES (?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, DATE_OP.getText());
                preparedStatement.setString(2, OLDNAME.getText());
                preparedStatement.setString(3, NEWNAME.getText());
                preparedStatement.setString(4, XTO.getText());
                preparedStatement.setString(5, TACHKA.getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("User has been created");
                alert.showAndWait();

                preparedStatement.execute();
                preparedStatement.close();

                clearFields();
            }catch (Exception e){
                label.setText("SQL Error");
                System.err.print(e);
            }
        });
        fields.getChildren().addAll(label1, DATE_OP, OLDNAME, NEWNAME, XTO, TACHKA,date, button1);
        layout.setCenter(fields);


        BorderPane.setMargin(fields,new Insets(0,0,0,20));


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void checkConnectionDB() {
        connection = DBConnection.DBConnect();
        if (connection == null){
            System.out.println("Connection Not DB");
            System.exit(1);
        }else {
            System.out.println("Successful");
        }
    }

    public void clearFields(){
        DATE_OP.clear();
        OLDNAME.clear();
        NEWNAME.clear();
        XTO.clear();
        TACHKA.clear();
    }

    private void getRB(){


    }
    public static void main(String[] args) {
        launch(args);
    }
}
