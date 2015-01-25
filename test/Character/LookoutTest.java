package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;

public class LookoutTest {

	Player player1;
	Player player2;
	Player player3;
	Player player4;
	Player player5;
	String string1;
	String string2;
	String string3;
	
	String gameMessageResult;
	List<Player> target;
	List<Player> lookoutTarget;
	List<Player> targets;
	List<Player> visitors;
	
	
	@Before
	public void setup() {
		Player.clearInGamePlayers();
		GameEngine.registerPlayer("Eleanor");
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Connie");
		GameEngine.registerPlayer("Harris");
		try {
			player1 = Player.get("Eleanor");
			player2 = Player.get("Derek");
			player3 = Player.get("Connie");
			player4 = Player.get("Harris");
		} catch (CannotGetPlayerException e) {
			fail("Cannot make players");
		}
		
		GameEngine.assignCharacter(player1, new Lookout(player1));
		GameEngine.assignCharacter(player2, new Escort(player2));
		GameEngine.assignCharacter(player3, new Detective(player3));
		GameEngine.assignCharacter(player4, new Detective(player4));
		
		// Make Connie and Harris visit Derek
		target = new ArrayList<Player>();
		target.add(player2);
		assertTrue(GameEngine.setTarget(player3, target));
		assertTrue(GameEngine.setTarget(player4, target));  // Get Connie and Harris to Visit Derek
		string1 = GameEngine.getCharacter(player3).doAction();
		string2 = GameEngine.getCharacter(player4).doAction();
	}
	
	@Test
	public void test_visited() {
		lookoutTarget = new ArrayList<Player>();
		lookoutTarget.add(player2);
		GameEngine.setTarget(player1, lookoutTarget);
		
		// Check Harris and Connie Visit Derek
		assertTrue(GameEngine.getCharacter(player2).getVistors().contains(player3));
		assertTrue(GameEngine.getCharacter(player2).getVistors().contains(player4));
		
		// Check Eleanor targets Derek
		targets = new ArrayList<Player>();
		targets.add(player1); targets.add(player2);
		assertTrue(GameEngine.getCharacter(player1).getTargets().contains(player2));
	}
	
	@Test
	public void test_lookout_action() {
		lookoutTarget = new ArrayList<Player>();
		lookoutTarget.add(player2);
		assertTrue(GameEngine.setTarget(player1,lookoutTarget));
		gameMessageResult = GameEngine.getCharacter(player1).doAction();
		String expectedMessageResult = "Your target was visited by " + player3.getName() + "," + player4.getName() + ",";
		assertEquals(gameMessageResult, expectedMessageResult);
	}
}
