package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;

/**
 * Mayor is part of the town that has power to higher weighted vote when
 * revealed
 * 
 * @author kaibro
 *
 */
public class Mayor extends Character {

	private boolean revealed;
	private final int vote_count;

	Mayor() {
		super(Roles.Mayor);
		revealed = false;
		vote_count = 3;
	}

	public boolean Revealed() {
		revealed = true;
		return revealed;
	}

	/**
	 * 
	 * @return vote count
	 */
	public int getRevealed() {
		if(revealed) {
			return vote_count;
		}
		else {
			return 1;
		}
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
	
	public static enum VoteCountOptions {
		VOTE_COUNT_2, VOTE_COUNT_3, VOTE_COUNT_4
	}
}
