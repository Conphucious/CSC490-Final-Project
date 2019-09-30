package system;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import screen.MainScreen;

public class Window {

	private static StackPane sp;
	private static Stage primaryStage;

	public Window(Stage primaryStage) {
		Window.primaryStage = primaryStage;
		
		String[] cmd = {"sudo", "modprobe" , "uinput"};
		String[] cmd2 = {"sudo", "python" , "kbrd.py"};
		try {
			Runtime.getRuntime().exec(cmd);
			Runtime.getRuntime().exec(cmd2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sp = new StackPane();

		primaryStage.setScene(new Scene(sp, 240, 320));
		primaryStage.getScene().setFill(Color.BLACK);
		primaryStage.setTitle("JOMBO ATΞ");
		primaryStage.show();
		
//		Configuration.setStage("mobile");
		
		new MainScreen(0);
	}
	
	public static StackPane getSp() {
		return sp;
	}
	
	public static Stage getStage() {
		return primaryStage;
	}
	
}
