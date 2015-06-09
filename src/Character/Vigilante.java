package Character;

import java.util.List;

import Character.CharacterFactory.Roles;
import GameEngine.GameRegistration.Player;
import Resources.GameMessage;

public class Vigilante extends Character {

	private int shots;

	protected Vigilante(Player player) {
		super(Roles.Vigilante, player);
		shots = 3;
	}

	public String performAction(List<Player> alive_player, Character target) {
		if (this.character_status.isBlocked()) {
			shots--;
			return GameMessage.Character.BLOCKED(this.player)
					+ GameMessage.Character.SHOTS_LEFT(shots);
		}
		if (shots <= 0) {
			return GameMessage.Character.SHOTS_LEFT(shots);
		}
		target.character_status.getVisitors().add(player);
		shots--;
		if (!target.character_status.isHealed()) {
			alive_player.remove(target.player);
			return GameMessage.Character.VIGILANTE_SUCCESS(shots);
		} else {
			return GameMessage.Character.VIGILANTE_FAIL(shots);
		}
	}

	@Override
	public String performAction(Character target) {
		// TODO Auto-generated method stub
		return null;
	}
}
