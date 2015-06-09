package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.GameEngine;
import GameEngine.Player;
import Resources.GameMessage;

public class Mafioso extends Character {

	private static final int MAFIOSO_MAX_NUM_TARGETS = 1;

	public Mafioso(Player player) {
		super(Roles.Mafioso, player);
	}

	/**
	 * The Mafioso can only take in a single target.
	 */
	@Override
	public boolean setTarget(List<Player> targets) {
		if (targets.size() > MAFIOSO_MAX_NUM_TARGETS
				|| !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		return setTargets(targets);
	}

	/**
	 * The Mafioso simply attempts to kill his target.
	 */
	@Override
	public String doAction() {
		if (getTargets().isEmpty()) {
			return GameMessage.NO_ACTION;
		}

		if (isRoleBlocked()) {
			return GameMessage.ROLE_BLOCKED;
		}

		Player victim = getTargets().get(0);
		GameEngine.getCharacter(victim).kill();
		return GameMessage.KILL_ATTEMPT(victim);
	}
}