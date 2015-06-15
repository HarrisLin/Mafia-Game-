package org.mobcore.mafia.character;

import org.mobcore.mafia.engine.GameRegistration.Player;
import org.mobcore.mafia.character.CharacterFactory.Roles;
import org.mobcore.mafia.resouces.GameLog;

import java.util.List;

public class Vigilante extends Character {

	private int shots;

	protected Vigilante(Player player) {
		super(Roles.Vigilante, player);
		shots = 3;
	}

	@Override
	public boolean performAction(List<Player> alive_player, Character target) {
		if (this.character_status.isBlocked()) {
			shots--;
			result = GameLog.Character.BLOCKED(player)
					+ GameLog.Character.SHOTS_LEFT(player, shots);
			return true;
		}
		if (shots <= 0) {
			result = GameLog.Character.SHOTS_LEFT(player, shots);
			return true;
		}
		target.character_status.getVisitors().add(player);
		shots--;
		if (!target.character_status.isHealed()) {
			alive_player.remove(target.player);
			result = GameLog.Character.VIGILANTE_SUCCESS(player, shots);
			return true;
		} else {
			result = GameLog.Character.VIGILANTE_FAIL(player, shots);
			return true;
		}
	}
}
