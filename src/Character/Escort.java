package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;
/**
 * Blocks one player from performing their night actions.
 * @author pacified
 *
 */
public class Escort extends Character {

	Escort() {
		super(Roles.Escort);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() != 1) {
			return false;
		}
		this.targets = targets;
		return true;
	}

	@Override
	public String doAction() {
		GameEngine.getCharacter(targets.get(0)).getTargets().clear();
		return "Thank you for performing your action you filthy whore";
	}
}
