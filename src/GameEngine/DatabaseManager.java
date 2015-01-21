package GameEngine;

import java.sql.*;

public class DatabaseManager {
	
	/**
	 * Initializes the database
	 */
	public static void init() {
		Connection c = null;
		Statement stmt = null;
		try {
			System.out.println("Class.forName");
			Class.forName("org.sqlite.JDBC");
			System.out.println("Get connection...");
			c = DriverManager.getConnection("jdbc:sqlite:mafia_data.db");
			System.out.println("Connected.");
			stmt = c.createStatement();
			String sql = 	"CREATE TABLE DATA " +
							"(NAME	TEXT	PRIMARY KEY	NOT NULL," +
							" ROLE	TEXT				NOT NULL," +
							" SIDE	TEXT				NOT NULL);";
			System.out.println("SQL statement: " + sql);
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
			System.out.println("Opened database successfully!");
			
			stmt = c.createStatement();
			String sql = "INSERT INTO DATA (NAME,ROLE,SIDE) " +
						 "VALUES ('" + player.getName() + "', '" +
						 		   character.getRole().name() + "', '" +
						 		   character.getSide().toString() + "');";
			System.out.println("SQL: " + sql);
			stmt.executeUpdate(sql);
			
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		System.out.println("Record created successfully!");
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
			System.out.println("Opened database successfully!");
			
			stmt = c.createStatement();
			String query = "SELECT * FROM data WHERE name = '" + player.getName() + "';";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("SQL Query: " + query);
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
		System.out.println("Record read successfully!");
		return imported_character;
	}

}
