package screen;

import java.sql.SQLException;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.User;
import system.Configuration;
import system.Database;
import system.Window;

public class ProfileScreen extends Screen {

	public ProfileScreen() {
		super(new Text[] { new Text(""), new Text(""), new Text(""), new Text("") }, new Text("Go back"));
		content();
		setMenu(0);
		setAction();
	}

	private void content() {
		Window.getSp().getChildren().clear();
		HBox mainPane = new HBox(10);
		VBox box = new VBox(10);

		User x = null;
		try {
			x = Database.selectFromUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String info = "\nUsername: " + x.getUsername() + " \n\nChallenges Completed: " + x.getCc()
				+ "\n\nTotal Challenge Pts: " + x.getPts() + " \n\n\nURL: \npnguyen3.com/user.php?id=" + x.getId();

		Text header = new Text("My Profile");
		header.setFill(Color.WHITE);
		header.setFont(new Font("Verdana", 25));

		Text text = new Text(info);
		text.setFill(Color.WHITE);
		text.setFont(new Font("Verdana", 12));

		box.setAlignment(Pos.CENTER_LEFT);
		mainPane.setAlignment(Pos.CENTER);
		box.getChildren().addAll(header, text);
		mainPane.getChildren().add(box);

		Window.getSp().getChildren().add(mainPane);
	}

	private void setAction() {
		Window.getStage().getScene().setOnKeyPressed(event -> {
			if (event.getCode() == Configuration.key5)
				new MainScreen(0);
		});
	}

}
