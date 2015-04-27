package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;
import GameEngine.Character;

public class Detective_Test {

	List<Player> playerList;
	List<String> nameList;
	List<Player> actionTarget;
	List<Player> lynchTarget;
	List<Character> allCharacters;
	Character character;

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

		try {
			for (String name : nameList) {
				GameEngine.registerPlayer(name);
				playerList.add(Player.get(name));
			}
		} catch (CannotGetPlayerException e) {
			fail("Cannot make players");
		}

		GameEngine.assignAllCharacters(0);

		allCharacters = GameEngine.getAllCharacters();
	}

	@Test
	public void test_detective_action_1() {
		
		for (Character search : allCharacters) {
			if (search.getRoleString().equals("Detective")) {
				character = search;
				break;
			}
		}
		actionTarget = new ArrayList<Player>();
		actionTarget.add(playerList.get(5));
		assertTrue(GameEngine.setTarget(character.getPlayer(), actionTarget));
		try {
			System.out.println(character.doAction());
		} catch (CannotGetPlayerException e) {
			fail("cannot find target");
		}
	}
	
	@Test
	public void test_detective_action_2() {
		
		for (Character search : allCharacters) {
			if (search.getRoleString().equals("Detective")) {
				character = search;
				break;
			}
		}
		actionTarget = new ArrayList<Player>();
		actionTarget.add(playerList.get(5));
		assertTrue(GameEngine.setTarget(character.getPlayer(), actionTarget));
		assertTrue(GameEngine.setTarget(playerList.get(5), actionTarget));		
		try {
			System.out.println(character.doAction());
		} catch (CannotGetPlayerException e) {
			fail("cannot find target");
		}
	}

}