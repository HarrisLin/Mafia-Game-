package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameMessage;
import GameEngine.Player;
import GameEngine.GameEngine;
import GameOptions.InvestigatorOptions.DetectRole;

// *****************************************************************
// DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE
// this is a tag for all the characters/classes that are done(｡◕‿◕｡)
// *****************************************************************

/**
 * The detective investigates a single Player target to discover his/her role
 */
public class Investigator extends Character {
	// game options
	private final GameOptions.Options detectsRole;

	/**
	 * Investigator Default constructor. The default setting for an Investigator
	 * is that the exact role of his target is revealed.
	 */
	public Investigator(Player player) {
		super(Roles.Investigator, player);
		detectsRole = GameOptions.InvestigatorOptions.DetectRole.DETECT_VAGUE_ROLE;
	}

	/**
	 * Investigator. Game Option constructor.
	 * 
	 * @param gameOption
	 *            - Investigator.Options.DETECT_EXACT_ROLE: the investigator
	 *            will discover the exact role of his target -
	 *            Investigator.Options.DETECT_VAGUE_ROLE: the investigator will
	 *            discover a hint for the role of his target
	 */
	Investigator(Player player, GameOptions.Options detectsRole) {
		super(Roles.Investigator, player);
		this.detectsRole = detectsRole;
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// The detective can only target a single Player
		if (targets.size() != 1
				|| !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		return setTargets(targets);
	}

	@Override
	public String doAction() {
		List<Player> targets = getTargets();
		if (targets.isEmpty()) {
			return GameMessage.NO_ACTION;
		}
		if (this.isRoleBlocked()) {
			return GameMessage.NO_RESULT;
		}
		GameEngine.getCharacter(targets.get(0)).addVisitor(getPlayer());
		DetectRole detect = (DetectRole) detectsRole;
		Character target = GameEngine.getCharacter(targets.get(0));
		String message;
		switch (detect) {
		case DETECT_EXACT_ROLE:
			message = target.getRoleString();
			message = GameMessage.INVESTIGATION_EXACT(message);
			break;
		case DETECT_VAGUE_ROLE:
			message = target.getInvestigation();
			message = GameMessage.INVESTIGATION_VAGUE(message);
			break;
		default:
			message = target.getInvestigation();
			message = GameMessage.INVESTIGATION_VAGUE(message);
		}
		return message;
	}
}
