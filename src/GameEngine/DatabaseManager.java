package GameEngine;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;

public class DatabaseManager {
	
	/**
	 * Initializes the database.
	 * WARNING: This will delete any previous database created by the game!
	 */
	public static void init() {
		Connection c = null;
		Statement stmt = null;
		Path db_path = FileSystems.getDefault().getPath("mafia_data.db");
		try {
			Files.deleteIfExists(db_path);
			System.out.println("Erased old database.");
		} catch (IOException e1) {
			System.out.println("Unable to delete old database. Continuing...");
		}
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:mafia_data.db");
			stmt = c.createStatement();
			String sql = 	"CREATE TABLE DATA " +
							"(NAME			TEXT	PRIMARY KEY	NOT NULL," +
							" ROLE			TEXT				NOT NULL," +
							" SIDE			TEXT				NOT NULL," +
							" ALIVE			INTEGER				NOT NULL," +
							" TARGETS		TEXT						," +
							" LYNCH_TARGET	TEXT						," +
							" LAST_WILL		TEXT						," +
							" DOUSED		INTEGER				NOT NULL);";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			return;
		}
	}
	
	/**
	 * Add player data to the database
	 */
	public static boolean addData(Player player, Character character) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:mafia_data.db");
			c.setAutoCommit(false);
			
			stmt = c.createStatement();
			String sql = SqlInsertString(character, player);
			stmt.executeUpdate(sql);
			
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Get character data from the database
	 */
	public static Character getData(Player player) {
		Connection c = null;
		Statement stmt = null;
		Character imported_character = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:mafia_data.db");
			c.setAutoCommit(false);
			
			stmt = c.createStatement();
			String query = "SELECT * FROM data WHERE name = '" + player.getName() + "';";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				String role_string = rs.getString("role");
				String side_string = rs.getString("side");
				System.out.println("Role string: " + role_string);
				System.out.println("Side string: " + side_string);
				
				//imported_character = new Character(Enumerators.Roles.valueOf(role_string), player);
			} else {
				System.out.println("Failed to retrieve query.");
			}
			
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}
		return imported_character;
	}
	
	/**
	 * Generates a SQL string used for SQL INSERT
	 * @param character Character object to be inserted into SQL database
	 * @param player Player object to be inserted into SQL database
	 * @return
	 */
	protected static String SqlInsertString(Character character, Player player) {
		return "INSERT INTO DATA " +
				"(NAME,ROLE,SIDE,ALIVE,TARGETS,LYNCH_TARGET,LAST_WILL,DOUSED) VALUES ('" + 
				player.getName() + "', '" +								// NAME
				character.getRole().name() + "', '" +					// ROLE
				character.getSide().toString() + "', '" +				// SIDE
				(character.checkAlive()?1:0) + "', '" +					// ALIVE
				concatenateTargets(character.getTargets()) + "', '"	+	// TARGETS
				getCharacterName(character.getLynchTarget()) + "', '" +	// LYNCH_TARGET
				character.getLastWill() + "', '" +						// LAST_WILL
				(character.isDoused()?1:0) +							// DOUSED
				"');";
	}
	
	/**
	 * Concatenates a list of Player targets into a string with ## separating each entry
	 * @param targets the list of targets
	 * @return concatenated string of targets
	 */
	protected static String concatenateTargets(List<Player> targets) {
		if (targets == null || targets.isEmpty()) {
			return "NULL";
		}
		String concatenated_targets = targets.get(0).getName();
		for (int k = 1; k < targets.size(); k++) {
			concatenated_targets += "##"+targets.get(k).getName();
		}
		return concatenated_targets;
	}
	
	/**
	 * SQL statements need to process null values as "NULL". A null character
	 * will blow up the DatabaseManager
	 * @param player the Player to nullcheck
	 * @return "NULL" if null, Player name otherwise
	 */
	private static String getCharacterName(Player player) {
		if (player == null) {
			return "NULL";
		} else {
			return player.getName();
		}
	}

}
