package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;

public class Detective_Test {

	List<Player> playerList;
	List<String> nameList;

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
		GameEngine.reset();
		
		nameList = new ArrayList<String>();
		playerList = new ArrayList<Player>();
		
		nameList.add("Eleanor");
		nameList.add("Derek");
		nameList.add("Connie");
		nameList.add("Harris");
		nameList.add("Kaibo");
		nameList.add("Andy");
		nameList.add("Daniel");
		nameList.add("Chelsea");
		nameList.add("Christina");
		nameList.add("Ileana");
		nameList.add("Arabelle");
		nameList.add("Don");
		nameList.add("Lily");
		nameList.add("Stephen");
		nameList.add("Boschman");
		nameList.add("Andrea");
		nameList.add("Mitchell");
		nameList.add("Kevin");
		nameList.add("Marc");
		nameList.add("Jordan");
		nameList.add("Samuel");
		nameList.add("Jasmin");
		nameList.add("Will");
		nameList.add("Barry");
		nameList.add("Jacqueline");
		nameList.add("Johnny");
		nameList.add("Reanne");
		nameList.add("Ray");
		nameList.add("Lucy");
		nameList.add("Eddie");
		nameList.add("Amber");
		nameList.add("Mario");
		nameList.add("Adam");
		nameList.add("Jessica");
		nameList.add("Emmitt");
		nameList.add("Shelby");
		nameList.add("Kayla");
		nameList.add("Catherine");
		nameList.add("Jay");
	
		try {
			for(String name : nameList) {
				GameEngine.registerPlayer(name);
				playerList.add(Player.get(name));
			}
		} catch (CannotGetPlayerException e) {
			fail("Cannot make players");
		}

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
		assertTrue(GameEngine.getCharacter(player2).getVistors()
				.contains(player1));
		assertTrue(GameEngine.getCharacter(player1).getTarget()
				.contains(player2));

		// Check Bus Driver targets Consigliere/Godfather
		targets = new ArrayList<Player>();
		targets.add(player3);
		targets.add(player4);
		assertTrue(GameEngine.getCharacter(player2).getTarget()
				.contains(player3));
		assertTrue(GameEngine.getCharacter(player2).getTarget()
				.contains(player4));
	}

	@Test
	public void test_detective_action() {
		detectiveTarget = new ArrayList<Player>();
		detectiveTarget.add(player2);
		assertTrue(GameEngine.setTarget(player1, detectiveTarget));
		gameMessageResult = GameEngine.getCharacter(player1).doAction();
		String expectedMessageResult = "Your target visited "
				+ player3.getName() + "," + player4.getName() + ",";
		assertEquals(gameMessageResult, expectedMessageResult);
	}
}
