
package tictactoe;

/**
 *
 * @author mohamed
 */
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Toast {

    public static void makeText(Stage stage, String message) {
        stage.initStyle(StageStyle.UNDECORATED);

        // Create a label to display the message
        Label label = new Label(message);
        label.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-text-fill: white; -fx-padding: 10px;");

//        VBox root = new VBox(label);
//        root.setAlignment(Pos.BOTTOM_CENTER);
//        root.setStyle("-fx-background-color: transparent;");
        //Create a StackPane to hold the label
        StackPane root = new StackPane();
        root.getChildren().add(label);
        StackPane.setAlignment(label, Pos.BOTTOM_CENTER);
        root.setStyle("-fx-background-color: transparent;");
        root.setMaxHeight(Double.MAX_VALUE);

        // Create a Scene with the StackPane as its root
        Scene scene = new Scene(root);
        scene.setFill(null);
        root.prefHeightProperty().bind(scene.heightProperty());

        // Set the scene on the stage and show it
        stage.setScene(scene);
        stage.show();

        // Create a Timeline animation to fade out the label after 2 seconds
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(label.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(4), new KeyValue(label.opacityProperty(), 0.0))
        );

        // Start the Timeline animation
        timeline.play();

        // Close the stage after the animation completes
        timeline.setOnFinished(event -> {
            stage.close();
        });
    }
}