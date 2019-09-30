package screen;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import system.Configuration;
import system.Window;

public class TosScreen extends Screen {

	public TosScreen() {
		super(null, null);
		content();
		setAction();
		checkConnectivity();
	}
	
	private void content() {
		Window.getSp().getChildren().clear();
		HBox mainPane = new HBox(10);
		VBox box = new VBox(10);
		
		Text header = new Text("Terms of Service");
		header.setFill(Color.WHITE);
		header.setFont(new Font("Verdana", 20));

		Text text = new Text("Your information may not\n"
				+ "be safely stored in our services\n"
				+ "so do not use credentials that\n"
				+ "you use on a normal basis.\n"
							+ "\n\nVisit: http://pnguyen3.com\n"
							+ "for more detailed information.");
		text.setFill(Color.WHITE);
		text.setFont(new Font("Verdana", 12));
		
		box.setAlignment(Pos.CENTER_LEFT);
		mainPane.setAlignment(Pos.TOP_CENTER);
		box.getChildren().addAll(header, text);
		mainPane.getChildren().add(box);
		
		Window.getSp().getChildren().add(mainPane);
		
		Text topText = new Text("Go back");
		HBox topBox = new HBox(5);
		topBox.getChildren().addAll(topText, new Text(""));
		topBox.setAlignment(Pos.TOP_RIGHT);
		topText.setFill(Color.DIMGREY);
		Window.getSp().getChildren().add(topBox);
	}
	
	
	private void setAction() {
		Window.getStage().getScene().setOnKeyPressed(event -> {
			if (event.getCode() == Configuration.key5)
				new MainScreen(3);
		});
	}

}
