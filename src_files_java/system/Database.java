package system;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Challenge;
import model.FinishedChallenge;
import model.User;

public class Database {

	public static User selectFromUser() throws SQLException {
		Connection dbConnection = getDBConnection();
		Statement statement = dbConnection.createStatement();
		ResultSet rs = statement.executeQuery(
				"SELECT user.id, username, reg_date, finished_challenge_id, challenge_id, proof_id, complete_date, pts FROM user \n"
						+ "JOIN user_challenge ON user_id = user.id\n"
						+ "JOIN finished_challenge ON user_challenge.finished_challenge_id = finished_challenge.id\n"
						+ "JOIN challenge ON finished_challenge.challenge_id = challenge.id\n"
						+ "WHERE username = 'Jimmy';");

		List<User> userList = new ArrayList<>();

		while (rs.next()) {
			int id = rs.getInt("ID");
			String username = rs.getString("USERNAME");
			int pts = rs.getInt("pts");
			Date date = rs.getDate("REG_DATE");
			userList.add(new User(id, username, -1, pts, date));
		}

		statement.close();
		dbConnection.close();

		int totalPts = 0;
		for (User x : userList)
			totalPts += x.getPts();

		return new User(userList.get(0).getId(), userList.get(0).getUsername(), userList.size(), totalPts,
				userList.get(0).getRegDate());
	}

	public static List<FinishedChallenge> selectFromFinishedChallenge() throws SQLException {
		Connection dbConnection = getDBConnection();
		Statement statement = dbConnection.createStatement();
		List<FinishedChallenge> cList = new ArrayList<>();

		ResultSet rs = statement.executeQuery(
				"SELECT challenge_id, finished_challenge.complete_date, challenge.name, challenge.pts, challenge.initial_date, challenge.description FROM user \n"
						+ "JOIN user_challenge ON user_id = user.id\n"
						+ "JOIN finished_challenge ON user_challenge.finished_challenge_id = finished_challenge.id\n"
						+ "JOIN challenge ON finished_challenge.challenge_id = challenge.id\n"
						+ "JOIN proof ON finished_challenge.proof_id = proof.id\n" + "WHERE username = 'Jimmy'");

		while (rs.next()) {
			int id = rs.getInt("CHALLENGE_ID");
			int pts = rs.getInt("PTS");
			String name = rs.getString("NAME");
			String desc = rs.getString("DESCRIPTION");
			Date iDate = rs.getDate("INITIAL_DATE");
			Date cDate = rs.getDate("COMPLETE_DATE");

			cList.add(new FinishedChallenge(id, pts, name, desc, iDate, cDate));
		}

		statement.close();
		dbConnection.close();
		return cList;
	}

	public static List<Challenge> selectFromAvailableChallenge() throws SQLException {
		Connection dbConnection = getDBConnection();
		Statement statement = dbConnection.createStatement();
		List<Challenge> cList = new ArrayList<>();

		ResultSet rs = statement.executeQuery("SELECT * FROM challenge "
				+ "WHERE NOT EXISTS (SELECT	* FROM finished_challenge "
				+ "JOIN user_challenge ON user_challenge.finished_challenge_id = finished_challenge.id "
				+ "JOIN user ON user.id = user_challenge.user_id "
				+ "WHERE challenge.id = finished_challenge.challenge_id AND user.username = 'Jimmy')");

		while (rs.next()) {
			int id = rs.getInt("ID");
			int pts = rs.getInt("PTS");
			String name = rs.getString("NAME");
			String desc = rs.getString("DESCRIPTION");
			Date date = rs.getDate("INITIAL_DATE");
			boolean pending = rs.getBoolean("IS_PENDING");

			cList.add(new Challenge(id, pts, name, desc, date, pending));
		}

		statement.close();
		dbConnection.close();
		return cList;
	}

	public static boolean isChallengeInsertedIntoDb(int challengeId) throws SQLException {
		Connection dbConnection = getDBConnection();
		Statement statement = dbConnection.createStatement();

		statement.executeUpdate("INSERT INTO finished_challenge (challenge_id, proof_id) VALUE (" + challengeId + ", 1);");
		
		ResultSet rs = statement.executeQuery("SELECT * FROM finished_challenge");
		int id = 0;
		while (rs.next()) {
			id++;
		}
		statement.executeUpdate("INSERT INTO user_challenge (user_id, finished_challenge_id) VALUE (1, " + id + ")");

		statement.close();
		dbConnection.close();
		return true;
	}

	public static Challenge selectFromAvailableChallenge(int ider) throws SQLException {
		Connection dbConnection = getDBConnection();
		Statement statement = dbConnection.createStatement();
		List<Challenge> cList = new ArrayList<>();

		ResultSet rs = statement.executeQuery("SELECT * FROM challenge");

		while (rs.next()) {
			int id = rs.getInt("ID");
			int pts = rs.getInt("PTS");
			String name = rs.getString("NAME");
			String desc = rs.getString("DESCRIPTION");
			Date date = rs.getDate("INITIAL_DATE");
			boolean pending = rs.getBoolean("IS_PENDING");

			cList.add(new Challenge(id, pts, name, desc, date, pending));
		}

		statement.close();
		dbConnection.close();
		
		if (ider < 0)
			return cList.get(0);
		
		return cList.get(ider);
	}

	public static List<Challenge> selectFromChallenge() throws SQLException {
		Connection dbConnection = getDBConnection();
		Statement statement = dbConnection.createStatement();
		List<Challenge> cList = new ArrayList<>();

		ResultSet rs = statement.executeQuery("SELECT * FROM challenge WHERE is_pending = FALSE");

		while (rs.next()) {
			int id = rs.getInt("ID");
			int pts = rs.getInt("PTS");
			String name = rs.getString("NAME");
			String desc = rs.getString("DESCRIPTION");
			Date date = rs.getDate("INITIAL_DATE");
			boolean pending = rs.getBoolean("IS_PENDING");

			cList.add(new Challenge(id, pts, name, desc, date, pending));
		}

		statement.close();
		dbConnection.close();
		return cList;
	}

	public static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(Configuration.DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(Configuration.DB_CONNECTION, Configuration.DB_USER,
					Configuration.DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

}
