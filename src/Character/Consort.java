package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

/**
 * Blocks one player from performing their night actions.
 * 
 * @author pacified
 *
 */
public class Consort extends Character {

	Consort(Player player) {
		super(Roles.Escort, player);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() != 1
				|| !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		this.targets = new ArrayList<Player>(targets);
		return true;
	}

	/**
	 * Removes target's targets
	 */
	@Override
	public String doAction() {
		GameEngine.getCharacter(targets.get(0)).getTargets().clear();
		return "Thank you for performing your action you filthy whore.";
	}
}