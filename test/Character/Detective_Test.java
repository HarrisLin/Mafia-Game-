package Character;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import GameEngine.GameEngine;
import GameEngine.Character;
import GameEngine.Player;
import Character.Detective;
import Character.Roles;

public class Detective_Test {
	
	@Test
	public void testAction() {
		// do admin things: register players and assign roles
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Harris");
		GameEngine.assignCharacter(Player.get("Derek"), new Detective());
		GameEngine.assignCharacter(Player.get("Harris"), new Dork());
		
		// make Derek target Harris
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		Assert.assertTrue( GameEngine.setTarget(Player.get("Derek"), target) );
		
		// make Derek investigate Harris
		Assert.assertEquals( GameEngine.getCharacter(Player.get("Derek")).doAction(),
				"Player Harris is a Dork.");
	}

	/**
	 * Gotta keep it professional.
	 * 
	 * Private shell class Dork to make sure that the Detective returns the
	 * correct Class string. It's methods do nothing since they're never called.
	 * They don't need to be implemented.
	 */
	private class Dork extends Character {

		protected Dork() {
			super("Dork", Roles.Administrator);
		}

		@Override
		public boolean setTarget(List<Player> targets) {
			return false;
		}

		@Override
		public String doAction() {
			return null;
		}		
	}
}
