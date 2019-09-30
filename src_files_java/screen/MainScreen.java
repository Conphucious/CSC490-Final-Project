package screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Asciiji;
import system.Configuration;
import system.Window;

public class MainScreen extends Screen {

	public MainScreen(int idx) {							//show latest 5 challenges to do. IF DONE, DONT SHOW					// last 5 did in history
		super(new Text[] { new Text("Account"), new Text(" Challenge "), new Text(" History "), new Text(" ToS") }, new Text("Confirm"));
		content();
		setMenu(idx);
		setAction();
		
		checkConnectivity();
	}

	private void content() {
		Window.getSp().getChildren().clear();
		VBox mainPane = new VBox(10);

		Text text = new Text(Asciiji.initExp);
		text.setFill(Color.WHITE);
		text.setFont(new Font("Verdana", 80));

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(.5), event -> text.setText(Asciiji.initExp)),
				new KeyFrame(Duration.seconds(5), event -> text.setText(Asciiji.blinkExp)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		mainPane.getChildren().add(text);
		mainPane.setAlignment(Pos.CENTER);
		Window.getSp().getChildren().add(mainPane);
	}
	
	private void setAction() {
		Window.getStage().getScene().setOnKeyPressed(event -> {
			if (event.getCode() == Configuration.key1)
				setCurrentMenu(0);
			else if (event.getCode() == Configuration.key2)
				setCurrentMenu(1);
			else if (event.getCode() == Configuration.key3)
				setCurrentMenu(2);
			else if (event.getCode() == Configuration.key4)
				setCurrentMenu(3);
			else if (event.getCode() == Configuration.key5) {
				switch (menuIndex) {
				case 0: 
					new ProfileScreen();
					break;
				case 1:
					new ChallengeScreen();
					break;
				case 2:
					new HistoryScreen();
					break;
				case 3:
					new TosScreen();
					break;
				default:
					break;
				}
			}
		});
	}

}
