package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Enumerators.Investigations;
import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;
import GameOptions.InvestigatorOptions.DetectRole;
import Character.Investigator;

public class Investigator_Test {

	Player player1;
	Player player2;
	Player player3;
	Player player4;
	Player player5;
	String string1;
	String string2;
	String string3;
	List<Player> target;
	List<Player> visitors;

	@Before
	public void setup() throws CannotGetPlayerException {
		// do admin things: register players and assign roles
		GameEngine.reset();
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Harris");
		GameEngine.registerPlayer("Andy");
		GameEngine.registerPlayer("Connie");
		GameEngine.registerPlayer("Kaibo");
		try {
			player1 = Player.get("Derek");
			player2 = Player.get("Connie");
			player3 = Player.get("Harris");
			player4 = Player.get("Andy");
			player5 = Player.get("Kaibo");
		} catch (CannotGetPlayerException e) {
			fail("Cannot make players");
		}

		GameEngine.assignCharacter(player1, new Investigator(player1,
				DetectRole.DETECT_EXACT_ROLE));
		GameEngine.assignCharacter(player2, new Investigator(player2,
				DetectRole.DETECT_VAGUE_ROLE));
		GameEngine.assignCharacter(player3, new Investigator(player3));
		GameEngine.assignCharacter(player4, new Arsonist(player4));
		GameEngine.assignCharacter(player5, new Mayor(player5));

		// make Derek target Harris
		target = new ArrayList<Player>();
		target.add(player3);
		assertTrue(GameEngine.setTarget(player1, target));
		string1 = GameEngine.getCharacter(player1).doAction();

		// make Harris target Andy
		target = new ArrayList<Player>();
		target.add(player4);
		assertTrue(GameEngine.setTarget(player3, target));
		string2 = GameEngine.getCharacter(player3).doAction();

		// make Connie target Kaibo
		target = new ArrayList<Player>();
		target.add(player5);
		assertTrue(GameEngine.setTarget(player2, target));
		string3 = GameEngine.getCharacter(player2).doAction();
	}

	@Test
	public void test_visited() throws CannotGetPlayerException {
		visitors = new ArrayList<Player>();
		visitors.add(player1);
		assertTrue(GameEngine.getCharacter(player3).getVistors()
				.contains(player1));
		visitors = new ArrayList<Player>();
		visitors.add(player3);
		assertTrue(GameEngine.getCharacter(player4).getVistors()
				.contains(player3));
		visitors = new ArrayList<Player>();
		visitors.add(player2);
		assertTrue(GameEngine.getCharacter(player5).getVistors()
				.contains(player2));
	}

	@Test
	public void test_exact_role() {
		// make Derek investigate Harris
		assertTrue(string1.contains("Investigator"));
	}

	@Test
	public void test_vague_role1() {
		// make Harris investigate Andy
		boolean test;
		if (string2.contains(Investigations.Murder.toString())) {
			test = true;
		} else if (string2.contains(Investigations.Arson.toString())) {
			test = true;
		} else if (string2.contains(Investigations.DestructionOfProperty.toString())) {
			test = true;
		} else if (string2.contains(Investigations.Trespassing.toString())) {
			test = true;
		} else {
			test = false;
		}
		assertTrue(test);
	}

	@Test
	public void test_vague_role2() {
		// make Connie investigate Kaibo
		boolean test;
		if (string3.contains(Investigations.Corruption.toString())) {
			test = true;
		} else {
			test = false;
		}
		assertTrue(test);
	}
}
