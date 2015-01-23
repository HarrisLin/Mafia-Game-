package GameEngine;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Character.Investigator;
import GameEngine.GameEngine;
import GameEngine.DatabaseManager;

public class DatabaseManager_Test {

	@Before
	public void setup() {
		Path db_path = FileSystems.getDefault().getPath(DatabaseManager.DATABASE_PATH);
		try {
			Files.deleteIfExists(db_path);
		} catch (IOException e1) {}
	}

	@After
	public void teardown() {
		Path db_path = FileSystems.getDefault().getPath(DatabaseManager.DATABASE_PATH);
		try {
			Files.deleteIfExists(db_path);
		} catch (IOException e1) {}
	}

	@Test
	public void test_initReadAndWrite() {
		Character c1, c2;

		GameEngine.registerPlayer("Derek");

		try {
			GameEngine.assignCharacter(Player.get("Derek"), new Investigator(Player.get("Derek")));
		} catch (CannotGetPlayerException e1) {
			e1.printStackTrace();
		}

		DatabaseManager.init();

		try {
			Player p = Player.get("Derek");
			c1 = GameEngine.getCharacter(Player.get("Derek"));
			DatabaseManager.addData(p, c1);

			c2 = DatabaseManager.getData(Player.get("Derek"));

			assertEquals(c1.getPlayer(), c2.getPlayer());
			assertEquals(c1.getRole(), c2.getRole());
			assertEquals(c1.getSide(), c2.getSide());
			assertEquals(c1.checkAlive(), c2.checkAlive());
			assertEquals(c1.getTargets(), c2.getTargets());
			assertEquals(c1.getLynchTarget(), c2.getLynchTarget());
			assertEquals(c1.isDoused(), c2.isDoused());
		} catch (Exception e) {
			fail("Something went wrong.");
		}
	}
}
