package Character;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.GameMessage;
import GameEngine.Player;

public class Arsonist_Test {
	
	@Before
	public void setup()
	{
		GameEngine.reset();
	}

	@Test
	public void test_arsonist_message_no_target()
	{
		ArrayList<Player> victims = new ArrayList<Player>();
		String result = GameMessage.ARSONIST_KILL(victims);
		assertEquals(GameMessage.NO_KILL, result);
	}
	
	@Test
	public void test_arsonist_message_one_target() throws CannotGetPlayerException
	{
		GameEngine.registerPlayer("Nathan Chiu");
		ArrayList<Player> victims = new ArrayList<Player>();
		victims.add(Player.get("Nathan Chiu"));
		
		String result = GameMessage.ARSONIST_KILL(victims);
		
		assertEquals(result, "Nathan Chiu has been killed.");
	}
	
	@Test
	public void test_arsonist_message_two_targets() throws CannotGetPlayerException
	{
		GameEngine.registerPlayer("Nathan Chiu");
		GameEngine.registerPlayer("Klarq");
		ArrayList<Player> victims = new ArrayList<Player>();
		victims.add(Player.get("Nathan Chiu"));
		victims.add(Player.get("Klarq"));
		
		String result = GameMessage.ARSONIST_KILL(victims);
		
		assertEquals(result, "Nathan Chiu and Klarq have been killed.");
	}
	
	@Test
	public void test_arsonist_message_multiple_targets() throws CannotGetPlayerException
	{
		GameEngine.registerPlayer("Nathan Chiu");
		GameEngine.registerPlayer("Klarq");
		GameEngine.registerPlayer("Christina's Boyfriend");
		ArrayList<Player> victims = new ArrayList<Player>();
		victims.add(Player.get("Nathan Chiu"));
		victims.add(Player.get("Klarq"));
		victims.add(Player.get("Christina's Boyfriend"));
		
		String result = GameMessage.ARSONIST_KILL(victims);
		
		assertEquals(result, "Nathan Chiu, Klarq and Christina's Boyfriend have been killed.");
	}
	
	@Test
	public void test_arsonist_no_target() throws CannotGetPlayerException
	{
		GameEngine.registerPlayer("Mr. Arsonist");
		Player arsonist = Player.get("Mr. Arsonist");
		GameEngine.assignCharacter(arsonist, new Arsonist(arsonist));
		
		
		ArrayList<Player> target = new ArrayList<Player>();
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.NO_ACTION, GameEngine.getCharacter(arsonist).doAction());
	}
	
	@Test
	public void test_arsonist_set_too_many_targets() throws CannotGetPlayerException
	{
		GameEngine.registerPlayer("Mr. Arsonist");
		Player arsonist = Player.get("Mr. Arsonist");
		GameEngine.assignCharacter(arsonist, new Arsonist(arsonist));
		
		GameEngine.registerPlayer("Nathan Chiu");
		Player victim = Player.get("Nathan Chiu");
		GameEngine.assignCharacter(victim, new Detective(victim));
		
		GameEngine.registerPlayer("Christina's Boyfriend");
		Player victim2 = Player.get("Christina's Boyfriend");
		GameEngine.assignCharacter(victim2, new Detective(victim2));
		
		ArrayList<Player> target = new ArrayList<Player>();
		target.add(victim);
		target.add(victim2);
		if (GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Error setting target.");
		}
	}
	
	@Test
	public void test_arsonist_douse() throws CannotGetPlayerException
	{
		GameEngine.registerPlayer("Mr. Arsonist");
		Player arsonist = Player.get("Mr. Arsonist");
		GameEngine.assignCharacter(arsonist, new Arsonist(arsonist));
		
		GameEngine.registerPlayer("Nathan Chiu");
		Player victim = Player.get("Nathan Chiu");
		GameEngine.assignCharacter(victim, new Detective(victim));
		
		ArrayList<Player> target = new ArrayList<Player>();
		target.add(victim);
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_DOUSE(victim), GameEngine.getCharacter(arsonist).doAction());
	}
	
	@Test
	public void test_arsonist_ignite_no_victims() throws CannotGetPlayerException
	{
		GameEngine.registerPlayer("Mr. Arsonist");
		Player arsonist = Player.get("Mr. Arsonist");
		GameEngine.assignCharacter(arsonist, new Arsonist(arsonist));
		
		ArrayList<Player> target = new ArrayList<Player>();
		target.add(arsonist);
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_KILL(new ArrayList<Player>()), GameEngine.getCharacter(arsonist).doAction());
	}
	
	@Test
	public void test_arsonist_ignite_one_victim() throws CannotGetPlayerException
	{
		GameEngine.registerPlayer("Mr. Arsonist");
		Player arsonist = Player.get("Mr. Arsonist");
		GameEngine.assignCharacter(arsonist, new Arsonist(arsonist));
		
		GameEngine.registerPlayer("Nathan Chiu");
		Player victim = Player.get("Nathan Chiu");
		GameEngine.assignCharacter(victim, new Detective(victim));
		
		ArrayList<Player> target = new ArrayList<Player>();
		target.add(victim);
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_DOUSE(victim), GameEngine.getCharacter(arsonist).doAction());
		
		ArrayList<Player> trigger = new ArrayList<Player>();
		trigger.add(arsonist);
		if (!GameEngine.getCharacter(arsonist).setTarget(trigger)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_KILL(target), GameEngine.getCharacter(arsonist).doAction());
	}
	
	@Test
	public void test_arsonist_ignite_two_victims() throws CannotGetPlayerException
	{
		GameEngine.registerPlayer("Mr. Arsonist");
		Player arsonist = Player.get("Mr. Arsonist");
		GameEngine.assignCharacter(arsonist, new Arsonist(arsonist));
		
		GameEngine.registerPlayer("Nathan Chiu");
		Player victim = Player.get("Nathan Chiu");
		GameEngine.assignCharacter(victim, new Detective(victim));
		
		GameEngine.registerPlayer("Christina's Boyfriend");
		Player victim2 = Player.get("Christina's Boyfriend");
		GameEngine.assignCharacter(victim2, new Detective(victim2));
		
		ArrayList<Player> target = new ArrayList<Player>();
		target.add(victim);
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_DOUSE(victim), GameEngine.getCharacter(arsonist).doAction());
		
		target = new ArrayList<Player>();
		target.add(victim2);
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_DOUSE(victim2), GameEngine.getCharacter(arsonist).doAction());
				
		ArrayList<Player> trigger = new ArrayList<Player>();
		trigger.add(arsonist);
		if (!GameEngine.getCharacter(arsonist).setTarget(trigger)) {
			fail("Could not set target.");
		}
		
		ArrayList<Player> verify_targets = new ArrayList<Player>();
		verify_targets.add(victim);
		verify_targets.add(victim2);
		assertEquals(GameMessage.ARSONIST_KILL(verify_targets), GameEngine.getCharacter(arsonist).doAction());
	}
	
	@Test
	public void test_arsonist_ignite_multiple_victims() throws CannotGetPlayerException
	{
		GameEngine.registerPlayer("Mr. Arsonist");
		Player arsonist = Player.get("Mr. Arsonist");
		GameEngine.assignCharacter(arsonist, new Arsonist(arsonist));
		
		GameEngine.registerPlayer("Nathan Chiu");
		Player victim = Player.get("Nathan Chiu");
		GameEngine.assignCharacter(victim, new Detective(victim));
		
		GameEngine.registerPlayer("Christina's Boyfriend");
		Player victim2 = Player.get("Christina's Boyfriend");
		GameEngine.assignCharacter(victim2, new Detective(victim2));
		
		GameEngine.registerPlayer("Boob3");
		Player victim3 = Player.get("Boob3");
		GameEngine.assignCharacter(victim3, new Detective(victim3));
		
		ArrayList<Player> target = new ArrayList<Player>();
		target.add(victim);
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_DOUSE(victim), GameEngine.getCharacter(arsonist).doAction());
		
		target = new ArrayList<Player>();
		target.add(victim2);
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_DOUSE(victim2), GameEngine.getCharacter(arsonist).doAction());
		
		target = new ArrayList<Player>();
		target.add(victim3);
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_DOUSE(victim3), GameEngine.getCharacter(arsonist).doAction());
				
		ArrayList<Player> trigger = new ArrayList<Player>();
		trigger.add(arsonist);
		if (!GameEngine.getCharacter(arsonist).setTarget(trigger)) {
			fail("Could not set target.");
		}
		
		ArrayList<Player> verify_targets = new ArrayList<Player>();
		verify_targets.add(victim);
		verify_targets.add(victim2);
		verify_targets.add(victim3);
		assertEquals(GameMessage.ARSONIST_KILL(verify_targets), GameEngine.getCharacter(arsonist).doAction());
	}
	
	@Test
	public void test_arsonist_ignite_multiple_victims_one_healed() throws CannotGetPlayerException
	{
		GameEngine.registerPlayer("Mr. Arsonist");
		Player arsonist = Player.get("Mr. Arsonist");
		GameEngine.assignCharacter(arsonist, new Arsonist(arsonist));
		
		GameEngine.registerPlayer("Nathan Chiu");
		Player victim = Player.get("Nathan Chiu");
		GameEngine.assignCharacter(victim, new Detective(victim));
		
		GameEngine.registerPlayer("Christina's Boyfriend");
		Player victim2 = Player.get("Christina's Boyfriend");
		GameEngine.assignCharacter(victim2, new Detective(victim2));
		
		GameEngine.registerPlayer("Boob3");
		Player victim3 = Player.get("Boob3");
		GameEngine.assignCharacter(victim3, new Detective(victim3));
		
		ArrayList<Player> target = new ArrayList<Player>();
		target.add(victim);
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_DOUSE(victim), GameEngine.getCharacter(arsonist).doAction());
		
		target = new ArrayList<Player>();
		target.add(victim2);
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_DOUSE(victim2), GameEngine.getCharacter(arsonist).doAction());
		if (!GameEngine.getCharacter(victim2).healPlayer());
		
		target = new ArrayList<Player>();
		target.add(victim3);
		if (!GameEngine.getCharacter(arsonist).setTarget(target)) {
			fail("Could not set target.");
		}
		assertEquals(GameMessage.ARSONIST_DOUSE(victim3), GameEngine.getCharacter(arsonist).doAction());
				
		ArrayList<Player> trigger = new ArrayList<Player>();
		trigger.add(arsonist);
		if (!GameEngine.getCharacter(arsonist).setTarget(trigger)) {
			fail("Could not set target.");
		}
		
		ArrayList<Player> verify_targets = new ArrayList<Player>();
		verify_targets.add(victim);
		verify_targets.add(victim3);
		assertEquals(GameMessage.ARSONIST_KILL(verify_targets), GameEngine.getCharacter(arsonist).doAction());
	}
}
