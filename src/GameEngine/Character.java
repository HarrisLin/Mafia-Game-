package GameEngine;

import java.util.List;

import Character.Roles;
/**
 * Characters in the game with a role and name of player
 * @author pacified
 *
 */
public abstract class Character {
	private Roles role;
	private boolean alive;
	
	protected List<Player> targets;

	/**
	 * Constructor for the character
	 * @param role : Role of the player (ex. Detective)
	 */
	protected Character(Roles role){
		this.role = role;
		alive = true;
	}
	
	/**
	 * Kills the character; s/he will no longer be alive
	 */
	public void kill() {
		alive = false;
	}
	/**
	 *  Check if player is alive or dead
	 * @return true if alive, false if dead
	 */
	public boolean checkAlive() {
		return alive;
	}
	
	/**
	 * 
	 * @return the name of the roll (ex. Detective)
	 */
	public String getCharacterRoll() {
		return role.toString();
	}
	
	public abstract boolean setTarget(List<Player> targets);
	public abstract String doAction();
}
