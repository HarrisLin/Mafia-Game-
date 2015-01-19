package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;
import GameEngine.GameEngine;

public class Mayor extends Character {

	private boolean revealed;
	private final int vote_count;

	Mayor() {
		super(Roles.Mayor);
		getRevealed(false);
		vote_count = 3;
	}

	@Override
	public boolean vote(List<Player> lynches) {
		if (getRevealed(false)) {
			if (lynches.size() != 1) {
				return false;
			}
			this.lynches = lynches;
			return true;
		} else if (getRevealed(true)) {
			if (targets.size() != vote_count) {
				return false;
			}
			for (int i = 0; i < vote_count; i++)
				this.lynches = lynches;
			return true;
		} else
			return false;
	}

	public boolean Revealed() {
		revealed = true;
		return revealed;
	}

	public boolean getRevealed(boolean revealed) {
		this.revealed = revealed;
		return revealed;
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
