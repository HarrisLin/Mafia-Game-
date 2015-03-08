package GameEngine;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
			boolean result = DatabaseManager.addData(p, c1);
			if (!result) {fail("Could not addData to an empty database.");}

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
			boolean result = DatabaseManager.addData(c1);
			if (!result) {fail("Could not addData to an empty database.");}
			
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
			boolean result = DatabaseManager.addData(p, c1);
			if (!result) {fail("Could not addData to an empty database.");}

			c2 = DatabaseManager.getData(Player.get("Derek"));

			assertEquals(c1.getPlayer(), c2.getPlayer());
			assertEquals(c1.getRole(), c2.getRole());
			assertEquals(c1.getSide(), c2.getSide());
			assertEquals(c1.checkAlive(), c2.checkAlive());
			assertEquals(c1.getTargets(), c2.getTargets());
			assertEquals(c1.getLynchTarget(), c2.getLynchTarget());
			assertEquals(c1.isDoused(), c2.isDoused());
			
			c1.douse();
			result = DatabaseManager.addData(p, c1);
			if (!result) {fail("Could not addData to a non-empty database.");}
			
			c2 = DatabaseManager.getData(Player.get("Derek"));

			assertEquals(c1.getPlayer(), c2.getPlayer());
			assertEquals(c1.getRole(), c2.getRole());
			assertEquals(c1.getSide(), c2.getSide());
			assertEquals(c1.checkAlive(), c2.checkAlive());
			assertEquals(c1.getTargets(), c2.getTargets());
			assertEquals(c1.getLynchTarget(), c2.getLynchTarget());
			assertEquals(c1.isDoused(), c2.isDoused());
			
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
			boolean result = DatabaseManager.addData(c1);
			if (!result) {fail("Could not addData to an empty database.");}

			c1.douse();
			result = DatabaseManager.addData(c1);
			if (!result) {fail("Could not addData to a non-empty database.");}
			
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
			GameEngine.assignCharacter(Player.get("Chelsea"), new Godfather(Player.get("Chelsea")));
			GameEngine.assignCharacter(Player.get("Daniel"), new Mafioso(Player.get("Daniel")));
			GameEngine.assignCharacter(Player.get("Kaibo"), new Investigator(Player.get("Kaibo")));
		} catch (CannotGetPlayerException e1) {
			e1.printStackTrace();
			fail();
		}
		
		DatabaseManager.init();
		
		try {
			
			//////////////////////////
			// Write to the database
			//////////////////////////
			
			// Add Harris to the database
			Player p1 = Player.get("Harris");
			harris1 = GameEngine.getCharacter(Player.get("Harris"));
			boolean result = DatabaseManager.addData(harris1);
			if (!result) {fail("Could not addData to an empty database.");}
			
			// Add Chelsea to the database
			Player p2 = Player.get("Chelsea");
			chelsea1 = GameEngine.getCharacter(Player.get("Chelsea"));
			result = DatabaseManager.addData(chelsea1);
			if (!result) {fail("Could not addData to an empty database.");}
	
			// Add Daniel to the database
			Player p3 = Player.get("Daniel");
			daniel1 = GameEngine.getCharacter(Player.get("Daniel"));
			result = DatabaseManager.addData(daniel1);
			if (!result) {fail("Could not addData to an empty database.");}
			
			// Add Kaibo to the database
			Player p4 = Player.get("Kaibo");
			kaibo1 = GameEngine.getCharacter(Player.get("Kaibo"));
			result = DatabaseManager.addData(kaibo1);
			if (!result) {fail("Could not addData to an empty database.");}
						
			//////////////////////////
			// Read from the database
			//////////////////////////
			
			// Read Chelsea from the database
			chelsea2 = DatabaseManager.getData(Player.get("Chelsea"));

			assertEquals(chelsea1.getPlayer(), chelsea2.getPlayer());
			assertEquals(chelsea1.getRole(), chelsea2.getRole());
			assertEquals(chelsea1.getSide(), chelsea2.getSide());
			assertEquals(chelsea1.checkAlive(), chelsea2.checkAlive());
			assertEquals(chelsea1.getTargets(), chelsea2.getTargets());
			assertEquals(chelsea1.getLynchTarget(), chelsea2.getLynchTarget());
			assertEquals(chelsea1.isDoused(), chelsea2.isDoused());
			
			// Read Harris from the database
			harris2 = DatabaseManager.getData(Player.get("Harris"));

			assertEquals(harris1.getPlayer(), harris2.getPlayer());
			assertEquals(harris1.getRole(), harris2.getRole());
			assertEquals(harris1.getSide(), harris2.getSide());
			assertEquals(harris1.checkAlive(), harris2.checkAlive());
			assertEquals(harris1.getTargets(), harris2.getTargets());
			assertEquals(harris1.getLynchTarget(), harris2.getLynchTarget());
			assertEquals(harris1.isDoused(), harris2.isDoused());
			
			// Read Kaibo from the database
			kaibo2 = DatabaseManager.getData(Player.get("Kaibo"));

			assertEquals(kaibo1.getPlayer(), kaibo2.getPlayer());
			assertEquals(kaibo1.getRole(), kaibo2.getRole());
			assertEquals(kaibo1.getSide(), kaibo2.getSide());
			assertEquals(kaibo1.checkAlive(), kaibo2.checkAlive());
			assertEquals(kaibo1.getTargets(), kaibo2.getTargets());
			assertEquals(kaibo1.getLynchTarget(), kaibo2.getLynchTarget());
			assertEquals(kaibo1.isDoused(), kaibo2.isDoused());
			
			// Read Daniel from the database
			daniel2 = DatabaseManager.getData(Player.get("Daniel"));

			assertEquals(daniel1.getPlayer(), daniel2.getPlayer());
			assertEquals(daniel1.getRole(), daniel2.getRole());
			assertEquals(daniel1.getSide(), daniel2.getSide());
			assertEquals(daniel1.checkAlive(), daniel2.checkAlive());
			assertEquals(daniel1.getTargets(), daniel2.getTargets());
			assertEquals(daniel1.getLynchTarget(), daniel2.getLynchTarget());
			assertEquals(daniel1.isDoused(), daniel2.isDoused());
		} catch (Exception e) {
			fail("Something went wrong.");
		}		
	}
	
	@Test
	public void test_initReadAndWriteMultiplePlayers_MultipleReadWrites() {
		Character harris1, harris2, chelsea1, chelsea2, daniel1, daniel2, kaibo1, kaibo2;
		
		GameEngine.registerPlayer("Harris");
		GameEngine.registerPlayer("Chelsea");
		GameEngine.registerPlayer("Daniel");
		GameEngine.registerPlayer("Kaibo");
		
		try {
			GameEngine.assignCharacter(Player.get("Harris"), new Investigator(Player.get("Harris")));
			GameEngine.assignCharacter(Player.get("Chelsea"), new Godfather(Player.get("Chelsea")));
			GameEngine.assignCharacter(Player.get("Daniel"), new Mafioso(Player.get("Daniel")));
			GameEngine.assignCharacter(Player.get("Kaibo"), new Investigator(Player.get("Kaibo")));
		} catch (CannotGetPlayerException e1) {
			e1.printStackTrace();
			fail();
		}
		
		DatabaseManager.init();
		
		try {
			
			//////////////////////////
			// Write to the database
			//////////////////////////
			
			// Add Harris to the database
			Player p1 = Player.get("Harris");
			harris1 = GameEngine.getCharacter(Player.get("Harris"));
			boolean result = DatabaseManager.addData(harris1);
			if (!result) {fail("Could not addData to an empty database.");}

			harris1.douse();
			result = DatabaseManager.addData(harris1);
			if (!result) {fail("Could not addData to a non-empty database.");}
			
			// Add Chelsea to the database
			Player p2 = Player.get("Chelsea");
			chelsea1 = GameEngine.getCharacter(Player.get("Chelsea"));
			result = DatabaseManager.addData(chelsea1);
			if (!result) {fail("Could not addData to an empty database.");}

			chelsea1.douse();
			result = DatabaseManager.addData(chelsea1);
			if (!result) {fail("Could not addData to a non-empty database.");}
			
			// Add Daniel to the database
			Player p3 = Player.get("Daniel");
			daniel1 = GameEngine.getCharacter(Player.get("Daniel"));
			result = DatabaseManager.addData(daniel1);
			if (!result) {fail("Could not addData to an empty database.");}

			daniel1.douse();
			result = DatabaseManager.addData(daniel1);
			if (!result) {fail("Could not addData to a non-empty database.");}
			
			// Add Kaibo to the database
			Player p4 = Player.get("Kaibo");
			kaibo1 = GameEngine.getCharacter(Player.get("Kaibo"));
			result = DatabaseManager.addData(kaibo1);
			if (!result) {fail("Could not addData to an empty database.");}

			kaibo1.douse();
			result = DatabaseManager.addData(kaibo1);
			if (!result) {fail("Could not addData to a non-empty database.");}
			
			//////////////////////////
			// Read from the database
			//////////////////////////
			
			// Read Chelsea from the database
			chelsea2 = DatabaseManager.getData(Player.get("Chelsea"));

			assertEquals(chelsea1.getPlayer(), chelsea2.getPlayer());
			assertEquals(chelsea1.getRole(), chelsea2.getRole());
			assertEquals(chelsea1.getSide(), chelsea2.getSide());
			assertEquals(chelsea1.checkAlive(), chelsea2.checkAlive());
			assertEquals(chelsea1.getTargets(), chelsea2.getTargets());
			assertEquals(chelsea1.getLynchTarget(), chelsea2.getLynchTarget());
			assertEquals(chelsea1.isDoused(), chelsea2.isDoused());
			
			// Read Harris from the database
			harris2 = DatabaseManager.getData(Player.get("Harris"));

			assertEquals(harris1.getPlayer(), harris2.getPlayer());
			assertEquals(harris1.getRole(), harris2.getRole());
			assertEquals(harris1.getSide(), harris2.getSide());
			assertEquals(harris1.checkAlive(), harris2.checkAlive());
			assertEquals(harris1.getTargets(), harris2.getTargets());
			assertEquals(harris1.getLynchTarget(), harris2.getLynchTarget());
			assertEquals(harris1.isDoused(), harris2.isDoused());
			
			// Read Kaibo from the database
			kaibo2 = DatabaseManager.getData(Player.get("Kaibo"));

			assertEquals(kaibo1.getPlayer(), kaibo2.getPlayer());
			assertEquals(kaibo1.getRole(), kaibo2.getRole());
			assertEquals(kaibo1.getSide(), kaibo2.getSide());
			assertEquals(kaibo1.checkAlive(), kaibo2.checkAlive());
			assertEquals(kaibo1.getTargets(), kaibo2.getTargets());
			assertEquals(kaibo1.getLynchTarget(), kaibo2.getLynchTarget());
			assertEquals(kaibo1.isDoused(), kaibo2.isDoused());
			
			// Read Daniel from the database
			daniel2 = DatabaseManager.getData(Player.get("Daniel"));

			assertEquals(daniel1.getPlayer(), daniel2.getPlayer());
			assertEquals(daniel1.getRole(), daniel2.getRole());
			assertEquals(daniel1.getSide(), daniel2.getSide());
			assertEquals(daniel1.checkAlive(), daniel2.checkAlive());
			assertEquals(daniel1.getTargets(), daniel2.getTargets());
			assertEquals(daniel1.getLynchTarget(), daniel2.getLynchTarget());
			assertEquals(daniel1.isDoused(), daniel2.isDoused());
			
			
			//////////////////////////
			// Write to the database again
			//////////////////////////
			
			harris1.douse();
			result = DatabaseManager.addData(harris1);
			if (!result) {fail("Could not addData to a non-empty database.");}

			ArrayList<Player> chelsea_targets = new ArrayList<Player>();
			chelsea_targets.add(Player.get("Harris"));
			chelsea1.setTarget(chelsea_targets);
			result = DatabaseManager.addData(chelsea1);
			if (!result) {fail("Could not addData to a non-empty database.");}

			daniel1.updateLastWill("I love Derek Chan.");
			result = DatabaseManager.addData(daniel1);
			if (!result) {fail("Could not addData to a non-empty database.");}
			
			//////////////////////////
			// Read from the database again
			//////////////////////////
			
			// Read Chelsea from the database
			chelsea2 = DatabaseManager.getData(Player.get("Chelsea"));

			assertEquals(chelsea1.getPlayer(), chelsea2.getPlayer());
			assertEquals(chelsea1.getRole(), chelsea2.getRole());
			assertEquals(chelsea1.getSide(), chelsea2.getSide());
			assertEquals(chelsea1.checkAlive(), chelsea2.checkAlive());
			assertEquals(chelsea1.getTargets(), chelsea2.getTargets());
			assertEquals(chelsea1.getLynchTarget(), chelsea2.getLynchTarget());
			assertEquals(chelsea1.isDoused(), chelsea2.isDoused());
			
			// Read Harris from the database
			harris2 = DatabaseManager.getData(Player.get("Harris"));

			assertEquals(harris1.getPlayer(), harris2.getPlayer());
			assertEquals(harris1.getRole(), harris2.getRole());
			assertEquals(harris1.getSide(), harris2.getSide());
			assertEquals(harris1.checkAlive(), harris2.checkAlive());
			assertEquals(harris1.getTargets(), harris2.getTargets());
			assertEquals(harris1.getLynchTarget(), harris2.getLynchTarget());
			assertEquals(harris1.isDoused(), harris2.isDoused());
			
			// Read Kaibo from the database
			kaibo2 = DatabaseManager.getData(Player.get("Kaibo"));

			assertEquals(kaibo1.getPlayer(), kaibo2.getPlayer());
			assertEquals(kaibo1.getRole(), kaibo2.getRole());
			assertEquals(kaibo1.getSide(), kaibo2.getSide());
			assertEquals(kaibo1.checkAlive(), kaibo2.checkAlive());
			assertEquals(kaibo1.getTargets(), kaibo2.getTargets());
			assertEquals(kaibo1.getLynchTarget(), kaibo2.getLynchTarget());
			assertEquals(kaibo1.isDoused(), kaibo2.isDoused());
			
			// Read Daniel from the database
			daniel2 = DatabaseManager.getData(Player.get("Daniel"));

			assertEquals(daniel1.getPlayer(), daniel2.getPlayer());
			assertEquals(daniel1.getRole(), daniel2.getRole());
			assertEquals(daniel1.getSide(), daniel2.getSide());
			assertEquals(daniel1.checkAlive(), daniel2.checkAlive());
			assertEquals(daniel1.getTargets(), daniel2.getTargets());
			assertEquals(daniel1.getLynchTarget(), daniel2.getLynchTarget());
			assertEquals(daniel1.isDoused(), daniel2.isDoused());

			
		} catch (Exception e) {
			fail("Something went wrong.");
		}		
	}
	
	@Test
	public void test_importPlayers() {
		Character harris1, harris2, chelsea1, chelsea2, daniel1, daniel2, kaibo1, kaibo2;

		GameEngine.registerPlayer("Harris");
		GameEngine.registerPlayer("Chelsea");
		GameEngine.registerPlayer("Daniel");
		GameEngine.registerPlayer("Kaibo");

		try {
			GameEngine.assignCharacter(Player.get("Harris"), new Investigator(Player.get("Harris")));
			GameEngine.assignCharacter(Player.get("Chelsea"), new Godfather(Player.get("Chelsea")));
			GameEngine.assignCharacter(Player.get("Daniel"), new Mafioso(Player.get("Daniel")));
			GameEngine.assignCharacter(Player.get("Kaibo"), new Investigator(Player.get("Kaibo")));
		} catch (CannotGetPlayerException e1) {
			e1.printStackTrace();
			fail();
		}

		DatabaseManager.init();

		try {

			//////////////////////////
			// Write to the database
			//////////////////////////

			// Add Harris to the database
			Player p1 = Player.get("Harris");
			harris1 = GameEngine.getCharacter(Player.get("Harris"));
			boolean result = DatabaseManager.addData(harris1);
			if (!result) {fail("Could not addData to an empty database.");}

			harris1.douse();
			result = DatabaseManager.addData(harris1);
			if (!result) {fail("Could not addData to a non-empty database.");}

			// Add Chelsea to the database
			Player p2 = Player.get("Chelsea");
			chelsea1 = GameEngine.getCharacter(Player.get("Chelsea"));
			result = DatabaseManager.addData(chelsea1);
			if (!result) {fail("Could not addData to an empty database.");}

			chelsea1.douse();
			result = DatabaseManager.addData(chelsea1);
			if (!result) {fail("Could not addData to a non-empty database.");}

			// Add Daniel to the database
			Player p3 = Player.get("Daniel");
			daniel1 = GameEngine.getCharacter(Player.get("Daniel"));
			result = DatabaseManager.addData(daniel1);
			if (!result) {fail("Could not addData to an empty database.");}

			daniel1.douse();
			result = DatabaseManager.addData(daniel1);
			if (!result) {fail("Could not addData to a non-empty database.");}

			// Add Kaibo to the database
			Player p4 = Player.get("Kaibo");
			kaibo1 = GameEngine.getCharacter(Player.get("Kaibo"));
			result = DatabaseManager.addData(kaibo1);
			if (!result) {fail("Could not addData to an empty database.");}

			kaibo1.douse();
			result = DatabaseManager.addData(kaibo1);
			if (!result) {fail("Could not addData to a non-empty database.");}
		} catch (Exception e) {
			fail("Something went wrong.");
		}
		
		//////////////////////////
		// Unregister all Players
		//////////////////////////
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
			Player.get("Daniel");
			fail("Player was not unregistered");
		} catch (CannotGetPlayerException e) {}
		try {
			Player.get("Kaibo");
			fail("Player was not unregistered");
		} catch (CannotGetPlayerException e) {}

		//////////////////////////
		// Reimport all Players
		//////////////////////////
		DatabaseManager.importPlayers();
		
		try {
			Player.get("Harris");
		} catch (CannotGetPlayerException e) {
			fail("Player was not reimported");
		}
		try {
			Player.get("Chelsea");
		} catch (CannotGetPlayerException e) {
			fail("Player was not reimported");
		}
		try {
			Player.get("Daniel");
		} catch (CannotGetPlayerException e) {
			fail("Player was not reimported");
		}
		try {
			Player.get("Kaibo");
		} catch (CannotGetPlayerException e) {
			fail("Player was not reimported");
		}
	}
		

}
