import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by Антон on 29.05.2017.
 */
public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        Label label = new Label();

        label.setText("this is label");

        Image image = new Image(getClass().getResourceAsStream("2.jpg"));
        ImageView img = new ImageView(image);
        img.setFitHeight(200);
        img.setFitWidth(200);

        Label labelImage = new Label();
        labelImage.setGraphic(img);
        labelImage.setTranslateX(75);
        labelImage.setTranslateY(100);


        labelImage.setText("city");
        labelImage.setGraphicTextGap(20);
        labelImage.setContentDisplay(ContentDisplay.TOP);

        Label magicLabel = new Label();
        magicLabel.setText("this is a magic label");
        magicLabel.setTranslateX(140);
        magicLabel.setTranslateY(350);

        magicLabel.setOnMouseEntered(event->{
            magicLabel.setScaleX(1.5);
            magicLabel.setScaleY(1.5);
            magicLabel.setTextFill(Color.RED);
        });

        magicLabel.setOnMouseExited(event -> {
            magicLabel.setScaleX(1);
            magicLabel.setScaleY(1);
            magicLabel.setTextFill(Color.WHITE);
        });
        Button button = new Button();

        Buttons btn = new Buttons(button);


        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);

        HBox pic = new HBox();
        pic.setAlignment(Pos.BOTTOM_CENTER);


        RadioButton radioButton = new RadioButton("home");
        RadioButton radioButton1 = new RadioButton("calendar");
        RadioButton radioButton2 = new RadioButton("contact");

        ToggleGroup toggleGroup = new ToggleGroup();
        radioButton.setToggleGroup(toggleGroup);
        radioButton1.setToggleGroup(toggleGroup);
        radioButton2.setToggleGroup(toggleGroup);

        radioButton2.setSelected(true);
        hBox.getChildren().addAll(radioButton, radioButton1, radioButton2);
        borderPane.setTop(hBox);

        radioButton.setUserData("home");
        radioButton1.setUserData("calendar");
        radioButton2.setUserData("contact");

        ImageView imageView = new ImageView();

        toggleGroup.selectedToggleProperty().addListener(event -> {
            Image image1 = new Image(getClass().getResourceAsStream(toggleGroup.getSelectedToggle().getUserData() + ".png"));
            imageView.setImage(image1);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
        });


        pic.getChildren().add(imageView);
        root.getChildren().addAll(label);
        root.getChildren().add(labelImage);
        root.getChildren().add(magicLabel);
        root.getChildren().add(button);
        root.getChildren().addAll(borderPane);



        Scene scene = new Scene(root, 400,400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private void getRB(){


    }
    public static void main(String[] args) {
        launch(args);
    }
}
