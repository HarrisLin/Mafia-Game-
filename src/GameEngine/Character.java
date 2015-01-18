package GameEngine;

import java.util.List;
import java.util.Random;

import Enumerators.Investigations;
import Enumerators.Roles;

/**
 * Character in the game
 * 
 * @author pacified
 *
 */
public abstract class Character {
	// Role of the character (ex. Detective)
	private Roles role;
	// Determines if the player is still alive in game
	private boolean alive;
	// Determines if character is night immune
	private boolean invulnerable;
	//List of possible investigation results for this character
	private List<Investigations> investigation;
	// Lists of target the player has chosen to perform night actions on
	protected List<Player> targets;
	//Random number generator for investigation or other purposes
	Random indexGenerator;
	/**
	 * Constructor for the character with default game options
	 * 
	 * @param role
	 *            : Role of the player (ex. Detective)
	 */
	protected Character(Roles role) {
		this.role = role;
		alive = true;
		invulnerable = false;
		investigation = Investigations.doInvestigation(role);
	}

	/**
	 * Constructor for the character with modified game options
	 * 
	 * @param role
	 *            : Role of the player (ex. Detective)
	 * @param invulnerable
	 *            : Night invulnerability On/Off
	 */
	protected Character(Roles role, boolean invulnerable) {
		this.role = role;
		alive = true;
		this.invulnerable = invulnerable;
		investigation = Investigations.doInvestigation(role);
	}

	/**
	 * Kills the character; s/he will no longer be alive
	 * return TRUE if killed and FALSE if not killed
	 */
	public boolean kill() {
		if(!invulnerable) {
			alive = false;
			return true;
		}
		return false;
	}

	/**
	 * Check if player is alive or dead
	 * 
	 * @return true if alive, false if dead
	 */
	public boolean checkAlive() {
		return alive;
	}

	/**
	 * Check if player is invulnerable
	 * 
	 * @return true if invulnerable, false if not
	 */
	public boolean checkInvulnerable() {
		return invulnerable;
	}
	
	/**
	 * 
	 * @return a random investigation of possible investigations
	 */
	public String getInvestigation() {
		Investigations investigate;
		int index = indexGenerator.nextInt(investigation.size());
		investigate = investigation.get(index);
		return investigate.toString().toLowerCase();
	}
	/**
	 * 
	 * @return the name of the roll (ex. Detective)
	 */
	public String getRoleString() {
		return role.toString();
	}

	/**
	 * 
	 * @return the roll of the character
	 */
	public Roles getCharacterRole() {
		return role;
	}

	/**
	 * 
	 * @param targets
	 * @return
	 */
	public abstract boolean setTarget(List<Player> targets);

	/**
	 * 
	 * @return
	 */
	public abstract String doAction();
}
