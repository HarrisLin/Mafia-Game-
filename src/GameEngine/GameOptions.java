package GameEngine;

import Enumerators.Roles;

/**
 * Game options for the game. Nested classes for scoping options for different
 * roles.
 * 
 * @author pacified
 *
 */
public class GameOptions {

	// Options for the roles as classes in the game options class
	private static SheriffOptions sheriff;

	/**
	 * Options for the sheriff role
	 * 
	 * @author pacified
	 *
	 */
	public class SheriffOptions {
		private boolean detectsMafia;
		private boolean detectsTriad;
		private boolean detectsArsonist;
		private boolean detectsCultist;
		private boolean detectsMassMurderer;

		/**
		 * Constructor with default options
		 */
		SheriffOptions() {
			defaultOptions();
		}
		
		/**
		 * Default options
		 */
		private void defaultOptions() {
			detectsMafia = true;
			detectsTriad = true;
			detectsArsonist = true;
			detectsCultist = true;
			detectsMassMurderer = true;
		}
		/**
		 * @return true if option is ON and false if option is OFF
		 */
		public boolean detectsMafia() {
			return detectsMafia;
		}

		/**
		 * @return true if option is ON and false if option is OFF
		 */
		public boolean detectsTriad() {
			return detectsTriad;
		}

		/**
		 * @return true if option is ON and false if option is OFF
		 */
		public boolean detectsArsonist() {
			return detectsArsonist;
		}

		/**
		 * @return true if option is ON and false if option is OFF
		 */
		public boolean detectsCultist() {
			return detectsCultist;
		}

		/**
		 * @return true if option is ON and false if option is OFF
		 */
		public boolean detectsMassMurderer() {
			return detectsMassMurderer;
		}

		/**
		 * Change options corresponding to which number the option is
		 * 
		 * @param option
		 *            is a number corresponding to the option
		 * @return true if the option has been successfully changed or false it
		 *         not
		 */
		public boolean changeOptions(int option) {
			switch (option) {
			case 0:
				defaultOptions();
				break;
			case 1:
				detectsMafia = !detectsMafia;
				break;
			case 2:
				detectsTriad = !detectsTriad;
				break;
			case 3:
				detectsArsonist = !detectsArsonist;
				break;
			case 4:
				detectsCultist = !detectsCultist;
				break;
			case 5:
				detectsMassMurderer = !detectsMassMurderer;
				break;
			default:
				return false;
			}
			return true;
		}

		public boolean getOptions(int option) throws NoSuchOptionException {
			switch (option) {
			case 1:
				return detectsMafia;
			case 2:
				return detectsTriad;
			case 3:
				return detectsArsonist;
			case 4:
				return detectsCultist;
			case 5:
				return detectsMassMurderer;
			default:
				throw new NoSuchOptionException();
			}
		}
	}

	/**
	 * Constructor
	 */
	GameOptions() {
		sheriff = new SheriffOptions();
	}

	/**
	 * Change options
	 * 
	 * @param role
	 *            : the role of which the option should be changed
	 * @param option
	 *            : a number that corresponds to which option to change and 0 to
	 *            change all options back to default
	 * @return true if the option has been sucessfully changed and false if it
	 *         has not
	 */
	public static boolean changeOptions(Roles role, int option) {
		switch (role) {
		case Sheriff:
			return sheriff.changeOptions(option);
		default:
			return false;
		}
	}

	/**
	 * Get options
	 * 
	 * @param role
	 *            : the role of which the option to get
	 * @param option
	 *            : a number that corresponds to the option to get
	 * @return true if the option is ON and false if the option is OFF
	 * @throws NoSuchOptionException
	 */
	public static boolean getOptions(Roles role, int option)
			throws NoSuchOptionException {
		switch (role) {
		case Sheriff:
			return sheriff.getOptions(option);
		default:
			throw new NoSuchOptionException();
		}
	}
}
