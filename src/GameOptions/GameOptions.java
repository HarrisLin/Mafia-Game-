package GameOptions;

import Enumerators.Roles;

/**
 * Game options for the game.
 * 
 * @author pacified
 *
 */
public class GameOptions {

	// Options for the roles as classes in the game options class
	private static Options sheriff;
	private static Options investigator;

	/**
	 * Constructor
	 */
	GameOptions() {
		sheriff = new SheriffOptions();
		investigator = new InvestigatorOptions();
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
		case Investigator:
			return investigator.changeOptions(option);
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
		case Investigator:
			return investigator.getOptions(option);
		default:
			throw new NoSuchOptionException();
		}
	}
}
