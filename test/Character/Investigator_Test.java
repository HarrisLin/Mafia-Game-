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

	@Before
	public void setup() {
		// do admin things: register players and assign roles
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Harris");
		GameEngine.registerPlayer("Andy");
		GameEngine.registerPlayer("Connie");
		GameEngine.registerPlayer("Kaibo");
		GameEngine.assignCharacter(Player.get("Derek"), new Investigator(
				Investigator.DetectRoleOption.DETECT_EXACT_ROLE));
		GameEngine.assignCharacter(Player.get("Connie"), new Investigator(
				Investigator.DetectRoleOption.DETECT_VAGUE_ROLE));
		GameEngine.assignCharacter(Player.get("Harris"), new Investigator());
		GameEngine.assignCharacter(Player.get("Andy"), new Arsonist());
		GameEngine.assignCharacter(Player.get("Kaibo"), new Mayor());
		// make Derek target Harris
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		assertTrue(GameEngine.setTarget(Player.get("Derek"), target));
		// make Harris target Andy
		target = new ArrayList<Player>();
		target.add(Player.get("Andy"));
		assertTrue(GameEngine.setTarget(Player.get("Harris"), target));
		// make Connie target Kaibo
		target = new ArrayList<Player>();
		target.add(Player.get("Kaibo"));
		assertTrue(GameEngine.setTarget(Player.get("Connie"), target));
	}

	@Test
	public void testInvestigation_exact_role() {
		// make Derek investigate Harris
		assertEquals(GameEngine.getCharacter(Player.get("Derek")).doAction(),
				"The result of your investigation yeilded a role of Investigator.");
	}

	@Test
	public void testInvestigation_vague_role() {
		// make Harris investigate Andy
		boolean test;
		String gameMessage = GameEngine.getCharacter(Player.get("Harris")).doAction();
		String startSentense = "The result of your investigation suggests a role guilty of ";
		if(gameMessage.equals(startSentense + "murder" + ".")) {
			test = true;
		}
		else if(gameMessage.equals(startSentense + "arson" + ".")) {
			test = true;
		}
		else if(gameMessage.equals(startSentense + "destruction of property" + ".")) {
			test = true;
		}
		else if(gameMessage.equals(startSentense + "trespassing" + ".")) {
			test = true;
		}
		else {
			test = false;
		}
		assertTrue(test);
	}
	
	@Test
	public void testInvestigation_vague_role1() {
		// make Harris investigate Andy
		boolean test;
		String gameMessage = GameEngine.getCharacter(Player.get("Connie")).doAction();
		String startSentense = "The result of your investigation suggests a role guilty of ";
		if(gameMessage.equals(startSentense + "corruption" + ".")) {
			test = true;
		}
		else {
			test = false;
		}
		assertTrue(test);
	}
}
