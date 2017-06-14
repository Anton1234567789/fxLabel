package antS3k3l3v.ListView;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaFX Tutorial13");

        VBox vBox = new VBox();

        Scene scene = new Scene(vBox, 400,400);

        ListView listView  = new ListView();
        ObservableList<String> data = FXCollections.observableArrayList(
                "red", "black", "green" ,"yellow", "pink",
                "blue", "violet", "brown", "silver"
        );
        listView.setItems(data);
        listView.setCellFactory(event->{
            return new ColorName();
        });

        Label colorName = new Label();
        colorName.setLayoutX(10);
        colorName.setLayoutY(100);
        colorName.setFont(new Font("Times New Romans", 20));

        listView.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv) ->{
            colorName.setText((String) nv);
            colorName.setTextFill(Color.web((String) nv));
                }
        );

        vBox.getChildren().addAll(listView, colorName);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    static class ColorName extends ListCell<String> {

        @Override
        public  void  updateItem(String item, boolean empty){
            super.updateItem(item,empty);
            Rectangle rectangle = new Rectangle(100, 20);
            if (item != null){
                rectangle.setFill(Color.web(item));
                setGraphic(rectangle);
            }else {
                setGraphic(null);
            }

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
