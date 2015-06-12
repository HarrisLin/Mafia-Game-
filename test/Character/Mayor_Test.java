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

public class Mayor_Test {

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
		assertTrue(GameEngine.Information.listAlivePlayers().contains("Harris"));
		assertTrue(GameEngine.Information.listAlivePlayers().contains("Eleanor"));
		Player doctor1 = GameRegistration.get("Derek");
		Player doctor2 = GameRegistration.get("Andy");
		Player mayor = GameRegistration.get("Connie");
		Player vigilante1 = GameRegistration.get("Harris");
		Player vigilante2 = GameRegistration.get("Eleanor");
		GameEngine.Inputs.setVote(doctor1, vigilante1);
		GameEngine.Inputs.setVote(doctor2, vigilante1);
		GameEngine.Inputs.setVote(mayor, vigilante2);
		GameEngine.Management.startLynch();
		GameEngine.Management.startNewDay();
		assertFalse(GameEngine.Information.listAlivePlayers().contains("Harris"));
		assertTrue(GameEngine.Information.listAlivePlayers().contains("Eleanor"));
		GameEngine.Inputs.revealMayor(mayor);
		GameEngine.Inputs.setVote(doctor1, vigilante2);
		GameEngine.Inputs.setVote(doctor2, vigilante2);
		GameEngine.Inputs.setVote(mayor, doctor1);
		GameEngine.Management.startLynch();
		assertFalse(GameEngine.Information.listAlivePlayers().contains("Derek"));
		assertTrue(GameEngine.Information.listAlivePlayers().contains("Connie"));
	}

}
