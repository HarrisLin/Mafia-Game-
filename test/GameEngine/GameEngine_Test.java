package GameEngine;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Character.Character;
import Character.Godfather;
import Character.Investigator;
import Character.Mafioso;
import Resources.DatabaseManager;

public class GameEngine_Test {
	
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
	public void test_ImportExportCharacterMap() {
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Andrea");
		GameEngine.registerPlayer("Mitchell");
		GameEngine.registerPlayer("Harris");
		GameEngine.registerPlayer("Chelsea");
		
		try {
			GameEngine.assignCharacter(Player.get("Derek"), new Investigator(Player.get("Derek")));
			GameEngine.assignCharacter(Player.get("Harris"), new Investigator(Player.get("Harris")));
			GameEngine.assignCharacter(Player.get("Andrea"), new Godfather(Player.get("Andrea")));
			GameEngine.assignCharacter(Player.get("Mitchell"), new Mafioso(Player.get("Mitchell")));
			GameEngine.assignCharacter(Player.get("Chelsea"), new Investigator(Player.get("Chelsea")));
		} catch (CannotGetPlayerException e1) {
			e1.printStackTrace();
			fail();
		}
		
		Map<Player, Character> original_values = new HashMap<Player, Character>();
		try {
		original_values.put(Player.get("Derek"), GameEngine.getCharacter(Player.get("Derek")));
		original_values.put(Player.get("Harris"), GameEngine.getCharacter(Player.get("Harris")));
		original_values.put(Player.get("Andrea"), GameEngine.getCharacter(Player.get("Andrea")));
		original_values.put(Player.get("Mitchell"), GameEngine.getCharacter(Player.get("Mitchell")));
		original_values.put(Player.get("Chelsea"), GameEngine.getCharacter(Player.get("Chelsea")));
		} catch (CannotGetPlayerException e) {
			e.printStackTrace();
			fail();
		}
		
		DatabaseManager.init();
		GameEngine.exportPlayerCharacterMap();
		
		GameEngine.reset();
		
		try {
			Player.get("Harris");
			fail("Player was not unregistered");
		} catch (CannotGetPlayerException e) {}
		try {
			Player.get("Chelsea");
			fail("Player was not unregistered");
		} catch (CannotGetPlayerException e) {}
		try {
			Player.get("Derek");
			fail("Player was not unregistered");
		} catch (CannotGetPlayerException e) {}
		try {
			Player.get("Andrea");
			fail("Player was not unregistered");
		} catch (CannotGetPlayerException e) {}
		try {
			Player.get("Mitchell");
			fail("Player was not unregistered");
		} catch (CannotGetPlayerException e) {}
		
		GameEngine.importPlayerCharacterMap();
		
		try {
		assert(original_values.get(Player.get("Derek")).equals(GameEngine.getCharacter(Player.get("Derek"))));
		assert(original_values.get(Player.get("Harris")).equals(GameEngine.getCharacter(Player.get("Harris"))));
		assert(original_values.get(Player.get("Andrea")).equals(GameEngine.getCharacter(Player.get("Andrea"))));
		assert(original_values.get(Player.get("Mitchell")).equals(GameEngine.getCharacter(Player.get("Mitchell"))));
		assert(original_values.get(Player.get("Chelsea")).equals(GameEngine.getCharacter(Player.get("Chelsea"))));
		} catch (CannotGetPlayerException e) {
			e.printStackTrace();
			fail();
		}
	}
}
