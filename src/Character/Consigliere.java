package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;
import GameEngine.GameEngine;
import GameOptions.ConsigliereOptions.DetectRole;

/**
 * The consigliere investigates a single Player target to discover his/her role
 */
public class Consigliere extends Character {
	// game options
	private final GameOptions.Options detectsRole;

	/**
	 *  Investigator
	 *  Default constructor. The default setting for an Investigator is that
	 *  the exact role of his target is revealed.
	 */
	public Consigliere() {
		super(Roles.Investigator);
		detectsRole = GameOptions.ConsigliereOptions.DetectRole.DETECT_VAGUE_ROLE;
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// The detective can only target a single Player
		if (targets.size() != 1
				|| !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		this.targets = new ArrayList<Player>(targets);
		return true;
	}

	@Override
	public String doAction() {
		String result;
		GameOptions.ConsigliereOptions.DetectRole detect = (DetectRole) detectsRole;
		switch(detect) {
		case DETECT_EXACT_ROLE:
			result = GameEngine.getCharacter(targets.get(0)).getRoleString();
			break;
		case DETECT_VAGUE_ROLE:
			result = GameEngine.getCharacter(targets.get(0)).getInvestigation();
			break;
		default:
			result = GameEngine.getCharacter(targets.get(0)).getInvestigation();
		}
		return "The result of your investigation yeilded a role of " + result + ".";
	}
}
