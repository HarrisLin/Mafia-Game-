package Character;

import java.util.ArrayList;
import java.util.List;

import Character.CharacterFactory.Roles;
import GameEngine.GameRegistration.Player;

/**
 * Character in the game
 */
public abstract class Character {
	private final Roles role;
	protected final Player player;
	protected final CharacterStatus character_status;
	/**
	 * Constructor
	 * 
	 * @param role
	 * @param player
	 */
	protected Character(Roles role, Player player) {
		this.role = role;
		this.player = player;
		character_status = new CharacterStatus();
	}
	/**
	 * Constructor
	 * 
	 * @param role
	 * @param player
	 * @param invulnerable
	 */
	protected Character(Roles role, Player player, boolean invulnerable) {
		this.role = role;
		this.player = player;
		character_status = new CharacterStatus();
	}
	/**
	 * Constructor
	 * 
	 * @param role
	 * @param player
	 * @param invulnerable
	 * @param block_immune
	 */
	protected Character(Roles role, Player player, boolean invulnerable, boolean block_immune) {
		this.role = role;
		this.player = player;
		character_status = new CharacterStatus();
	}
	
	/**
	 * @return String of the role
	 */
	public Roles getRole() {
		return role;
	}

	/**
	 * Perform night action
	 * 
	 * @param target
	 * @return game log string message
	 */
	public abstract String performAction(Character target);
	public abstract String performAction(List<Player> alive_player, Character target);
	
	/**
	 * CharacterStatus saves all the the character status information.
	 */
	protected class CharacterStatus {
		private final boolean invulnerable;
		private final boolean block_immune;
		private final List<Player> visitors;
		private boolean doused;
		private boolean blocked;
		private boolean healed;
		
		/**
		 * Constructor
		 */
		private CharacterStatus() {
			invulnerable = false;
			block_immune = false;
			visitors = new ArrayList<Player>();
			doused = false;
			blocked = false;
			healed = false;
		}
		/**
		 * @return true if is invulnerable, else false
		 */
		protected boolean isInvulnerable() {
			return invulnerable;
		}
		/**
		 * @return true if is block immune, else false
		 */
		protected boolean isBlockImmune() {
			return block_immune;
		}
		/**
		 * @return list of visitors
		 */
		protected List<Player> getVisitors() {
			return visitors;
		}
		/**
		 * @return true if doused, else false
		 */
		protected boolean isDoused() {
			return doused;
		}
		/**
		 * @return true if doused and false if not-doused
		 */
		protected boolean douse() {
			return doused = !doused;
		}
		/**
		 * @return true if blocked, else false
		 */
		protected boolean isBlocked() {
			return blocked;
		}
		/**
		 * @return true when blocked
		 */
		protected boolean block() {
			return blocked = true;
		}
		/**
		 * @return true if healed, else false
		 */
		protected boolean isHealed() {
			return healed;
		}
		/**
		 * @return true when healed
		 */
		protected boolean heal() {
			return healed = true;
		}
		/**
		 * @return true when reset
		 */
		public boolean reset() {
			blocked = false;
			healed = false;
			visitors.clear();
			return true;
		}
	}
}
