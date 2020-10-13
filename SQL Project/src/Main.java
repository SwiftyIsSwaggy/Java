import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    Label Welcome = new Label("Welcome to the Database");
    FileInputStream input = new FileInputStream("D:\\VSC\\Java\\SQL Project\\res\\BackU.jpg");
    Image myPic = new Image(input);

    BackgroundImage backgrndImage = new BackgroundImage(myPic, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    Background backgrnd = new Background(backgrndImage);
    DropShadow ds = new DropShadow();
    ds.setColor(Color.LIGHTBLUE);

 
 

    Button Enter = new Button("Enter");
    Enter.setPrefWidth(80.0);
    Enter.setDefaultButton(true);
    Enter.setOnAction(e -> {
      SecondScene secondWindow = new SecondScene();
      secondWindow.start(primaryStage);
    });

    Button Close = new Button("Close");
    Close.setPrefWidth(80.0);
    Close.setCancelButton(true);
    Close.setOnAction(e -> primaryStage.close());

    GridPane layout = new GridPane();
    layout.setAlignment(Pos.CENTER);
    layout.setVgap(5);
    layout.setHgap(5);
    layout.setPadding(new Insets(10, 10, 10, 10));
    layout.add(Welcome, 0, 0,2,1);
    layout.add(Enter, 0, 1);
    layout.add(Close, 1, 1);
    layout.setBackground(backgrnd);

    Welcome.setTextFill(Color.DARKSLATEBLUE);
    Welcome.setEffect(ds);

    Welcome.setStyle("-fx-font: normal bold 30px 'serif' ");
    Scene view = new Scene(layout, 600, 600);
    view.setOnKeyPressed(e -> {
      e.consume();
    });
    primaryStage.setScene(view);
    primaryStage.setTitle("Pangas John Database");
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}
