package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;
import GameEngine.GameEngine;
import GameOptions.GameOptions;
import GameOptions.NoSuchOptionException;

/**
 * The detective investigates a single Player target to discover his/her role
 */
public class Investigator extends Character {
	// game options
	boolean detectsExactRole;

	Investigator() {
		super(Roles.Detective);
		try {
			detectsExactRole = GameOptions.getOptions(this.getCharacterRole(),
					1);
		} catch (NoSuchOptionException e) {
			detectsExactRole = false;
		}
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// The detective can only target a single Player
		if (targets.size() != 1) {
			return false;
		}
		this.targets = targets;
		return true;
	}

	@Override
	public String doAction() {
		String result;
		if (detectsExactRole) {
			result = GameEngine.getCharacter(targets.get(0)).getRoleString();
		} else {
			//**NOT IMPLIMENTED YET**
			// if escort/consort/liaison DOES NOT block a town member && target
			// role is escort/consort/liaison, result = soliciting
			result = GameEngine.getCharacter(targets.get(0)).getInvestigation();
		}
		return "The result of your investigation yeilded a role of " + result + ".";
	}

}
