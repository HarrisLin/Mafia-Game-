package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;

/**
 * The Godfather targets a single player to kill and has night immunity.
 */
public class Godfather extends Character {

	Godfather() {
		super(Roles.Godfather);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String doAction() {
		// TODO Auto-generated method stub
		return null;
	}
}
