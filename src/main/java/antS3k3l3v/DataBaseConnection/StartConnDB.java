package antS3k3l3v.DataBaseConnection;


import antS3k3l3v.DataBaseConnection.objects.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartConnDB extends Application{
    private Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    TextField DATE_OP, OLDNAME, NEWNAME, XTO;
    PasswordField TACHKA;
    DatePicker date;
    final ObservableList options = FXCollections.observableArrayList();
    final ObservableList<User> data = FXCollections.observableArrayList();

    TableView<User> table;
    ListView listView;
    private RadioButton male;
    private RadioButton female;
    private String radiobuttonLabel;

    public void start(Stage primaryStage) throws Exception {
        checkConnectionDB();


//        primaryStage.setTitle("DBSQL");
//        Scene scene = new Scene(root, 400,400);
//        primaryStage.setScene(scene);
//        primaryStage.initStyle(StageStyle.TR444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444446666666666666666666666666666666666666666666666666666666666ANSPARENT);
//        primaryStage.setTitle("JavaFX 8 Tutorial 17 - Switching Scenes");
//        primaryStage.setTitle("JavaFX 8 Tutorial 18 - Database");
//        primaryStage.setTitle("JavaFX 8 Tutorial 19 - Database");
//        primaryStage.setTitle("JavaFX 8 Tutorial 20 - DataPicker Database");
//        primaryStage.setTitle("JavaFX 8 Tutorial 21 - Table and Database");
//        primaryStage.setTitle("JavaFX 8 Tutorial 22 - Combobox and Database");
//        primaryStage.setTitle("JavaFX 8 Tutorial 23 - Combobox, TextField and Database");
//        primaryStage.setTitle("JavaFX 8 Tutorial 25 - Refresh Tab;lle On Adding and Database");
//        primaryStage.setTitle("JavaFX 8 Tutorial 26 - Login From Database On Enter KeyPressed");
//        primaryStage.setTitle("JavaFX 8 Tutorial 27 - Warning Alert Dialog Database ");
//        primaryStage.setTitle("JavaFX 8 Tutorial 28 - Delete user From Database ");

//        primaryStage.setTitle("JavaFX 8 Tutorial 29 - Radio Button From Database ");
//        primaryStage.setTitle("JavaFX 8 Tutorial 32 - Validate and Clear RB From Database ");
//        primaryStage.setTitle("JavaFX 8 Tutorial 33 - Fetch or Get RB From Database ");
        primaryStage.setTitle("JavaFX 8 Tutorial 34 - Number or USER ID From Database ");

        BorderPane layout = new BorderPane();
        Scene newscene = new Scene(layout, 1000,500, Color.rgb(0,0,0,0));

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

        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
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

                            fillComboBox();
                    preparedStatement.close();
                    resultSet.close();
                }catch (Exception e){
                    label.setText("SQL Error");
                    System.err.print(e);
                }
            }
        });


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
                fillComboBox();
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


        TACHKA = new PasswordField();
        TACHKA.setFont(Font.font("SanSerif", 15));
        TACHKA.setPromptText("TACHKA");
        TACHKA.setMaxWidth(300);

        ToggleGroup gender = new ToggleGroup();

        male = new RadioButton("Male");
        male.setToggleGroup(gender);
        male.setOnAction(event -> {
                radiobuttonLabel = male.getText();
        });

        female = new RadioButton("FeMale");
        female.setToggleGroup(gender);
        female.setOnAction(event -> {
            radiobuttonLabel = female.getText();
        });

        // ДОБАВЛЕНИЕ ДАННЫХ В ТАБЛИЦУ ПО НАЖАТИЮ КНОПКИ
        Button save = new Button("Save");
        save.setFont(Font.font("SanSerif",16));
        save.setOnAction(event -> {
            if (validateFields() & validateNumber()){
            try {
                String query = "INSERT INTO ZAEBALI (DATE_OP, OLDNAME, NEWNAME, XTO, TACHKA, GENDER) " +
                        "VALUES (?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDate(1, (Date.valueOf(DATE_OP.getText())));
                preparedStatement.setString(2, OLDNAME.getText());
                preparedStatement.setString(3, NEWNAME.getText());
                preparedStatement.setString(4, XTO.getText());
                preparedStatement.setString(5, TACHKA.getText());
                preparedStatement.setString(6, radiobuttonLabel);
//                preparedStatement.setString(6, ((TextField)date.getEditor()).getText());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("User has been created");
                alert.showAndWait();

                preparedStatement.executeUpdate();
                preparedStatement.close();
                resultSet.close();
                clearFields();
                refreshTable();

                fillComboBox();
            }catch (Exception e){
                label.setText("SQL Error");
                System.err.print(e);
            }
            }
        });
        fields.getChildren().addAll(label1, DATE_OP, OLDNAME, NEWNAME, XTO, TACHKA,date,male,female ,save);
        layout.setCenter(fields);


        BorderPane.setMargin(fields,new Insets(0,0,0,20));

        table = new TableView<User>();

        TableColumn column = new TableColumn("DATE_OP");
        column.setMinWidth(80);
        column.setCellValueFactory(new PropertyValueFactory<>("DATE_OP"));

        TableColumn column1 = new TableColumn("OLDNAME");
        column1.setMinWidth(100);
        column1.setCellValueFactory(new PropertyValueFactory<>("OLDNAME"));

        TableColumn column2 = new TableColumn("NEWNAME");
        column2.setMinWidth(100);
        column2.setCellValueFactory(new PropertyValueFactory<>("NEWNAME"));

        TableColumn column3 = new TableColumn("XTO");
        column3.setMinWidth(60);
        column3.setCellValueFactory(new PropertyValueFactory<>("XTO"));

        TableColumn column4 = new TableColumn("TACHKA");
        column4.setMinWidth(30);
        column4.setCellValueFactory(new PropertyValueFactory<>("TACHKA"));

        TableColumn column5 = new TableColumn("Gender");
        column5.setMinWidth(30);
        column5.setCellValueFactory(new PropertyValueFactory<>("GENDER"));

        table.getColumns().addAll(column,column1,column2,column3,column4,column5);
        table.setTableMenuButtonVisible(true);

        layout.setRight(table);
        BorderPane.setMargin(table, new Insets(0,35,10,50));

        Button loadDB = new Button("Load data table");
        loadDB.setFont(Font.font("SanSerif", 15));
        loadDB.setOnAction(event -> {
        refreshTable();
        });

        ComboBox comboBox = new ComboBox(options);
        comboBox.setMaxHeight(30);

        comboBox.setOnAction(event -> {
            String query = "Select * FROM ZAEBALI WHERE OLDNAME = ? ";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, (String)comboBox.getSelectionModel().getSelectedItem());
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    DATE_OP.setText(resultSet.getString("DATE_OP"));
                    OLDNAME.setText(resultSet.getString("OLDNAME"));
                    NEWNAME.setText(resultSet.getString("NEWNAME"));
                    XTO.setText(resultSet.getString("XTO"));
                    TACHKA.setText(resultSet.getString("TACHKA"));
                    if ("Male".equals(resultSet.getString("GENDER"))){
                        male.setSelected(true);
                    }else if("FeMale".equals(resultSet.getString("GENDER"))){
                        female.setSelected(true);
                    }
                    else {
                        male.setSelected(false);
                        female.setSelected(false);
                    }
//                    ((TextField)date.getEditor().setText(resultSet.getString("date"));
                }

                preparedStatement.execute();
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        Button delete = new Button("Delete User");
        delete.setFont(Font.font("SanSerif",15));
        delete.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conformation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure delete record?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                try{
                    String query = "DELETE FROM ZAEBALI WHERE OLDNAME = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, OLDNAME.getText());


                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    resultSet.close();
                    clearFields();
                    refreshTable();
                    fillComboBox();
                }catch (Exception e){
                    label.setText("SQL Error");
                    System.err.print(e);
                }
            }

        });



        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(loadDB, delete, comboBox);


        layout.setBottom(hBox);
        BorderPane.setMargin(hBox, new Insets(10,0,10,600));

        listView = new ListView(options);
        listView.setMaxSize(100, 250);
        layout.setLeft(listView);
        BorderPane.setMargin(listView, new Insets(10));

        listView.setOnMouseClicked(event -> {
            String query = "Select * FROM ZAEBALI WHERE OLDNAME = ? ";
            try {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, (String)listView.getSelectionModel().getSelectedItem());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    DATE_OP.setText(resultSet.getString("DATE_OP"));
                    OLDNAME.setText(resultSet.getString("OLDNAME"));
                    NEWNAME.setText(resultSet.getString("NEWNAME"));
                    XTO.setText(resultSet.getString("XTO"));
                    TACHKA.setText(resultSet.getString("TACHKA"));

                    if (null != resultSet.getString("GENDER"))
                        switch (resultSet.getString("GENDER")){
                            case "Male":
                                male.setSelected(true);
                                break;
                            case "FeMale":
                                female.setSelected(true);
                                break;
                                default:
                                    male.setSelected(false);
                                    female.setSelected(false);
                                    break;
                        }  else {
                        male.setSelected(false);
                        female.setSelected(false);
                    }
//                    ((TextField)date.getEditor().setText(resultSet.getString("date"));
                }
                preparedStatement.execute();
                preparedStatement.close();
                resultSet.close();
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private boolean validateNumber(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(TACHKA.getText());
        if (m.find() && m.group().equals(TACHKA.getText())){
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("VaLidate Number TACHKA");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Number TACHKA");
            alert.showAndWait();
            return false;
        }
    }

    private  boolean validateFields(){
        if (DATE_OP.getText().isEmpty() | OLDNAME.getText().isEmpty() |
                NEWNAME.getText().isEmpty() | XTO.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("VaLidate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Into The Fields");
            alert.showAndWait();

            return false;
        }
        if (!(male.isSelected() | female.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("VaLidate genders");
            alert.setHeaderText(null);
            alert.setContentText("Please Entery Gender (Male,Female)");
            alert.showAndWait();

            return false;
        }
        return true;
    }

    public void refreshTable(){
            data.clear();
        try{
            String query = "Select * From ZAEBALI";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                data.add(new User(
                        resultSet.getString("DATE_OP"),
                        resultSet.getString("OLDNAME"),
                        resultSet.getString("NEWNAME"),
                        resultSet.getString("XTO"),
                        resultSet.getString("TACHKA"),
                        resultSet.getString("GENDER")
                ));
                table.setItems(data);
            }

            preparedStatement.executeUpdate();

            preparedStatement.close();
            resultSet.close();
        }catch (Exception e){
            System.err.print(e);
        }
    }


    public void fillComboBox(){

        options.clear();
        try {
            String query = "select OLDNAME from ZAEBALI";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                options.add(resultSet.getString("OLDNAME"));
            }

            preparedStatement.executeUpdate();
            preparedStatement.close();
            resultSet.close();
            refreshTable();

        } catch (SQLException e) {e.printStackTrace();

        }


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
        male.setSelected(false);
        female.setSelected(false);
//        date.setValue(null);
    }

    private void getRB(){


    }
    public static void main(String[] args) {
        launch(args);
    }
}
