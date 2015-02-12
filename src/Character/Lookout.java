package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.GameMessage;
import GameEngine.Player;

public class Lookout extends Character {
	public Lookout(Player player) {
		super(Roles.Lookout, player);
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
		List<Player> tracker = GameEngine.getCharacter(targets.get(0)).getVistors();
		
		
		//TODO: Include option for ignoring detection immunity
		//      Add exception that lookout does not die to veteran ***
		//      ALSO any bus driver exceptions
		String result = "Your target was visited by ";
		
		for ( int i = 0; i < tracker.size(); i++ ) {
			if ( !tracker.get(i).equals(this.getPlayer()) ) {
				result = result + tracker.get(i).getName().toString() + ",";
			}
			
		}
		
		return result;
	}
}
