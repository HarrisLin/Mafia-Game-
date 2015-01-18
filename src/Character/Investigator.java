package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;
import GameEngine.GameEngine;

/**
 * The detective investigates a single Player target to discover his/her role
 */
public class Investigator extends Character{

	Investigator() {
		super(Roles.Detective);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// The detective can only target a single Player
		if (targets.size() != 1 ) {
			return false;
		}
		this.targets = targets;
		return true;
	}

	@Override
	public String doAction() {
		String target_name = targets.get(0).getName();
		String result =  GameEngine.getCharacter(targets.get(0)).getCharacterRoll()	;
		return "Player " + target_name + " is a " + result + ".";
	}
	
	

}
