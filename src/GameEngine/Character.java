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
	private final Roles role;
	// Player of the character (ex. Harris Pheg)
	private final Player player;
	// Determines if character is night immune
	private final boolean invulnerable;
	// Determines if character is block immune (ex. Godfather)
	private final boolean blockImmune;
	// **********************************************
	// ABOVE ARE NOT MODIFIED AND BELOW ARE MODIFIED
	// **********************************************
	// Determines if the player is still alive in game
	private boolean alive;
	// For the lookout and detective class, this is a count for all the visitors
	// of the character that night (be sure to clear this every night)
	private List<Player> visitors;
	// Lists of target the player has chosen to perform night actions
	private List<Player> targets;
	// Target the player has chosen to vote for lynch
	private Player lynchTarget;
	// Last Will must be written before night
	private String lastWill;
	// *********************************************************
	// THE FOLLOW INSTANCES ARE REQUIRED FOR PARTICULAR CLASSES
	// *********************************************************
	// Determines of character is doused or not
	private boolean doused;
	// List of possible investigation results for this character
	private List<Investigations> investigation;
	// Random number generator for investigation or other purposes
	private Random indexGenerator;
	// If consort or escort blocks you roleBlocked is true. Requires doAction to
	// check if character is role blocked before start action
	private boolean roleBlocked;
	// If doctor visits, you are healed
	private boolean healed;

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
		blockImmune = false;
		targets = new ArrayList<Player>();
		visitors = new ArrayList<Player>();
		lastWill = "No last will.";
		doused = false;
		investigation = Investigations.doInvestigation(role);
		roleBlocked = false;
		healed = false;
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
		blockImmune = false;
		targets = new ArrayList<Player>();
		visitors = new ArrayList<Player>();
		lastWill = "No last will.";
		doused = false;
		investigation = Investigations.doInvestigation(role);
		roleBlocked = false;
		healed = false;
	}

	/**
	 * Constructor for the character with modified game options
	 * 
	 * @param role
	 *            : Role of the player (ex. Detective)
	 * @param invulnerable
	 *            : Night invulnerability On/Off
	 * @param blockImmune
	 *            : Immune to escort/consort On/Off
	 */
	protected Character(Roles role, Player player, boolean invulnerable,
			boolean blockImmune) {
		this.role = role;
		this.player = player;
		alive = true;
		this.invulnerable = invulnerable;
		this.blockImmune = blockImmune;
		targets = new ArrayList<Player>();
		visitors = new ArrayList<Player>();
		lastWill = "No last will.";
		doused = false;
		investigation = Investigations.doInvestigation(role);
		roleBlocked = false;
		healed = false;
	}
	
	// ***************************************************************
	// find new day
	// METHOD THAT RESETS THE VALUES OF THIS CHARACTER FOR A NEW DAY
	// ***************************************************************
	/**
	 * Clears and resets values for a new day for this character. Also returns a
	 * save data for achieve (not implemented yet).
	 * 
	 * @return a save data of this player as a string for archieve
	 */
	public String newDay() {
		String data = "No data";
		visitors.clear();
		targets.clear();
		lynchTarget = null;
		roleBlocked = false;
		healed = false;
		return data;

	}

	// *********************************
	// find role
	// METHODS THAT HAVE TO DO WITH ROLE
	// *********************************
	/**
	 * @return the name of the roll (ex. Detective)
	 */
	public String getRoleString() {
		return role.toString();
	}

	/**
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

	//************************************
	// find target
	// METHODS THAT HAVE TO DO WITH TARGET
	// ***********************************
	/**
	 * Set targets
	 * @param targets
	 * @return TRUE
	 */
	protected boolean setTargets(List<Player> targets) {
		this.targets = new ArrayList<Player>(targets);
		return true;
	}
	
	/**
	 * @return list of night action targets of the player
	 */
	public List<Player> getTargets() {
		return new ArrayList<Player>(targets);
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
		if (!invulnerable && !healed && alive) {
			GameEngine.killPlayer(getPlayer());
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

	// *************************************
	// find vote find lynch
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

	// ***********************************************
	// find last will
	// LAST WILL WILL BE CHANGED UPDATED OBTAINED HERE
	// ***********************************************
	/**
	 * Updates last will
	 * 
	 * @param lastWill
	 * @return TRUE
	 */
	public boolean updateLastWill(String lastWill) {
		this.lastWill = lastWill;
		return true;
	}

	/**
	 * gets last will
	 * 
	 * @return lastWill
	 */
	public String getLastWill() {
		return lastWill;
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
		return investigate.toString();
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
	protected boolean isDoused() {
		return doused;
	}

	// *********************************
	// find escort find consort
	// BLOCKING OR BLOCK IMMUNE METHODS
	// *********************************

	/**
	 * 
	 * @return TRUE if character is successfully blocked and FALSE if not
	 */
	public boolean blockNightAction() {
		if (blockImmune) {
			return false;
		}
		return roleBlocked = true;
	}

	/**
	 * 
	 * @return TRUE if character is role blocked or FALSE if character is not
	 */
	protected boolean isRoleBlocked() {
		return roleBlocked;
	}

	// ***********************************************
	// find doctor find heal
	// THE METHODS THAT HAVE TO DO WITH DOCTOR HEALING
	// ***********************************************

	/**
	 * heal the player
	 * 
	 * @return TRUE
	 */
	public boolean healPlayer() {
		return healed = true;
	}

	// *******************************
	// find abstract
	// THE FOLLOW ARE ABSTRACT CLASSES
	// *******************************
	/**
	 * 
	 * @param targets
	 * @return TRUE if targets are set and FALSE it not
	 */
	public abstract boolean setTarget(List<Player> targets);

	/**
	 * Consider role block and visiting
	 * 
	 * @return String reported to player
	 */
	public abstract String doAction();
	
	/**
	 * Returns the affiliation of the character
	 * 
	 * @return Sides.Town, Sides.Triad, Sides.Neutral, or Sides.Mafia
	 */
	public Enumerators.Sides getSide() {
		return role.getSide();
	}

	/**
	 * @return the role of the character
	 */
	protected Enumerators.Roles getRole() {
		return role;
	}
}
