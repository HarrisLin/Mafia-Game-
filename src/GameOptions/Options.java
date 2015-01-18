package GameOptions;


public interface Options {
	/**
	 * Change options corresponding to which number the option is
	 * 
	 * @param option
	 *            is a number corresponding to the option
	 * @return true if the option has been successfully changed or false it not
	 */
	public boolean changeOptions(int option);

	/**
	 * Get the option corresponding to the number inputed by the argument
	 * 
	 * @param option
	 *            number corresponding to the option
	 * @return TRUE if option is ON or FALSE if option is OFF
	 * @throws NoSuchOptionException
	 *             if there is no such option
	 */
	public boolean getOptions(int option) throws NoSuchOptionException;
}
