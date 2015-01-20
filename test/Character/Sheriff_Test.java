package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import GameEngine.GameEngine;
import GameEngine.Player;

public class Sheriff_Test {

	List<Player> target;
	String gameMessage;

	@Before
	public void setup() {
		// do admin things: register players and assign roles
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Harris");
		GameEngine.registerPlayer("Andy");
		GameEngine.registerPlayer("Connie");
		GameEngine.registerPlayer("Kaibo");
		GameEngine.registerPlayer("Don");
		GameEngine.registerPlayer("Nathan");
		GameEngine
				.assignCharacter(
						Player.get("Derek"),
						new Sheriff(
								Player.get("Derek"),
								GameOptions.SheriffOptions.DetectMafia.DETECT_MAFIA_ON,
								GameOptions.SheriffOptions.DetectArsonist.DETECT_ARSONIST_OFF,
								GameOptions.SheriffOptions.DetectCultist.DETECT_CULTIST_OFF,
								GameOptions.SheriffOptions.DetectMassMurderer.DETECT_MASSMURDERER_OFF));
		GameEngine
				.assignCharacter(
						Player.get("Connie"),
						new Sheriff(
								Player.get("Connie"),
								GameOptions.SheriffOptions.DetectMafia.DETECT_MAFIA_OFF,
								GameOptions.SheriffOptions.DetectArsonist.DETECT_ARSONIST_ON,
								GameOptions.SheriffOptions.DetectCultist.DETECT_CULTIST_ON,
								GameOptions.SheriffOptions.DetectMassMurderer.DETECT_MASSMURDERER_ON));
		GameEngine.assignCharacter(Player.get("Harris"),
				new Godfather(Player.get("Harris")));
		GameEngine.assignCharacter(Player.get("Andy"),
				new Arsonist(Player.get("Andy")));
		GameEngine.assignCharacter(Player.get("Kaibo"),
				new MassMurderer(Player.get("Kaibo")));
		GameEngine.assignCharacter(Player.get("Don"),
				new Cultist(Player.get("Don")));
		GameEngine.assignCharacter(Player.get("Nathan"),
				new Mayor(Player.get("Nathan")));
	}

	@Test
	public void test_visited1() {
		// make Derek target Harris
		target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Derek")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is suspicious.");

		// make Connie target Harris
		target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Connie")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
		assertTrue(GameEngine.getCharacter(Player.get("Harris")).getVistors()
				.contains(Player.get("Connie")));
		assertTrue(GameEngine.getCharacter(Player.get("Harris")).getVistors()
				.contains(Player.get("Derek")));
	}

	@Test
	public void test_visited2() {
		// make Derek target Harris
		target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Derek")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is suspicious.");

		// make Connie target Harris
		target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Connie")).doAction();
		assertFalse(GameEngine.getCharacter(Player.get("Harris")).getVistors()
				.contains(Player.get("Nathan")));
	}

	@Test
	public void test_detect_mafia_on() {
		// make Derek target Harris
		target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Derek")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is suspicious.");
	}

	@Test
	public void test_detect_mafia_off() {
		// make Connie target Harris
		target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Connie")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_arsonist_on() {
		// make Connie target Andy
		target = new ArrayList<Player>();
		target.add(Player.get("Andy"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Connie")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is a arsonist.");
	}

	@Test
	public void test_detect_arsonist_off() {
		// make Derek target Andy
		target = new ArrayList<Player>();
		target.add(Player.get("Andy"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Derek")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_cultist_on() {
		// make Connie target Don
		target = new ArrayList<Player>();
		target.add(Player.get("Don"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Connie")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is a cultist.");
	}

	@Test
	public void test_detect_cultist_off() {
		// make Derek target Don
		target = new ArrayList<Player>();
		target.add(Player.get("Don"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Derek")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_massmurderer_on() {
		// make Connie target Kaibo
		target = new ArrayList<Player>();
		target.add(Player.get("Kaibo"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Connie")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is a mass murderer.");
	}

	@Test
	public void test_detect_massmurderer_off() {
		// make Derek target Kaibo
		target = new ArrayList<Player>();
		target.add(Player.get("Kaibo"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Derek")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_town1() {
		// make Derek target Nathan
		target = new ArrayList<Player>();
		target.add(Player.get("Nathan"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Derek")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_town2() {
		// make Connie target Nathan
		target = new ArrayList<Player>();
		target.add(Player.get("Nathan"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		gameMessage = GameEngine.getCharacter(Player.get("Connie")).doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}
}
