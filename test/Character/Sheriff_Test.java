package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;

public class Sheriff_Test {

	Player player1;
	Player player2;
	Player player3;
	Player player4;
	Player player5;
	Player player6;
	Player player7;
	String string1;
	String string2;
	String string3;
	List<Player> target;
	List<Player> visitors;
	String gameMessage;

	@Before
	public void setup() {
		// do admin things: register players and assign roles
		Player.clearInGamePlayers();
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Connie");
		GameEngine.registerPlayer("Harris");
		GameEngine.registerPlayer("Andy");
		GameEngine.registerPlayer("Kaibo");
		GameEngine.registerPlayer("Don");
		GameEngine.registerPlayer("Nathan");
		try {
			player1 = Player.get("Derek");
			player2 = Player.get("Connie");
			player3 = Player.get("Harris");
			player4 = Player.get("Andy");
			player5 = Player.get("Kaibo");
			player6 = Player.get("Don");
			player7 = Player.get("Nathan");
		} catch (CannotGetPlayerException e) {
			fail("Cannot make players");
		}
		GameEngine
				.assignCharacter(
						player1,
						new Sheriff(
								player1,
								GameOptions.SheriffOptions.DetectMafia.DETECT_MAFIA_ON,
								GameOptions.SheriffOptions.DetectArsonist.DETECT_ARSONIST_OFF,
								GameOptions.SheriffOptions.DetectCultist.DETECT_CULTIST_OFF,
								GameOptions.SheriffOptions.DetectMassMurderer.DETECT_MASSMURDERER_OFF));
		GameEngine
				.assignCharacter(
						player2,
						new Sheriff(
								player2,
								GameOptions.SheriffOptions.DetectMafia.DETECT_MAFIA_OFF,
								GameOptions.SheriffOptions.DetectArsonist.DETECT_ARSONIST_ON,
								GameOptions.SheriffOptions.DetectCultist.DETECT_CULTIST_ON,
								GameOptions.SheriffOptions.DetectMassMurderer.DETECT_MASSMURDERER_ON));
		GameEngine.assignCharacter(player3, new Godfather(player3));
		GameEngine.assignCharacter(player4, new Arsonist(player4));
		GameEngine.assignCharacter(player5, new MassMurderer(player5));
		GameEngine.assignCharacter(player6, new Cultist(player6));
		GameEngine.assignCharacter(player7, new Mayor(player7));
	}

	@Test
	public void test_visited1() {
		// make Derek target Harris
		target = new ArrayList<Player>();
		target.add(player3);
		assertTrue(GameEngine.setTarget(player1, target));
		gameMessage = GameEngine.getCharacter(player1).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is suspicious.");

		// make Connie target Harris
		target = new ArrayList<Player>();
		target.add(player3);
		assertTrue(GameEngine.setTarget(player2, target));
		gameMessage = GameEngine.getCharacter(player2).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
		assertTrue(GameEngine.getCharacter(player3).getVistors()
				.contains(player2));
		assertTrue(GameEngine.getCharacter(player3).getVistors()
				.contains(player1));
	}

	@Test
	public void test_visited2() {
		// make Derek target Harris
		target = new ArrayList<Player>();
		target.add(player3);
		assertTrue(GameEngine.setTarget(player1, target));
		gameMessage = GameEngine.getCharacter(player1).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is suspicious.");

		// make Connie target Harris
		target = new ArrayList<Player>();
		target.add(player3);
		assertTrue(GameEngine.setTarget(player2, target));
		gameMessage = GameEngine.getCharacter(player2).doAction();
		assertFalse(GameEngine.getCharacter(player3).getVistors()
				.contains(player7));
	}

	@Test
	public void test_detect_mafia_on() {
		// make Derek target Harris
		target = new ArrayList<Player>();
		target.add(player3);
		assertTrue(GameEngine.setTarget(player1, target));
		gameMessage = GameEngine.getCharacter(player1).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is suspicious.");
	}

	@Test
	public void test_detect_mafia_off() {
		// make Connie target Harris
		target = new ArrayList<Player>();
		target.add(player3);
		assertTrue(GameEngine.setTarget(player2, target));
		gameMessage = GameEngine.getCharacter(player2).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_arsonist_on() {
		// make Connie target Andy
		target = new ArrayList<Player>();
		target.add(player4);
		assertTrue(GameEngine.setTarget(player2, target));
		gameMessage = GameEngine.getCharacter(player2).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is a arsonist.");
	}

	@Test
	public void test_detect_arsonist_off() {
		// make Derek target Andy
		target = new ArrayList<Player>();
		target.add(player4);
		assertTrue(GameEngine.setTarget(player1, target));
		gameMessage = GameEngine.getCharacter(player1).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_cultist_on() {
		// make Connie target Don
		target = new ArrayList<Player>();
		target.add(player6);
		assertTrue(GameEngine.setTarget(player2, target));
		gameMessage = GameEngine.getCharacter(player2).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is a cultist.");
	}

	@Test
	public void test_detect_cultist_off() {
		// make Derek target Don
		target = new ArrayList<Player>();
		target.add(player6);
		assertTrue(GameEngine.setTarget(player1, target));
		gameMessage = GameEngine.getCharacter(player1).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_massmurderer_on() {
		// make Connie target Kaibo
		target = new ArrayList<Player>();
		target.add(player5);
		assertTrue(GameEngine.setTarget(player2, target));
		gameMessage = GameEngine.getCharacter(player2).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is a mass murderer.");
	}

	@Test
	public void test_detect_massmurderer_off() {
		// make Derek target Kaibo
		target = new ArrayList<Player>();
		target.add(player5);
		assertTrue(GameEngine.setTarget(player1, target));
		gameMessage = GameEngine.getCharacter(player1).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_town1() {
		// make Derek target Nathan
		target = new ArrayList<Player>();
		target.add(player7);
		assertTrue(GameEngine.setTarget(player1, target));
		gameMessage = GameEngine.getCharacter(player1).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_town2() {
		// make Connie target Nathan
		target = new ArrayList<Player>();
		target.add(player7);
		assertTrue(GameEngine.setTarget(player2, target));
		gameMessage = GameEngine.getCharacter(player2).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}
}
