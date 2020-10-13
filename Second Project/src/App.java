import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class App extends Application{
   
    @Override
    public void start(Stage primaryStage)
    {
        Circle mycircle = new Circle();
        mycircle.setCenterX(100);
        mycircle.setCenterY(100);
        mycircle.setRadius(50);
        mycircle.setStroke(Color.BLACK);
        mycircle.setFill(Color.GOLD);
        //Button btOK= new Button("OK");
        Pane pane =new Pane();
        pane.getChildren().add(mycircle);
    
        
        Scene scene = new Scene(pane,400,200);
        primaryStage.setTitle("My Java Fx");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        System.out.println("Hey");
        Application.launch(args);
    }
}
