package GameEngine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AssignAllPlayers_Test {
	
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
		//nameList.add("Samuel");
		//nameList.add("Jasmin");
		//nameList.add("Will");
		//nameList.add("Barry");
		//nameList.add("Jacqueline");
		//nameList.add("Johnny");
		//nameList.add("Reanne");
		//nameList.add("Ray");
		//nameList.add("Lucy");
		//nameList.add("Eddie");
		//nameList.add("Amber");
		//nameList.add("Mario");
		//nameList.add("Adam");
		//nameList.add("Jessica");
		//nameList.add("Emmitt");
		//nameList.add("Shelby");
		//nameList.add("Kayla");
		//nameList.add("Catherine");
		//nameList.add("Jay");
		//nameList.add("Chris");
		
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
	public void test() {

		GameEngine.getAlivePlayer();
		for(Character character : GameEngine.getAlivePlayer()) {
			System.out.println(character.getPlayer().getName() + ": " + character.getRoleString());
		}
		
		assertEquals(allCharacters.size(), 20);
	}

}
