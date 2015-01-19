package Character;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;

/**
 * Arsonist douses and kills
 * @author pacified
 *
 */
public class Arsonist extends Character {
	Arsonist() {
		super(Roles.Arsonist, true);
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
