package GameEngine;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Character.Godfather;
import Character.Investigator;
import Character.Mafioso;
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
			System.out.println("Adding data...");
			boolean result = DatabaseManager.addData(p, c1);
			if (!result) {fail("Could not addData to an empty database.");}
			
			System.out.println("Retrieving data...");
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

	@Test
	public void test_initReadAndWrite_OverloadedMethodCall() {
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
			System.out.println("Adding data...");
			boolean result = DatabaseManager.addData(c1);
			if (!result) {fail("Could not addData to an empty database.");}
			
			System.out.println("Retrieving data...");
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
	
	@Test
	public void test_initReadAndWriteTwice() {
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
			System.out.println("Adding data...");
			boolean result = DatabaseManager.addData(p, c1);
			if (!result) {fail("Could not addData to an empty database.");}

			System.out.println("Updating data...");
			c1.douse();
			result = DatabaseManager.addData(p, c1);
			if (!result) {fail("Could not addData to a non-empty database.");}
			
			System.out.println("Retrieving data...");
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
	
	@Test
	public void test_initReadAndWriteTwice_OverloadedMethodCall() {
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
			System.out.println("Adding data...");
			boolean result = DatabaseManager.addData(c1);
			if (!result) {fail("Could not addData to an empty database.");}

			System.out.println("Updating data...");
			c1.douse();
			result = DatabaseManager.addData(c1);
			if (!result) {fail("Could not addData to a non-empty database.");}
			
			System.out.println("Retrieving data...");
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
	
	@Test
	public void test_initReadAndWriteMultiplePlayers() {
		Character harris1, harris2, chelsea1, chelsea2, daniel1, daniel2, kaibo1, kaibo2;
		
		GameEngine.registerPlayer("Harris");
		GameEngine.registerPlayer("Chelsea");
		GameEngine.registerPlayer("Daniel");
		GameEngine.registerPlayer("Kaibo");
		
		try {
			GameEngine.assignCharacter(Player.get("Harris"), new Investigator(Player.get("Harris")));
			GameEngine.assignCharacter(Player.get("Harris"), new Godfather(Player.get("Harris")));
			GameEngine.assignCharacter(Player.get("Harris"), new Mafioso(Player.get("Harris")));
			GameEngine.assignCharacter(Player.get("Harris"), new Investigator(Player.get("Harris")));
		} catch (CannotGetPlayerException e1) {
			e1.printStackTrace();
			fail();
		}
		
		
	}
}
