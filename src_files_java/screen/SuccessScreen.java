package screen;

import java.sql.SQLException;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import system.Configuration;
import system.Database;
import system.Window;

public class SuccessScreen extends Screen {

	public SuccessScreen(int challengeId) {
		super(null, null);
		content(challengeId);
		setAction();
		checkConnectivity();
	}
	
	private void content(int challengeId) {
		Window.getSp().getChildren().clear();
		HBox mainPane = new HBox(10);
		VBox box = new VBox(10);
		
		String head = "";
		String body = "";
		
		try {
			if (Database.isChallengeInsertedIntoDb(challengeId)) {
				head = "Successfully\nsent Data!";
				body = "The challenge has been\nsuccessfully completed";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			head ="An error occured!";
			body = "Please try again later!";
		}
		
		
		Text header = new Text(head);
		header.setFill(Color.WHITE);
		header.setFont(new Font("Verdana", 20));

		Text text = new Text(body);
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
				new MainScreen(1);
		});
	}

}
