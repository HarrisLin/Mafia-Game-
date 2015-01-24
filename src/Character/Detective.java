package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.GameMessage;
import GameEngine.Player;

public class Detective extends Character {
	Detective(Player player) {
		super(Roles.Detective, player);
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
		if (targets.isEmpty()) {
			return GameMessage.NO_ACTION;
		}
		String message = "Cannot get your results";
		if (this.isRoleBlocked()) {
			return message;
		}
		GameEngine.getCharacter(targets.get(0)).addVisitor(getPlayer());
		
		// Retrieve the list of who Detective Target visited
		List<Player> tracker = GameEngine.getCharacter(targets.get(0)).getTargets();
		
		
		//TODO: Include option for ignoring night immunity if detective
		//      For balance considerations: when tailing ARSONIST and he/she
		//          ignites, should we return everyone ignited, or "inactive"
		//          and for Bus Driver, should we only say first person visited?
		String result = "Your target visited ";
		for ( int i = 0; i < tracker.size(); i++ ) {
			result = result + tracker.get(i).toString() + ",";
		}
		
		return result;
	}
}
