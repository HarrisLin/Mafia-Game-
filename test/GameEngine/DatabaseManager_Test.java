package GameEngine;

import org.junit.Test;

import Character.Investigator;
import GameEngine.GameEngine;
import GameEngine.DatabaseManager;

public class DatabaseManager_Test {

	@Test
	public void test_initReadAndWrite() {
		GameEngine.registerPlayer("Derek");
		
		try {
			GameEngine.assignCharacter(Player.get("Derek"), new Investigator(Player.get("Derek")));
		} catch (CannotGetPlayerException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Initializing...");
		DatabaseManager.init();
		try {
			System.out.println("Getting player...");
			Player p = Player.get("Derek");
			System.out.println("Getting character...");
			Character c = GameEngine.getCharacter(Player.get("Derek"));
			System.out.println("Adding data...");
			DatabaseManager.addData(p, c);
			System.out.println("Retrieving data...");
			DatabaseManager.getData(Player.get("Derek"));
		} catch (Exception e) {	}
	}
}
