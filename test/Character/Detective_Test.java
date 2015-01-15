package Character;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import GameEngine.*;
import GameEngine.Character;
import Character.Detective;
import Character.Roles;

public class Detective_Test {
	
	@Test
	public void testAction() {
		GameEngine.registerPlayer("Derek");
		GameEngine.registerPlayer("Harris");
		GameEngine.assignCharacter(Player.get("Derek"), new Detective());
		GameEngine.assignCharacter(Player.get("Harris"), new Dork());
		
		List<Player> target = new ArrayList<Player>();
		target.add(Player.get("Harris"));
		Assert.assertTrue( GameEngine.setTarget(Player.get("Derek"), target) );
		Assert.assertEquals( GameEngine.getCharacter(Player.get("Derek")).doAction(),
				"Player Harris is a Dork.");
	}

	/**
	 * Gotta keep it professional.
	 */
	private class Dork extends Character {

		protected Dork() {
			super("Dork", Roles.Administrator);
		}

		@Override
		public boolean setTarget(List<Player> targets) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String doAction() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
