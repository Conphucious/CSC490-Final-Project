package screen;

import java.sql.SQLException;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.FinishedChallenge;
import system.Configuration;
import system.Database;
import system.Window;

public class HistoryScreen extends Screen {

	public HistoryScreen() {
		super(new Text[] { new Text(""), new Text(""), new Text(""), new Text("") }, new Text("Go back"));
		content();
		setMenu(0);
		setAction();
		
		checkConnectivity();
	}

	private void content() {
		Window.getSp().getChildren().clear();
		HBox mainPane = new HBox(10);
		VBox box = new VBox(10);

		List<FinishedChallenge> x = null;
		try {
			x = Database.selectFromFinishedChallenge();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Text text = null;

		if (x.isEmpty()) {
			text = new Text("No challenges have\nbeen completed yet.");
		} else {
			String info = "";
			
			for (FinishedChallenge y : x) {
				info += "ID: " + y.getId() + "\t\tPts: " + y.getPts() + "\n" +
						"Name: " + y.getChallenge_name() + "\n" +
						"Detail: " + y.getDescription() + "\n" +
						"Start Date: " + y.getInitial_date() + "\n" +
						"Complete Date: " + y.getComplete_date() + "\n\n";
			}
			
			text = new Text(info);
		}
		
		Text header = new Text("\nLast 3 Challenges");
		header.setFill(Color.WHITE);
		header.setFont(new Font("Verdana", 25));
		text.setFill(Color.WHITE);
		text.setFont(new Font("Verdana", 12));


		box.setAlignment(Pos.CENTER);
		mainPane.setAlignment(Pos.CENTER);
		box.getChildren().addAll(header, text);
		mainPane.getChildren().add(box);

		Window.getSp().getChildren().add(mainPane);
	}

	private void setAction() {
		Window.getStage().getScene().setOnKeyPressed(event -> {
			if (event.getCode() == Configuration.key5)
				new MainScreen(2);
		});
	}

}
