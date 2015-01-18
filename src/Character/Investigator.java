package Character;

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
	private final Investigator.OptionDetectRole detectsExactRole;
	private final Investigator.OptionKnowPerson detectsTargetName;

	/**
	 * Investigator.
	 * Game Option constructor.
	 * @param gameOption - Investigator.Options.DETECT_EXACT_ROLE: the investigator will discover the exact role of his target
	 *                    - Investigator.Options.DETECT_VAGUE_ROLE: the investigator will discover a hint for the role of his target
	 */
	Investigator(Investigator.OptionDetectRole gameOptionDetectRole, Investigator.OptionKnowPerson gameOptionKnowPerson) {
		super(Roles.Investigator);

		detectsExactRole = gameOptionDetectRole;
		detectsTargetName = gameOptionKnowPerson;
	}
	
	/**
	 *  Investigator
	 *  Default constructor. The default setting for an Investigator is that
	 *  the exact role of his target is revealed.
	 */
	Investigator() {
		super(Roles.Investigator);
		detectsExactRole = Investigator.OptionDetectRole.DETECT_VAGUE_ROLE;
		detectsTargetName = Investigator.OptionKnowPerson.TARGET_NAME_NOT_GIVEN;
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
	
	/**
	 * The game options for Investigators
	 */
	public static enum OptionDetectRole {
		DETECT_EXACT_ROLE, DETECT_VAGUE_ROLE, DETECT_LIST_OF_ROLE
	}
	
	public static enum OptionKnowPerson {
		TARGET_NAME_GIVEN, TARGET_NAME_NOT_GIVEN
	}
}
