package Character;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;

public class Witch extends Character {
	Witch() {
		super(Roles.Witch);
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
