package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import GameEngine.GameEngine;
import GameEngine.Player;

public class Sheriff_Test {

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
								GameOptions.SheriffOptions.DetectMafia.DETECT_MAFIA_ON,
								GameOptions.SheriffOptions.DetectArsonist.DETECT_ARSONIST_OFF,
								GameOptions.SheriffOptions.DetectCultist.DETECT_CULTIST_OFF,
								GameOptions.SheriffOptions.DetectMassMurderer.DETECT_MASSMURDERER_OFF));
		GameEngine
				.assignCharacter(
						Player.get("Connie"),
						new Sheriff(
								GameOptions.SheriffOptions.DetectMafia.DETECT_MAFIA_OFF,
								GameOptions.SheriffOptions.DetectArsonist.DETECT_ARSONIST_ON,
								GameOptions.SheriffOptions.DetectCultist.DETECT_CULTIST_ON,
								GameOptions.SheriffOptions.DetectMassMurderer.DETECT_MASSMURDERER_ON));
		GameEngine.assignCharacter(Player.get("Harris"), new Godfather());
		GameEngine.assignCharacter(Player.get("Andy"), new Arsonist());
		GameEngine.assignCharacter(Player.get("Kaibo"), new MassMurderer());
		GameEngine.assignCharacter(Player.get("Don"), new Cultist());
		GameEngine.assignCharacter(Player.get("Nathan"), new Mayor());
	}

	@Test
	public void test_detect_mafia_on() {
		// make Derek target Harris
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		String gameMessage = GameEngine.getCharacter(Player.get("Derek"))
				.doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is suspicious.");
	}

	@Test
	public void test_detect_mafia_off() {
		// make Connie target Harris
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		String gameMessage = GameEngine.getCharacter(Player.get("Connie"))
				.doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_arsonist_on() {
		// make Connie target Andy
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Andy"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		String gameMessage = GameEngine.getCharacter(Player.get("Connie"))
				.doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is a arsonist.");
	}

	@Test
	public void test_detect_arsonist_off() {
		// make Derek target Andy
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Andy"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		String gameMessage = GameEngine.getCharacter(Player.get("Derek"))
				.doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_cultist_on() {
		// make Connie target Don
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Don"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		String gameMessage = GameEngine.getCharacter(Player.get("Connie"))
				.doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is a cultist.");
	}

	@Test
	public void test_detect_cultist_off() {
		// make Derek target Don
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Don"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		String gameMessage = GameEngine.getCharacter(Player.get("Derek"))
				.doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_massmurderer_on() {
		// make Connie target Kaibo
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Kaibo"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		String gameMessage = GameEngine.getCharacter(Player.get("Connie"))
				.doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is a mass murderer.");
	}

	@Test
	public void test_detect_massmurderer_off() {
		// make Derek target Kaibo
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Kaibo"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		String gameMessage = GameEngine.getCharacter(Player.get("Derek"))
				.doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_town1() {
		// make Derek target Nathan
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Nathan"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		String gameMessage = GameEngine.getCharacter(Player.get("Derek"))
				.doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}

	@Test
	public void test_detect_town2() {
		// make Connie target Nathan
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Nathan"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		String gameMessage = GameEngine.getCharacter(Player.get("Connie"))
				.doAction();
		assertEquals(gameMessage,
				"The outcome of your results suggests your target is not suspicious.");
	}
}
