package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;
import GameEngine.GameEngine;

/**
 * The detective investigates a single Player target to discover his/her role
 */
public class Investigator extends Character {
	// game options
	private final Investigator.DetectRoleOption detectsExactRole;

	/**
	 * Investigator.
	 * Game Option constructor.
	 * @param gameOption - Investigator.Options.DETECT_EXACT_ROLE: the investigator will discover the exact role of his target
	 *                    - Investigator.Options.DETECT_VAGUE_ROLE: the investigator will discover a hint for the role of his target
	 */
	Investigator(Investigator.DetectRoleOption gameOptionDetectRole) {
		super(Roles.Investigator);
		detectsExactRole = gameOptionDetectRole;
	}
	
	/**
	 *  Investigator
	 *  Default constructor. The default setting for an Investigator is that
	 *  the exact role of his target is revealed.
	 */
	public Investigator() {
		super(Roles.Investigator);
		detectsExactRole = Investigator.DetectRoleOption.DETECT_VAGUE_ROLE;
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
		switch(detectsExactRole) {
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
	
	/**
	 * The game options for Investigators
	 */
	public static enum DetectRoleOption {
		DETECT_EXACT_ROLE, DETECT_VAGUE_ROLE
	}
}
