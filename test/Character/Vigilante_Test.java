package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Character.CharacterFactory.Roles;
import GameEngine.GameEngine;
import GameEngine.GameRegistration;
import GameEngine.GameRegistration.Player;

public class Vigilante_Test {

	@Before
	public void setup() {
		GameEngine.Registration.registerPlayer("Derek");
		GameEngine.Registration.registerPlayer("Andy");
		GameEngine.Registration.registerPlayer("Connie");
		GameEngine.Registration.registerPlayer("Harris");
		GameEngine.Registration.registerPlayer("Eleanor");
		List<Roles> role_list = new ArrayList<Roles>();
		role_list.add(Roles.Doctor);
		role_list.add(Roles.Doctor);
		role_list.add(Roles.Mayor);
		role_list.add(Roles.Mayor);
		role_list.add(Roles.Vigilante);
		GameEngine.Management.startGame(role_list, 1);
	}
	
	@Test
	public void test() {
		assertTrue(GameEngine.Information.listAlivePlayers().contains("Connie"));
		Player vigilante = GameRegistration.get("Eleanor");
		Player target = GameRegistration.get("Connie");
		GameEngine.Inputs.setTarget(vigilante, target);
		GameEngine.Management.startNewDay();
		assertFalse(GameEngine.Information.listAlivePlayers().contains("Connie"));
	}
}
