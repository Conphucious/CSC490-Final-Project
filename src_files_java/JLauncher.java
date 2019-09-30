import javafx.application.Application;
import javafx.stage.Stage;
import system.Window;

public class JLauncher extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new Window(primaryStage);
	}

}
