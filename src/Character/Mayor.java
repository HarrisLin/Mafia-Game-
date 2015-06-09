package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;
import Resources.GameMessage;

//****************************************************************
//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE
//this is a tag for all the characters/classes that are done
//*****************************************************************

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

	public Mayor(Player player) {
		super(Roles.Mayor, player);
		revealed = false;
		vote_count = 3;
	}

	/**
	 * 
	 * @return vote count
	 */
	public int getRevealed() {
		if (revealed) {
			return vote_count;
		} else {
			return 1;
		}
	}

	/**
	 * 
	 * @param lynches
	 * @return TRUE if lynch target selected successfully or FALSE if not
	 */
	@Override
	public boolean vote(List<Player> lynchVote) {
		if (lynchVote.size() != 1) {
			return false;
		}
		
		try {
			if (GameEngine.getCharacter(lynchVote.get(0)).isAlive()) {
				if(revealed) {
					lynchTarget = new ArrayList<Player>();
					lynchTarget.add(lynchVote.get(0));
					lynchTarget.add(lynchVote.get(0));
					lynchTarget.add(lynchVote.get(0));
					return true;
				}
				else {
					lynchTarget = lynchVote;
				}
				return true;
			}
		} catch (CannotGetPlayerException e) {
			return false;
		}
		return false;
	}
	
	/**
	 * The Mayor targets oneself to reveal itself
	 */
	@Override
	public boolean setTarget(List<Player> targets) {
		// bus driver must only target 2 people
		if (targets.size() != 1) {
			return false;
		}
		try {
			if (!GameEngine.getCharacter(targets.get(0)).isAlive()) {
				return false;
			}
			if (!GameEngine.getCharacter(targets.get(0)).equals(this)) {
				return false;
			}
		} catch (CannotGetPlayerException e) {
			System.out.println(GameMessage.NO_CHARACTER(targets.get(0),
					targets.get(1)));
			return false;
		}
		return revealed = true;
	}
	
	@Override
	public String doAction() {
		// TODO Auto-generated method stub
		return null;
	}
}
