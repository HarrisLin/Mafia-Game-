package GameOptions;


/**
 * Options for the sheriff role
 * 
 * @author pacified
 *
 */
public class SheriffOptions implements Options {
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
	@Override
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
	
	/**
	 * Get the option corresponding to the number inputed by the argument
	 * 
	 * @param option
	 *            number corresponding to the option
	 * @return TRUE if option is ON or FALSE if option is OFF
	 * @throws NoSuchOptionException
	 *             if there is no such option
	 */
	@Override
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
