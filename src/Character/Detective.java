package Character;

import java.util.List;

import GameEngine.Character;
import GameEngine.Player;
import GameEngine.GameEngine;

public class Detective extends Character{

	Detective(String player) {
		super(player, Roles.Detective);
	}

	/**
	 * The detective investigates a single Player target to discover his/her role
	 */
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
		String result =  GameEngine.getCharacter(targets.get(0)).getCharacterName();
		return "Player " + target_name + " is a " + result + ".";
	}
	
	

}
