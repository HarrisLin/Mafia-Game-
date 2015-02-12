package GameEngine;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.jmx.snmp.Enumerated;

import Character.CharacterFactory;
import Enumerators.Roles;

public class DatabaseManager {

	public static final String DATABASE_PATH = "mafia_data.db";

	/**
	 * Initializes the database.
	 * WARNING: This will delete any previous database created by the game!
	 */
	public static void init() {
		Connection c = null;
		Statement stmt = null;
		Path db_path = FileSystems.getDefault().getPath(DATABASE_PATH);
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
			String sql =	"CREATE TABLE DATA " +
							"(NAME			TEXT	PRIMARY KEY	NOT NULL," +
							" ROLE			TEXT				NOT NULL," +
							" SIDE			TEXT				NOT NULL," +
							" ALIVE			INTEGER				NOT NULL," +
							" TARGETS		TEXT						," +
							" LYNCH_TARGET	TEXT						," +
							" LAST_WILL		TEXT						," +
							" DOUSED		INTEGER				NOT NULL," +
							" ROLE_INFO		TEXT					   );";
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
			String sql = SqlInsertString(character);
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
				// Get our data from the SQL database
				String role_string = rs.getString("role");
				String side_string = rs.getString("side");
				boolean alive_bool = (rs.getInt("alive") == 1);
				String targets_string = rs.getString("targets");
				String lynch_string = rs.getString("lynch_target");
				String last_will_string = rs.getString("last_will");
				boolean doused_bool = (rs.getInt("doused") == 1);
				String role_info = rs.getString("role_info");

				// translate our SQL data into actual data
				Roles role = Roles.valueOf(Roles.class, role_string);
				imported_character = CharacterFactory.makeCharacter(role, player);
				if (alive_bool == false) {
					imported_character.kill();
				}
				if (targets_string.equals("NULL") == false) {
					imported_character.setTarget(getTargetsFromString(targets_string));
				}
				if (last_will_string.equals("NULL") == false ){
					imported_character.updateLastWill(last_will_string);
				}
				if (doused_bool == true) {
					imported_character.douse();
				}
				imported_character.importDatabaseRoleInfo(role_info);
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
	 * @return
	 */
	protected static String SqlInsertString(Character character) {
		return "INSERT INTO DATA " +
				"(NAME,ROLE,SIDE,ALIVE,TARGETS,LYNCH_TARGET,LAST_WILL,DOUSED,ROLE_INFO) VALUES ('" + 
				character.getPlayer().getName() + "', '" +				// NAME
				character.getRole().name() + "', '" +					// ROLE
				character.getSide().toString() + "', '" +				// SIDE
				(character.checkAlive()?1:0) + "', '" +					// ALIVE
				concatenateTargets(character.getTargets()) + "', '"	+	// TARGETS
				getCharacterName(character.getLynchTarget()) + "', '" +	// LYNCH_TARGET
				character.getLastWill() + "', '" +						// LAST_WILL
				(character.isDoused()?1:0) + "', '"  +					// DOUSED
				character.getDatabaseRoleInfoString() +					// ROLE_INFO
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
	 * Converts a SQL string of targets into a list of Players.
	 * The SQL string is composed of all target names separated
	 * by "##".
	 *
	 * For example, "Derek##Harris##Connie##Andy##Nathan"
	 *
	 * If a player in the string is not registered in the game,
	 * that player is ignored and the list of targets will not
	 * include that player.
	 * @param string the SQL string of targets made
	 * @return a list of targets
	 */
	protected static List<Player> getTargetsFromString(String string) {
		ArrayList<Player> targets = new ArrayList<Player>();
		if (string.equals("NULL")) {
			return targets;
		}
		String targets_string[] = string.split("##");
		for (int k = 0; k < targets_string.length; k++) {
			try {
				targets.add(Player.get(targets_string[k]));
			} catch (CannotGetPlayerException e) {
				// skip this player, the player is not registered
			}
		}
		return targets;
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
