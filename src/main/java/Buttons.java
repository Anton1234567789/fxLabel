import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Buttons {

    Button button = new Button();

    public Buttons(Button button) {
        this.button = button;
        Image image = new Image(getClass().getResourceAsStream("2.jpg"));
        ImageView img = new ImageView(image);
        img.setFitHeight(40);
        img.setFitWidth(40);
        button.setGraphic(img);

        button.setTranslateX(200);
        button.setTranslateY(20);

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.AQUA);
        shadow.setRadius(40);

        button.setStyle("-fx-base:#b6e7c9");

        button.setOnAction(event -> {
            button.setEffect(shadow);
        });
    }

}
