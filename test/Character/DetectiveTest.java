package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;

public class DetectiveTest {

	Player player1;
	Player player2;
	Player player3;
	Player player4;
	Player player5;
	String string1;
	String string2;
	String string3;
	
	String gameMessageResult;
	List<Player> busDriverTarget;
	List<Player> detectiveTarget;
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
		
		GameEngine.assignCharacter(player1, new Detective(player1));
		GameEngine.assignCharacter(player2, new BusDriver(player2));
		GameEngine.assignCharacter(player3, new Consigliere(player3));
		GameEngine.assignCharacter(player4, new Godfather(player4));
		
		// Make Derek target Connie and Harris
		busDriverTarget = new ArrayList<Player>();
		busDriverTarget.add(player3); busDriverTarget.add(player4);
		assertTrue(GameEngine.setTarget(player2, busDriverTarget));
		string1 = GameEngine.getCharacter(player2).doAction();
	}
	
	@Test
	public void test_visited() {
		detectiveTarget = new ArrayList<Player>();
		detectiveTarget.add(player2);
		GameEngine.setTarget(player1, detectiveTarget);
		GameEngine.getCharacter(player1).doAction();
		// Check Detective visited Bus Driver
		assertTrue(GameEngine.getCharacter(player2).getVistors().contains(player1));
		assertTrue(GameEngine.getCharacter(player1).getTargets().contains(player2));
		
		// Check Bus Driver targets Consigliere/Godfather
		targets = new ArrayList<Player>();
		targets.add(player3); targets.add(player4);
		assertTrue(GameEngine.getCharacter(player2).getTargets().contains(player3));
		assertTrue(GameEngine.getCharacter(player2).getTargets().contains(player4));
	}
	
	@Test
	public void test_detective_action() {
		detectiveTarget = new ArrayList<Player>();
		detectiveTarget.add(player2);
		assertTrue(GameEngine.setTarget(player1,detectiveTarget));
		gameMessageResult = GameEngine.getCharacter(player1).doAction();
		String expectedMessageResult = "Your target visited " + player3 + "," + player4 + ",";
		assertEquals(gameMessageResult, expectedMessageResult);
	}
}
