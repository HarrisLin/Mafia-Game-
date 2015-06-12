package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import GameEngine.GameEngine;
import GameEngine.GameRegistration;
import GameEngine.GameRegistration.Player;
import Character.CharacterFactory.Roles;

public class Doctor_Test {

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
		role_list.add(Roles.Vigilante);
		role_list.add(Roles.Vigilante);
		GameEngine.Management.startGame(role_list, 1);
	}
	
	@Test
	public void test() {
		assertTrue(GameEngine.Information.listAlivePlayers().contains("Connie"));
		Player doctor = GameRegistration.get("Derek");
		Player vigilante = GameRegistration.get("Eleanor");
		Player target = GameRegistration.get("Connie");
		GameEngine.Inputs.setTarget(doctor, target);
		GameEngine.Inputs.setTarget(vigilante, target);
		GameEngine.Management.performNightActions();
		assertTrue(GameEngine.Information.listAlivePlayers().contains("Connie"));
	}

}
