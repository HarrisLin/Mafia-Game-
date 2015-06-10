package Character;

import java.util.List;

import Character.CharacterFactory.Roles;
import GameEngine.GameRegistration.Player;
import Resources.GameMessage;

public class Doctor extends Character {

	protected Doctor(Player player) {
		super(Roles.Doctor, player);
	}

	public boolean performAction(Character target) {
		if(this.character_status.isBlocked()) {
			result = GameMessage.Character.BLOCKED(player);
			return true;
		}
		target.character_status.getVisitors().add(player);
		target.character_status.heal();
		result = GameMessage.Character.DOCTOR_SUCCESS(player, target.player);
		return true;
	}

	@Override
	public boolean performAction(List<Player> alive_player, Character target) {
		// TODO Auto-generated method stub
		return false;
	}
}
