import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Javafx extends Application{
   
    @Override
    public void start(Stage primaryStage)
    {
        Button btOK= new Button("OK");
        Scene scene = new Scene(btOK,200,500);
        primaryStage.setTitle("My Java Fx");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}