package Character;

import Enumerators.Roles;
import GameEngine.CannotGetPlayerException;
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
	public String doAction() throws CannotGetPlayerException {
		
		if (getTarget().size() != 1) {
			return GameMessage.NO_ACTION();
		}
		
		Player target = getTarget().get(0);
		
		if(!GameEngine.getCharacter(target).isAlive()) {
			return GameMessage.TARGET_DEAD();
		}
		if (this.isRoleBlocked()) {
			return GameMessage.NO_FEEDBACK();
		}
		
		GameEngine.getCharacter(target).addVisitor(getPlayer());
		
		DetectRole detect = (DetectRole) detectsRole;
		
		switch (detect) {
		case DETECT_EXACT_ROLE:
			return GameMessage.INVESTIGATION_EXACT(target);
		case DETECT_VAGUE_ROLE:
			return GameMessage.INVESTIGATION_VAGUE(target);
		default:
			return GameMessage.INVESTIGATION_VAGUE(target);
		}
	}
}
