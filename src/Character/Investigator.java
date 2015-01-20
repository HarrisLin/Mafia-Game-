package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;
import GameEngine.GameEngine;
import GameOptions.InvestigatorOptions.DetectRole;

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
		this.targets = new ArrayList<Player>(targets);
		return true;
	}

	@Override
	public String doAction() {
		GameEngine.getCharacter(targets.get(0)).addVisitor(getPlayer());
		String result;
		GameOptions.InvestigatorOptions.DetectRole detect = (DetectRole) detectsRole;
		switch (detect) {
		case DETECT_EXACT_ROLE:
			result = "The result of your investigation yeilded a role of "
					+ GameEngine.getCharacter(targets.get(0)).getRoleString()
					+ ".";
			break;
		case DETECT_VAGUE_ROLE:
			result = "The result of your investigation suggests a role guilty of "
					+ GameEngine.getCharacter(targets.get(0))
							.getInvestigation() + ".";
			break;
		default:
			result = GameEngine.getCharacter(targets.get(0)).getInvestigation()
					+ ".";
		}
		return result;
	}
}
