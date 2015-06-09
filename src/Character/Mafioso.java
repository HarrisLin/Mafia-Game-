package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.CannotGetPlayerException;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.GameMessage;
import GameEngine.Player;

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
		if (targets.size() > MAFIOSO_MAX_NUM_TARGETS) {
			return false;
		}
		return super.setTarget(targets);
	}

	/**
	 * The Mafioso simply attempts to kill his target.
	 */
	@Override
	public String doAction() {
		if (getTarget().isEmpty()) {
			return GameMessage.NO_ACTION;
		}

		if (isRoleBlocked()) {
			return GameMessage.ROLE_BLOCKED;
		}

		Player victim = getTarget().get(0);
		try {
			GameEngine.getCharacter(victim).kill();
		} catch (CannotGetPlayerException e) {}
		return GameMessage.KILL_ATTEMPT(victim);
	}
}