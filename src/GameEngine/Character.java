package GameEngine;

import java.util.List;

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
	// Determines the investigation of a player via detective
	private List<Investigations> investigation;
	// Lists of target the player has chosen to perform night actions on
	protected List<Player> targets;

	/**
	 * Constructor for the character with default game options
	 * 
	 * @param role
	 *            : Role of the player (ex. Detective)
	 */
	protected Character(Roles role) {
		this.role = role;
		alive = true;
		invulnerable = role.checkInvulnerable();
		investigation = role.getInvestigations();
	}

	/**
	 * Constructor for the character with modified game options
	 * 
	 * @param role
	 *            : Role of the player (ex. Detective)
	 * @param invulnerable
	 *            : Night invulnerability On/Off
	 * @param suspicious
	 *            : Suspicious Yes/No
	 * @param investigation
	 *            : Investigation message
	 */
	protected Character(Roles role, boolean invulnerable,
			List<Investigations> investigation) {
		this.role = role;
		alive = true;
		this.invulnerable = invulnerable;
		this.investigation = investigation;
	}

	/**
	 * Kills the character; s/he will no longer be alive
	 */
	public void kill() {
		alive = false;
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
	 * Investigates this player
	 * 
	 * @return the investigation message
	 */
	public List<Investigations> investigate() {
		return investigation;
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
