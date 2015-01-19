package Character;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;

public class Vigilante extends Character {
	Vigilante() {
		super(Roles.Vigilante);
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
