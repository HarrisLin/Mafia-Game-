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
	protected List<Player> actionTarget;
	// Target the player has chosen to vote for lynch
	protected List<Player> lynchTarget;
	// Vote count on this character
	private int lynchCount;
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
		invulnerable = false;
		blockImmune = false;
		alive = true;
		visitors = new ArrayList<Player>();
		actionTarget = new ArrayList<Player>();
		lynchTarget = new ArrayList<Player>();
		lynchCount = 0;
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
		this.invulnerable = invulnerable;
		blockImmune = false;
		alive = true;
		visitors = new ArrayList<Player>();
		actionTarget = new ArrayList<Player>();
		lynchTarget = new ArrayList<Player>();
		lynchCount = 0;
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
		this.invulnerable = invulnerable;
		this.blockImmune = blockImmune;
		alive = true;
		visitors = new ArrayList<Player>();
		actionTarget = new ArrayList<Player>();
		lynchTarget = new ArrayList<Player>();
		lynchCount = 0;
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
		actionTarget.clear();
		lynchTarget.clear();
		lynchCount = 0;
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
	public Roles getRole() {
		return role;
	}

	/**
	 * Returns the affiliation of the character
	 * 
	 * @return Sides.Town, Sides.Triad, Sides.Neutral, or Sides.Mafia
	 */
	public Enumerators.Sides getSide() {
		return role.getSide();
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

	// ************************************
	// find target
	// METHODS THAT HAVE TO DO WITH TARGET
	// ***********************************
	/**
	 * Set targets
	 * 
	 * @param targets
	 * @return TRUE
	 */
	public boolean setTarget(List<Player> target) {
		if (target.size() != 1) {
			return false;
		}
		try {
			if (!GameEngine.getCharacter(target.get(0)).isAlive()) {
				return false;
			}
		} catch (CannotGetPlayerException e) {
			System.out.println(GameMessage.NO_CHARACTER(target.get(0)));
			return false;
		}
		actionTarget = new ArrayList<Player>(target);
		return true;
	}

	public boolean setTarget(Player target) {
		try {
			if (!GameEngine.getCharacter(target).isAlive()) {
				return false;
			}
		} catch (CannotGetPlayerException e) {
			System.out.println(GameMessage.NO_CHARACTER(target));
			return false;
		}
		actionTarget = new ArrayList<Player>();
		actionTarget.add(target);
		return true;
	}

	/**
	 * @return list of night action targets of the player
	 */
	public List<Player> getTarget() {
		return new ArrayList<Player>(actionTarget);
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
			GameEngine.killCharacter(this);
			alive = false;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if player is alive or dead
	 * 
	 * @return true if alive, false if dead
	 */
	public boolean isAlive() {
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
		try {
			if (GameEngine.getCharacter(player).isAlive()) {
				visitors.add(player);
				return true;
			} else {
				return false;
			}
		} catch (CannotGetPlayerException e) {
			return false;
		}
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
	public boolean vote(List<Player> lynchVote) {
		if (lynchVote.size() != 1) {
			return false;
		}
		try {
			if (GameEngine.getCharacter(lynchVote.get(0)).isAlive()) {
				if (lynchTarget.get(0) == null) {
					lynchTarget = lynchVote;
					GameEngine.getCharacter(lynchTarget.get(0)).addLynchCount();
					return true;
				} else {
					GameEngine.getCharacter(lynchTarget.get(0)).subLynchCount();
					lynchTarget = lynchVote;
					GameEngine.getCharacter(lynchTarget.get(0)).addLynchCount();
					return true;
				}
			} else {
				return false;
			}
		} catch (CannotGetPlayerException e) {
			return false;
		}
	}

	public boolean vote(Player lynchVote) {
		try {
			if (GameEngine.getCharacter(lynchVote).isAlive()) {
				if (voteTarget.isEmpty()) {
					voteTarget = new ArrayList<Player>();
					voteTarget.add(lynchVote);
					GameEngine.getCharacter(voteTarget.get(0)).addLynchCount();
					return true;
				} else {
					GameEngine.getCharacter(lynchTarget.get(0)).subLynchCount();
					lynchTarget = new ArrayList<Player>();
					lynchTarget.add(lynchVote);
					GameEngine.getCharacter(lynchTarget.get(0)).addLynchCount();
					return true;
				}
			} else {
				return false;
			}
		} catch (CannotGetPlayerException e) {
			return false;
		}
	}

	/**
	 * Lynch target for kill
	 */
	public boolean lynch() {
		alive = false;
		GameEngine.killCharacter(this);
		return true;
	}

	/**
	 * 
	 * @return target player of which this character wishes to lynch
	 */
	public List<Player> getLynchTarget() {
		return lynchTarget;
	}

	/**
	 * @return how many people voted this character/player for lynch
	 */
	public int getLynchCount() {
		return lynchCount;
	}

	public int addLynchCount() {
		return lynchCount++;
	}

	public int subLynchCount() {
		if (lynchCount > 0) {
			return lynchCount--;
		} else {
			return lynchCount;
		}
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
		if (alive) {
			this.lastWill = lastWill;
			return true;
		} else {
			return false;
		}
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
		if (alive) {
			return doused = true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return TRUE if character is doused and FALSE if not
	 */
	public boolean isDoused() {
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
		if (alive && !blockImmune) {
			return roleBlocked = true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return TRUE if character is role blocked or FALSE if character is not
	 */
	public boolean isRoleBlocked() {
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
		if (alive) {
			return healed = true;
		} else {
			return false;
		}
	}

	public boolean isHealed() {
		return healed;
	}

	// *******************************
	// find database
	// THE FOLLOW ARE METHODS FOR THE DATABASE
	// *******************************
	/**
	 * Returns a string separated by "##" that contains all character
	 * role-specific information that needs to be saved in a database.
	 *
	 * THIS MUST BE OVERRIDDEN FOR CHARACTERS THAT NEED TO SAVE ROLE-SPECIFIC
	 * VALUES.
	 *
	 * For example, if we needed to store these values for Veteran - Game Option
	 * (String): TWO_SHOTS_PER_GAME - Number of shots remaining (int): 2 It
	 * would be stored as "TWO_SHOTS_PER_GAME##2"
	 * 
	 * @return A string with data fields separated by "##" to be inserted into
	 *         the database
	 */
	protected String getDatabaseRoleInfoString() {
		return "";
	}

	/**
	 * Imports character role-specific information that needs to be saved in a
	 * database.
	 *
	 * THIS MUST BE OVERRIDDEN FOR CHARACTERS THAT NEED TO SAVE ROLE-SPECIFIC
	 * VALUES.
	 *
	 * For example, if we had this string from Veteran "TWO_SHOTS_PER_GAME##2"
	 * We would parse the string and set the following values: - Game Option
	 * (enum): TWO_SHOTS_PER_GAME - Number of shots remaining (int): 2
	 * 
	 * @param role_info
	 *            The data retrieved from the database, with each field
	 *            separated by "##".
	 * @return true if successful, false otherwise
	 */
	protected boolean importDatabaseRoleInfo(String role_info) {
		return true;
	}

	// *******************************
	// find abstract
	// THE FOLLOW ARE ABSTRACT METHODS
	// *******************************

	/**
	 * Consider role block and visiting
	 * 
	 * @return String reported to player
	 */
	public abstract String doAction() throws CannotGetPlayerException;

	// *******************************
	// find equals
	// THE FOLLOW ARE THE EQUAL AND HASH METHODS
	// *******************************
	@Override
	public boolean equals(Object o) {
		if (o instanceof Character == false) {
			return false;
		}
		Character other = (Character) o;
		if ((this.role == other.role) && (this.player == other.player)
				&& (this.alive == other.alive) && (this.doused == other.doused)
				&& (this.blockImmune == other.blockImmune)
				&& (this.healed == other.healed)
				&& (this.invulnerable == other.invulnerable)
				&& (this.roleBlocked == other.roleBlocked)
				&& (this.lastWill == other.lastWill)
				&& (this.lynchTarget == other.lynchTarget)
				&& (this.actionTarget == other.actionTarget)
				&& (this.visitors == other.visitors)) {
			return true;
		} else {
			return false;
		}
	}
}
