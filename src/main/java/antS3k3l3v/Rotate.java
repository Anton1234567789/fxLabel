package antS3k3l3v;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Rotate extends Application {
    private int degree = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Button btn = new Button();
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("helloWorld");
//            }
//        });

        BorderPane root = new BorderPane();



//        StackPane root = new StackPane();
//        root.getChildren().addAll(btn);

        Scene scene = new Scene(root, 500,500);
        Color foreground = Color.rgb(255,255,255,0.9);

        Rectangle rectangle = new Rectangle(300,200);
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setArcHeight(15);
        rectangle.setArcWidth(15);

        rectangle.setFill(Color.rgb(0,0,0,0.55));
        rectangle.setStroke(foreground);
        rectangle.setStrokeWidth(1.5);

        root.setCenter(rectangle);

        Button button = new Button("Rotate");
        button.setOnAction(event -> {
            if(event.getSource() == button){
                degree = degree + 15;
                rectangle.setRotate(degree);
            }
        });

        root.setBottom(button);
        BorderPane.setMargin(button, new Insets(0,0,20,200));
        primaryStage.setTitle("javaFX viewer query");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
