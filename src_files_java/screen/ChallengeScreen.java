package screen;

import java.sql.SQLException;
import java.util.List;

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

public class ChallengeScreen extends Screen {

	private List<Challenge> cList;
	private Text text = null;
	private int index = 0;

	public ChallengeScreen() {
		super(null, null);
		
		try {
			cList = Database.selectFromAvailableChallenge();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (cList.size() == 0) {
			setMenuText(new Text[] { new Text("           "), new Text("        "), new Text("          "), new Text("           ") });
			setTopText(new Text("Exit"));
		} else if (cList.size() == 1) {
			setMenuText(new Text[] { new Text("  Accept       "), new Text("        "), new Text("          "), new Text("        Exit   ") });
			setTopText(new Text("Confirm"));
		} else if (cList.size() > 1) {
			setMenuText(new Text[] { new Text("  Accept   "), new Text("           "), new Text("    Next "), new Text("    Exit   ") });
			setTopText(new Text("Confirm"));
		}
		
		content();
		setMenu(0);
		setAction();

		checkConnectivity();
	}

	private void content() {
		Window.getSp().getChildren().clear();
		HBox mainPane = new HBox(10);
		VBox box = new VBox(10);

		if (cList.size() == 0)
			text = new Text("No challenges are left!");
		else
			text = new Text(cList.get(index).toString());

		Text header = new Text("Available Challenges\n");
		header.setFill(Color.WHITE);
		header.setFont(new Font("Verdana", 20));
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
			if (cList.size() == 0) {
				if (event.getCode() == Configuration.key5)
					new MainScreen(1);
			} else if (cList.size() == 1) {
				if (event.getCode() == Configuration.key1)
					setCurrentMenu(0);
				else if (event.getCode() == Configuration.key4)
					setCurrentMenu(3);
				else if (event.getCode() == Configuration.key5) {
					switch (menuIndex) {
					case 0:
						break;
					case 3:
						new MainScreen(1);
						break;
					}
				}
			} else {
				System.out.println(cList.get(index).getId() + "_" + index);
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
						new AcceptChallengeScreen(cList.get(index).getId());
						break;
					case 1:
						if (index <= cList.size() - 1 && index > 0) {
							index--;
							System.out.println(index);
							text.setText(cList.get(index).toString());
						}
						break;
					case 2:
						if (index < cList.size() - 1) {
							index++;
							System.out.println(index);
							text.setText(cList.get(index).toString());
						}
						break;
					case 3:
						new MainScreen(1);
						break;
					}
				}
				
				if (index == 0) {
					getMenuText()[1].setText("           ");
					getMenuText()[2].setText("     Next ");
				} else if (index == cList.size() - 1) {
					getMenuText()[2].setText("          ");
					getMenuText()[1].setText(" Previous");
				} else {
					getMenuText()[1].setText(" Previous");
					getMenuText()[2].setText("     Next ");
				}
			}
		});
	}

}
