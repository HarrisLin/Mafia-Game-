package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;

public class Detective extends Character {
	Detective(Player player) {
		super(Roles.Detective, player);
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
