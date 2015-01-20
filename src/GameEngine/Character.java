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
	// *******************************************************************
	// SHARED INSTANCES THAT ARE ESSENTIAL TO CHARACTER AND GAME MECHANICS
	// *******************************************************************
	// Role of the character (ex. Detective)
	private Roles role;
	// Player of the character (ex. Harris Pheg)
	private Player player;
	// Determines if the player is still alive in game
	private boolean alive;
	// Determines if character is night immune
	private boolean invulnerable;
	// For the lookout and detective class, this is a count for all the visitors
	// of the character that night (be sure to clear this every night)
	private List<Player> visitors;
	// Lists of target the player has chosen to perform night actions
	protected List<Player> targets;
	// Target the player has chosen to vote for lynch
	protected Player lynchTarget;
	// *********************************************************
	// THE FOLLOW INSTANCES ARE REQUIRED FOR PARTICULAR CLASSES
	// *********************************************************
	// Determines of character is doused or not
	private boolean doused;
	// List of possible investigation results for this character
	private List<Investigations> investigation;
	// Random number generator for investigation or other purposes
	private Random indexGenerator;

	// ************************************
	// find constructor
	// CONSTRUCTOR METHODS FOR THIS CLASS
	// ************************************
	/**
	 * Constructor for the character with default game options
	 * 
	 * @param role
	 *            : Role of the player (ex. Detective)
	 */
	protected Character(Roles role, Player player) {
		this.role = role;
		this.player = player;
		alive = true;
		invulnerable = false;
		visitors = new ArrayList<Player>();
		doused = false;
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
	protected Character(Roles role, Player player, boolean invulnerable) {
		this.role = role;
		this.player = player;
		alive = true;
		this.invulnerable = invulnerable;
		visitors = new ArrayList<Player>();
		doused = false;
		investigation = Investigations.doInvestigation(role);	
	}

	// *****************************************
	// find temp
	// METHODS THAT SHOULD BE CHANGED OR REVISED
	// *****************************************
	/**
	 * **UNSAFE METHOD** Used to change targets of character via bus driver and
	 * other character that require to modify target list
	 * 
	 * Rather than using this method, the character role should have a separate
	 * personal method (like getInvestigation) in this class that will modify
	 * the list but will not leak the list.
	 * 
	 * @return list of night action targets of the player
	 */
	public List<Player> getTargets() {
		return targets;
	}

	// *********************************
	// find role
	// METHODS THAT HAVE TO DO WITH ROLE
	// *********************************
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

	// *************************************
	// find player
	// METHODS THAT HAVE TO DO WITH PLAYER
	// *************************************
	/**
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

	// ********************************************************
	// find kill
	// METHODS THAT HAVE TO DO WILL KILLING AND NIGHT IMMUNITY
	// ********************************************************
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
	 * Check if player is alive or dead
	 * 
	 * @return true if alive, false if dead
	 */
	public boolean checkAlive() {
		return alive;
	}

	/**
	 * Check if player is invulnerable (might not be a needed/used method)
	 * 
	 * @return true if invulnerable, false if not
	 */
	public boolean checkInvulnerable() {
		return invulnerable;
	}

	// ********************************************************************
	// find visitor
	// VISITOR METHODS AND METHODS FOR ROLES THAT HAS TO DO WITH VISITORS
	// ********************************************************************
	/**
	 *
	 * @return the players who have visited this character that night
	 */
	public List<Player> getVistors() {
		return new ArrayList<Player>(visitors);
	}

	/**
	 * 
	 * @param player
	 *            who visited this character
	 * @return TRUE
	 */
	public boolean addVisitor(Player player) {
		visitors.add(player);
		return true;
	}

	/**
	 * Clears visitor list
	 */
	public void clearVisitor() {
		visitors.clear();
	}

	// *************************************
	// find vote
	// METHODS THAT HAVE TO DO WITH VOTING
	// *************************************
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

	// ************************************************************************
	// find special
	// THE FOLLOW SECTION IS FOR ROLE METHODS THAT ARE SPECIFICALLY USED TO THE
	// ROLE
	// ************************************************************************

	// **********************************************************
	// find investigator
	// INVESTIGATOR METHOD THAT GETS VAGUE INVESGITATOR RESULTS
	// **********************************************************
	/**
	 * @return a random investigation of possible investigations
	 */
	public String getInvestigation() {
		Investigations investigate;
		indexGenerator = new Random();
		int index = indexGenerator.nextInt(investigation.size());
		investigate = investigation.get(index);
		return investigate.toString().toLowerCase();
	}

	// ***********************************************
	// find arsonist
	// ARSONIST METHODS THAT HAVE TO DO WITH DOUSING
	// ***********************************************
	/**
	 * Douses the target
	 * 
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

	// *******************************
	// find abstract
	// THE FOLLOW ARE ABSTRACT CLASSES
	// *******************************
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
