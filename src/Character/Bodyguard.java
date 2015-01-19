package Character;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;

/**
 * 
 * @author pacified
 *
 */
public class Bodyguard extends Character {

	Bodyguard() {
		super(Roles.Bodyguard);
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
