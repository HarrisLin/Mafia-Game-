package Character;

import java.util.List;

import Character.CharacterFactory.Roles;
import GameEngine.GameRegistration.Player;
import Resources.GameLog;

public class Doctor extends Character {

	protected Doctor(Player player) {
		super(Roles.Doctor, player);
	}

	@Override
	public boolean performAction(List<Player> alive_player, Character target) {
		if(this.character_status.isBlocked()) {
			result = GameLog.Character.BLOCKED(player);
			return true;
		}
		target.character_status.getVisitors().add(player);
		target.character_status.heal();
		result = GameLog.Character.DOCTOR_SUCCESS(player, target.player);
		return true;
	}
}
