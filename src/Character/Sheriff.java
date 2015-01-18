package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;
import GameEngine.GameEngine;

/**
 * The Sheriff targets a Player and determines if they are Suspicious or Not Suspicious
 */
public class Sheriff extends Character {

	Sheriff() {
		super(Roles.Sheriff);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// The sheriff can only target a single Player
		if (targets.size() != 1 ) {
			return false;
		}
		this.targets = targets;
		return true;
	}
	
	@Override
	public String doAction() {
		String target_name = targets.get(0).getName();
		String result;
		if(GameEngine.getCharacter(targets.get(0)).checkSuspicious()) {
			result = "suspicious";
		}
		else {
			result = "not suspicous";
		}
		return "Player " + target_name + " is a " + result + ".";
	}
}