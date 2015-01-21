package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameMessage;
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
	 * Default constructor. The default setting for a consigliere is that the
	 * vague role of his target is revealed.
	 */
	public Consigliere(Player player) {
		super(Roles.Investigator, player);
		detectsRole = GameOptions.ConsigliereOptions.DetectRole.DETECT_VAGUE_ROLE;
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
		if(targets.isEmpty()) {
			return GameMessage.NO_ACTION;
		}
		String result;
		GameOptions.ConsigliereOptions.DetectRole detect = (DetectRole) detectsRole;
		switch (detect) {
		case DETECT_EXACT_ROLE:
			result = GameEngine.getCharacter(targets.get(0)).getRoleString();
			break;
		case DETECT_VAGUE_ROLE:
			result = GameEngine.getCharacter(targets.get(0)).getInvestigation();
			break;
		default:
			result = GameEngine.getCharacter(targets.get(0)).getInvestigation();
		}
		return "The result of your investigation yeilded a role of " + result
				+ ".";
	}
}
