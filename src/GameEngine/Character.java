package GameEngine;

import java.util.ArrayList;
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
	// Determines of character is doused or not
	private boolean doused;
	// List of possible investigation results for this character
	private List<Investigations> investigation;
	// For the lookout and detective class, this is a count for all the visitors
	// of the character that night (be sure to clear this every night)
	private List<Player> visitors;
	// Lists of target the player has chosen to perform night actions
	protected List<Player> targets;
	// Target the player has chosen to vote for lynch
	protected Player lynchTarget;
	// Random number generator for investigation or other purposes
	private Random indexGenerator;

	/**
	 * Constructor for the character with default game options
	 * 
	 * @param role
	 *            : Role of the player (ex. Detective)
	 */
	protected Character(Roles role) {
		this.role = role;
		alive = true;
		doused = false;
		invulnerable = false;
		investigation = Investigations.doInvestigation(role);
		visitors = new ArrayList<Player>();
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
		doused = false;
		this.invulnerable = invulnerable;
		investigation = Investigations.doInvestigation(role);
		visitors = new ArrayList<Player>();
	}

	/**
	 * **UNSAFE METHOD** Used to change targets of character via bus driver and
	 * other character that require to modify target list 
	 * 
	 * @return list of night action targets of the player
	 */
	public List<Player> getTargets() {
		return targets;
	}

	/**
	 * 
	 * @param lynches
	 * @return TRUE if lynch target selected successfully or FALSE if not
	 */
	public boolean vote(Player lynchVote) {
		if (GameEngine.getCharacter(lynchVote).checkAlive()) {
			lynchTarget = lynchVote;
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return target player of which this character wishes to lynch
	 */
	public Player getLynchTarget() {
		return lynchTarget;
	}
	/**
	 * Kills the character; s/he will no longer be alive return TRUE if killed
	 * and FALSE if not killed
	 */
	public boolean kill() {
		if (!invulnerable) {
			alive = false;
			return true;
		}
		return false;
	}

	/**
	 * Douses the target
	 * @return TRUE
	 */
	public boolean douse() {
		return doused = true;
	}
	
	/**
	 * 
	 * @return TRUE if character is doused and FALSE if not
	 */
	public boolean isDoused() {
		return doused;
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
		indexGenerator = new Random();
		int index = indexGenerator.nextInt(investigation.size());
		investigate = investigation.get(index);
		return investigate.toString().toLowerCase();
	}

	/**
	 * Is this bug safe?
	 * 
	 * @return the players who have visited this character that night
	 */
	public List<Player> getVistors() {
		return new ArrayList<Player>(visitors);
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
