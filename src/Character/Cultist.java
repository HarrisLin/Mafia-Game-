package Character;

import java.util.List;

import GameEngine.Character;
import GameEngine.Player;
import Enumerators.Roles;

public class Cultist extends Character {

	Cultist() {
		super(Roles.Cultist);
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
