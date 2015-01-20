package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import GameEngine.GameEngine;
import GameEngine.Player;
import Character.Investigator;

public class Investigator_Test {

	String string1;
	String string2;
	String string3;
	List<Player> target;
	List<Player> visitors;

	@Before
	public void setup() {
		// do admin things: register players and assign roles
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Harris");
		GameEngine.registerPlayer("Andy");
		GameEngine.registerPlayer("Connie");
		GameEngine.registerPlayer("Kaibo");
		GameEngine
				.assignCharacter(
						Player.get("Derek"),
						new Investigator(
								Player.get("Derek"),
								GameOptions.InvestigatorOptions.DetectRole.DETECT_EXACT_ROLE));
		GameEngine.assignCharacter(Player.get("Connie"), new Investigator(
				Player.get("Connie"),
				GameOptions.InvestigatorOptions.DetectRole.DETECT_VAGUE_ROLE));
		GameEngine.assignCharacter(Player.get("Harris"), new Investigator(
				Player.get("Harris")));
		GameEngine.assignCharacter(Player.get("Andy"),
				new Arsonist(Player.get("Andy")));
		GameEngine.assignCharacter(Player.get("Kaibo"),
				new Mayor(Player.get("Kaibo")));

		// make Derek target Harris
		target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		string1 = GameEngine.getCharacter(Player.get("Derek")).doAction();

		// make Harris target Andy
		target = new ArrayList<Player>();
		target.add(Player.get("Andy"));
		assertTrue(GameEngine.setTarget(Player.get("Harris"), target));
		string2 = GameEngine.getCharacter(Player.get("Harris")).doAction();

		// make Connie target Kaibo
		target = new ArrayList<Player>();
		target.add(Player.get("Kaibo"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
		string3 = GameEngine.getCharacter(Player.get("Connie")).doAction();
	}

	@Test
	public void test_visited() {
		visitors = new ArrayList<Player>();
		visitors.add(Player.get("Derek"));
		assertTrue(GameEngine.getCharacter(Player.get("Harris")).getVistors()
				.contains(Player.get("Derek")));
		visitors = new ArrayList<Player>();
		visitors.add(Player.get("Harris"));
		assertTrue(GameEngine.getCharacter(Player.get("Andy")).getVistors()
				.contains(Player.get("Harris")));
		visitors = new ArrayList<Player>();
		visitors.add(Player.get("Connie"));
		assertTrue(GameEngine.getCharacter(Player.get("Kaibo")).getVistors()
				.contains(Player.get("Connie")));
	}

	@Test
	public void test_exact_role() {
		// make Derek investigate Harris
		assertEquals(string1,
				"The result of your investigation yeilded a role of Investigator.");
	}

	@Test
	public void test_vague_role1() {
		// make Harris investigate Andy
		boolean test;

		String startSentense = "The result of your investigation suggests a role guilty of ";
		if (string2.equals(startSentense + "murder" + ".")) {
			test = true;
		} else if (string2.equals(startSentense + "arson" + ".")) {
			test = true;
		} else if (string2.equals(startSentense + "destruction of property"
				+ ".")) {
			test = true;
		} else if (string2.equals(startSentense + "trespassing" + ".")) {
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
		String startSentense = "The result of your investigation suggests a role guilty of ";
		if (string3.equals(startSentense + "corruption" + ".")) {
			test = true;
		} else {
			test = false;
		}
		assertTrue(test);
	}
}
