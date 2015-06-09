package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.CannotGetPlayerException;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.GameMessage;
import GameEngine.Player;
/**
 * Detective check who one character visited that night
 * @author Eleanor Wong
 *
 */
public class Detective extends Character {
	public Detective(Player player) {
		super(Roles.Detective, player);
	}

	@Override
	public String doAction() throws CannotGetPlayerException {
		
		if (getTarget().size() != 1) {
			return GameMessage.NO_ACTION;
		}
		
		Player target = getTarget().get(0);
		
		if(!GameEngine.getCharacter(target).isAlive()) {
			return GameMessage.TARGET_DEAD;
		}
		if (this.isRoleBlocked()) {
			return GameMessage.ROLE_BLOCKED;
		}
		
		GameEngine.getCharacter(target).addVisitor(getPlayer());
		
		// Retrieve the list of who Detective Target visited
		List<Player> tracker = GameEngine.getCharacter(target).getTarget();
		
		return GameMessage.DETECTIVE_FEEDBACK(tracker);
	}
}
