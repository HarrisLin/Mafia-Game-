package GameEngine;

import java.util.List;

import Character.Roles;
/**
 * Characters in the game with a role and name of player
 * @author pacified
 *
 */
public abstract class Character {
	private String name;
	private Roles role;
	private boolean alive;
	
	protected List<Player> targets;

	/**
	 * Constructor for the character
	 * @param name : Name of the player (ex. Harris Pheg)
	 * @param role : Role of the player (ex. Detective)
	 */
	protected Character(String name, Roles role){
		this.name = name;
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
	 * @return the name of the player (ex. Harris Pheg)
	 */
	public String getCharacterName() {
		return name;
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
