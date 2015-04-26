package GameEngine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import Enumerators.Roles;
import GameEngine.GameEngine;
import GameEngine.DatabaseManager;
import TestUtils.TestCharacter;

public class DatabaseManager_PrivateMethods_Test {

	@After
	public void teardown() {
		GameEngine.reset();
	}

	@Test
	public void test_concatenateTargets_emptyList() {
		ArrayList<Player> targets = new ArrayList<Player>();
		assertEquals("NULL", TestDatabaseManager.testConcatenateTargets(targets));
	}

	@Test
	public void test_concatenateTargets_nullList() {
		assertEquals("NULL", TestDatabaseManager.testConcatenateTargets(null));
	}

	@Test
	public void test_concatenateTargets_singleTarget() {
		GameEngine.registerPlayer("Derek");
		assertEquals("Derek", TestDatabaseManager.testConcatenateTargets(Player.listAllPlayers()));
	}

	@Test
	public void test_concatenateTargets_multipleTargets() {
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Harris");
		GameEngine.registerPlayer("Andy");
		assertEquals("Derek##Harris##Andy", TestDatabaseManager.testConcatenateTargets(Player.listAllPlayers()));
	}

	@Test
	public void test_SqlInsertString_freshInitializedCharacter() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Derek");
		TestCharacter character = new TestCharacter(Roles.Detective, Player.get("Derek"));
		GameEngine.assignCharacter(Player.get("Derek"), character);
		assertEquals("INSERT OR REPLACE INTO DATA (NAME,ROLE,SIDE,ALIVE,TARGETS,LYNCH_TARGET,LAST_WILL,DOUSED,ROLE_INFO) " +
				"VALUES ('Derek', 'Detective', 'Town', '1', 'NULL', 'NULL', 'No last will.', '0', '');",
				TestDatabaseManager.testSqlInsertString(character));
	}

	@Test
	public void test_SqlInsertString_characterWithSingleTarget() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Harris");
		TestCharacter character = new TestCharacter(Roles.Detective, Player.get("Derek"));
		character.setTarget(Player.get("Harris"));
		GameEngine.assignCharacter(Player.get("Derek"), character);
		assertEquals("INSERT OR REPLACE INTO DATA (NAME,ROLE,SIDE,ALIVE,TARGETS,LYNCH_TARGET,LAST_WILL,DOUSED,ROLE_INFO) " +
				"VALUES ('Derek', 'Detective', 'Town', '1', 'Harris', 'NULL', 'No last will.', '0', '');",
				TestDatabaseManager.testSqlInsertString(character));
	}

	@Test
	public void test_SqlInsertString_characterWithMultipleTargets() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Harris");
		GameEngine.registerPlayer("Connie");
		GameEngine.registerPlayer("Eleanor");
		TestCharacter character = new TestCharacter(Roles.Detective, Player.get("Derek"));
		character.setTarget(Player.get("Harris"));
		character.addTarget(Player.get("Connie"));
		character.addTarget(Player.get("Eleanor"));
		assertEquals(3, character.getTargets().size());
		GameEngine.assignCharacter(Player.get("Derek"), character);
		assertEquals("INSERT OR REPLACE INTO DATA (NAME,ROLE,SIDE,ALIVE,TARGETS,LYNCH_TARGET,LAST_WILL,DOUSED,ROLE_INFO) " +
				"VALUES ('Derek', 'Detective', 'Town', '1', 'Harris##Connie##Eleanor', 'NULL', 'No last will.', '0', '');",
				TestDatabaseManager.testSqlInsertString(character));
	}

	@Test
	public void test_SqlInsertString_characterWithLynchTarget() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("NotTheGodfather");
		TestCharacter character = new TestCharacter(Roles.Detective, Player.get("Derek"));
		TestCharacter character2 = new TestCharacter(Roles.Detective, Player.get("NotTheGodfather"));
		GameEngine.assignCharacter(Player.get("Derek"), character);
		GameEngine.assignCharacter(Player.get("NotTheGodfather"), character2);
		character.vote(Player.get("NotTheGodfather"));
		assertEquals("INSERT OR REPLACE INTO DATA (NAME,ROLE,SIDE,ALIVE,TARGETS,LYNCH_TARGET,LAST_WILL,DOUSED,ROLE_INFO) " +
				"VALUES ('Derek', 'Detective', 'Town', '1', 'NULL', 'NotTheGodfather', 'No last will.', '0', '');",
				TestDatabaseManager.testSqlInsertString(character));
	}

	@Test
	public void test_SqlInsertString_characterWithLastWill() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Derek");
		TestCharacter character = new TestCharacter(Roles.Detective, Player.get("Derek"));
		GameEngine.assignCharacter(Player.get("Derek"), character);
		character.updateLastWill("Harris is a dork");
		assertEquals("INSERT OR REPLACE INTO DATA (NAME,ROLE,SIDE,ALIVE,TARGETS,LYNCH_TARGET,LAST_WILL,DOUSED,ROLE_INFO) " +
				"VALUES ('Derek', 'Detective', 'Town', '1', 'NULL', 'NULL', 'Harris is a dork', '0', '');",
				TestDatabaseManager.testSqlInsertString(character));
	}

	@Test
	public void test_SqlInsertString_characterDoused() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Derek");
		TestCharacter character = new TestCharacter(Roles.Detective, Player.get("Derek"));
		GameEngine.assignCharacter(Player.get("Derek"), character);
		character.douse();
		assertEquals("INSERT OR REPLACE INTO DATA (NAME,ROLE,SIDE,ALIVE,TARGETS,LYNCH_TARGET,LAST_WILL,DOUSED,ROLE_INFO) " +
				"VALUES ('Derek', 'Detective', 'Town', '1', 'NULL', 'NULL', 'No last will.', '1', '');",
				TestDatabaseManager.testSqlInsertString(character));
	}

	@Test
	public void test_getTargetsFromString_null() {
		String sql_string = "NULL";
		List<Player> result_list = TestDatabaseManager.testGetTargetsFromString(sql_string);
		assert(result_list.isEmpty());
	}

	@Test
	public void test_getTargetsFromString_singleRegistered() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Derek");
		String sql_string = "Derek";
		List<Player> result_list = TestDatabaseManager.testGetTargetsFromString(sql_string);
		assertEquals(1, result_list.size());
		assert(result_list.contains(Player.get("Derek")));
	}

	@Test
	public void test_getTargetsFromString_multipleRegistered() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Eleanor");
		String sql_string = "Eleanor##Derek";
		List<Player> result_list = TestDatabaseManager.testGetTargetsFromString(sql_string);
		assertEquals(2, result_list.size());
		assert(result_list.contains(Player.get("Derek")));
		assert(result_list.contains(Player.get("Eleanor")));
	}

	@Test
	public void test_getTargetsFromString_multipleRegistered2() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Eleanor");
		GameEngine.registerPlayer("Andy");
		String sql_string = "Eleanor##Derek##Andy";
		List<Player> result_list = TestDatabaseManager.testGetTargetsFromString(sql_string);
		assertEquals(3, result_list.size());
		assert(result_list.contains(Player.get("Andy")));
		assert(result_list.contains(Player.get("Derek")));
		assert(result_list.contains(Player.get("Eleanor")));
	}

	@Test
	public void test_getTargetsFromString_singleNotRegistered() throws CannotGetPlayerException {
		String sql_string = "Derek";
		List<Player> result_list = TestDatabaseManager.testGetTargetsFromString(sql_string);
		assert(result_list.isEmpty());
	}

	@Test
	public void test_getTargetsFromString_multipleNotRegistered() throws CannotGetPlayerException {
		String sql_string = "Eleanor##Derek";
		List<Player> result_list = TestDatabaseManager.testGetTargetsFromString(sql_string);
		assert(result_list.isEmpty());
	}

	@Test
	public void test_getTargetsFromString_multipleNotRegistered2() throws CannotGetPlayerException {
		String sql_string = "Eleanor##Derek##Andy";
		List<Player> result_list = TestDatabaseManager.testGetTargetsFromString(sql_string);
		assert(result_list.isEmpty());
	}

	@Test
	public void test_getTargetsFromString_mixedRegistered() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Derek");
		String sql_string = "Eleanor##Derek";
		List<Player> result_list = TestDatabaseManager.testGetTargetsFromString(sql_string);
		assertEquals(1, result_list.size());
		assert(result_list.contains(Player.get("Derek")));
	}

	@Test
	public void test_getTargetsFromString_mixedRegistered2() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Eleanor");
		GameEngine.registerPlayer("Andy");
		String sql_string = "Eleanor##Derek##Andy";
		List<Player> result_list = TestDatabaseManager.testGetTargetsFromString(sql_string);
		assertEquals(2, result_list.size());
		assert(result_list.contains(Player.get("Andy")));
		assert(!result_list.contains(Player.get("Derek")));
		assert(result_list.contains(Player.get("Eleanor")));
	}

	@Test
	public void test_getTargetsFromString_mixedRegistered3() throws CannotGetPlayerException {
		GameEngine.registerPlayer("Eleanor");
		String sql_string = "Eleanor##Derek##Andy";
		List<Player> result_list = TestDatabaseManager.testGetTargetsFromString(sql_string);
		assertEquals(1, result_list.size());
		assert(!result_list.contains(Player.get("Andy")));
		assert(!result_list.contains(Player.get("Derek")));
		assert(result_list.contains(Player.get("Eleanor")));
	}

	/**
	 * Private class used to expose protected methods for testing
	 */
	private static class TestDatabaseManager extends DatabaseManager {

		protected static String testSqlInsertString(Character character) {
			return SqlInsertString(character);
		}

		protected static String testConcatenateTargets(List<Player> targets) {
			return concatenateTargets(targets);
		}

		protected static List<Player> testGetTargetsFromString(String sql_string) {
			return getTargetsFromString(sql_string);
		}
	}
}
