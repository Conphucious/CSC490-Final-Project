package screen;

import java.sql.SQLException;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Challenge;
import system.Configuration;
import system.Database;
import system.Window;

public class AcceptChallengeScreen extends Screen {

	public AcceptChallengeScreen(int challengeId) {							//show latest 5 challenges to do. IF DONE, DONT SHOW					// last 5 did in history
		super(new Text[] { new Text("Yes"), new Text("             "), new Text("             "), new Text(" Go Back") }, new Text("Confirm"));
		content(challengeId);
		setMenu(0);
		setAction(challengeId);
	}
	
	private void content(int challengeId) {
		Window.getSp().getChildren().clear();
		HBox mainPane = new HBox(10);
		VBox box = new VBox(10);

		Text header = new Text("Confirm that you\ncompleted the\nchallenge.\n");
		header.setFill(Color.WHITE);
		header.setFont(new Font("Verdana", 20));
		
		Text text = new Text("");
		
		try {
			Challenge c = Database.selectFromAvailableChallenge(challengeId - 1);
			text = new Text(c.toString());
			text.setFill(Color.WHITE);
			text.setFont(new Font("Verdana", 12));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		box.setAlignment(Pos.CENTER);
		mainPane.setAlignment(Pos.CENTER);
		box.getChildren().addAll(header, text);
		mainPane.getChildren().add(box);
		Window.getSp().getChildren().add(mainPane);
	}
	
	private void setAction(int challengeId) {
		Window.getStage().getScene().setOnKeyPressed(event -> {
			if (event.getCode() == Configuration.key1)
				setCurrentMenu(0);
			else if (event.getCode() == Configuration.key4)
				setCurrentMenu(3);
			else if (event.getCode() == Configuration.key5) {
				switch (menuIndex) {
				case 0: 
					new SuccessScreen(challengeId);
					break;
				case 3:
					new ChallengeScreen();
					break;
				default:
					break;
				}
			}
		});
	}

}
