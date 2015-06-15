package org.mobcore.mafia.character;

import org.mobcore.mafia.engine.GameRegistration.Player;
import org.mobcore.mafia.character.CharacterFactory.Roles;
import org.mobcore.mafia.resouces.GameLog;

import java.util.List;

public class Mayor extends Character {
	private boolean revealed;
	private final int vote_count;
	
	protected Mayor(Player player) {
		super(Roles.Mayor, player);
		result = GameLog.Character.MAYOR_NOT_REVEALED(player);
		revealed = false;
		vote_count = 3;
	}
	
	/**
	 * Reveals mayor
	 */
	public boolean reveal() {
		result = GameLog.Character.MAYOR_REVEALED(player);
		return revealed = true;
	}
	/**
	 * @return true if revealed, else false
	 */
	public boolean isRevealed() {
		return revealed;
	}
	
	public int getVoteCount() {
		return vote_count;
	}
	
	@Override
	public boolean performAction(List<Player> alive_player, Character target) {
		// TODO Auto-generated method stub
		return false;
	}
}
