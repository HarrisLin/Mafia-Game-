package org.mobcore.mafia.character;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.mobcore.mafia.character.CharacterFactory.Roles;
import org.mobcore.mafia.engine.GameEngine;
import org.mobcore.mafia.engine.GameRegistration;
import org.mobcore.mafia.engine.GameRegistration.Player;

public class Investigator_Test {

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
		role_list.add(Roles.Investigator);
		role_list.add(Roles.Vigilante);
		GameEngine.Management.startGame(role_list, 1);
	}

	@Test
	public void test() {
		Player doctor = GameRegistration.get("Derek");
		Player mayor = GameRegistration.get("Connie");
		Player investigator = GameRegistration.get("Harris");
		Player vigilante = GameRegistration.get("Eleanor");
		GameEngine.Inputs.setTarget(investigator, vigilante);
		GameEngine.Management.performNightActions();
		GameEngine.Management.startNewDay();
		String result = GameEngine.Information.showResult(investigator);
		assertTrue(result.contains("murder") || result.contains("trespassing"));
		GameEngine.Inputs.setTarget(investigator, mayor);
		GameEngine.Management.performNightActions();
		GameEngine.Management.startNewDay();
		result = GameEngine.Information.showResult(investigator);
		assertTrue(result.contains("corruption"));
		GameEngine.Inputs.setTarget(investigator, doctor);
		GameEngine.Management.performNightActions();
		GameEngine.Management.startNewDay();
		result = GameEngine.Information.showResult(investigator);
		assertTrue(result.contains("no crime"));
		
	}

}
