package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import GameEngine.GameEngine;
import GameEngine.Player;
import Character.Investigator;

public class Investigator_Test {
	
	@Test
	public void testInvestigation_exact_role() {
		// do admin things: register players and assign roles
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Harris");
		GameEngine.assignCharacter(Player.get("Derek"), new Investigator());
		GameEngine.assignCharacter(Player.get("Harris"), new Investigator());
		
		// make Derek target Harris
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		assertTrue( GameEngine.setTarget(Player.get("Derek"), target) );
		
		// make Derek investigate Harris
		assertEquals( GameEngine.getCharacter(Player.get("Derek")).doAction(),
				"The result of your investigation yeilded a role of Investigator.");
	}

}
