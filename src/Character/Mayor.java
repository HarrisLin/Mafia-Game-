package Character;

import java.util.List;

import Character.CharacterFactory.Roles;
import GameEngine.GameRegistration.Player;
import Resources.GameMessage;

public class Mayor extends Character {
	private boolean revealed;
	private final int vote_count;
	
	protected Mayor(Player player) {
		super(Roles.Mayor, player);
		result = GameMessage.Character.MAYOR_NOT_REVEALED(player);
		revealed = false;
		vote_count = 3;
	}
	
	/**
	 * Reveals mayor
	 */
	public boolean reveal() {
		result = GameMessage.Character.MAYOR_REVEALED(player);
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
	public boolean performAction(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean performAction(List<Player> alive_player, Character target) {
		// TODO Auto-generated method stub
		return false;
	}
}
