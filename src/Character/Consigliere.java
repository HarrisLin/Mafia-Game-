package Character;

import Enumerators.Roles;
import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;
import GameOptions.ConsigliereOptions.DetectRole;
import Resources.GameMessage;

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
		super(Roles.Consigliere, player);
		detectsRole = GameOptions.ConsigliereOptions.DetectRole.DETECT_VAGUE_ROLE;
	}

	@Override
	public String doAction() throws CannotGetPlayerException {

		if (getTarget().size() != 1) {
			return GameMessage.NO_ACTION();
		}

		Player target = getTarget().get(0);

		if (!GameEngine.getCharacter(target).isAlive()) {
			return GameMessage.TARGET_DEAD();
		}
		if (this.isRoleBlocked()) {
			return GameMessage.NO_FEEDBACK();
		}

		GameEngine.getCharacter(target).addVisitor(getPlayer());

		GameOptions.ConsigliereOptions.DetectRole detect = (DetectRole) detectsRole;

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
