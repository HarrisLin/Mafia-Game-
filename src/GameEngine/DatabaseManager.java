package GameEngine;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

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
							"(NAME	TEXT	PRIMARY KEY	NOT NULL," +
							" ROLE	TEXT				NOT NULL," +
							" SIDE	TEXT				NOT NULL);";
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
			String sql = "INSERT INTO DATA (NAME,ROLE,SIDE) " +
						 "VALUES ('" + player.getName() + "', '" +
						 		   character.getRole().name() + "', '" +
						 		   character.getSide().toString() + "');";
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

}
