package GameOptions;

/**
 * Options for the sheriff role
 * 
 * @author pacified
 *
 */
public class SheriffOptions implements RoleOptions {
	private DetectMafia detectsMafia;
	private DetectArsonist detectsArsonist;
	private DetectCultist detectsCultist;
	private DetectMassMurderer detectsMassMurderer;

	/**
	 * Constructor with default options
	 */
	public SheriffOptions() {
		defaultOptions();
	}
	
	/**
	 * Default options
	 */
	private void defaultOptions() {
		detectsMafia = DetectMafia.DETECT_MAFIA_ON;
		detectsArsonist = DetectArsonist.DETECT_ARSONIST_ON;
		detectsCultist = DetectCultist.DETECT_CULTIST_ON;
		detectsMassMurderer = DetectMassMurderer.DETECT_MASSMURDERER_ON;
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
			detectsMafia = detectsMafia.getNext();
			break;
		case 2:
			detectsArsonist = detectsArsonist.getNext();
			break;
		case 3:
			detectsCultist = detectsCultist.getNext();
			break;
		case 4:
			detectsMassMurderer = detectsMassMurderer.getNext();
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
	public Options getOptions(int option) throws NoSuchOptionException {
		switch (option) {
		case 1:
			return detectsMafia;
		case 2:
			return detectsArsonist;
		case 3:
			return detectsCultist;
		case 4:
			return detectsMassMurderer;
		default:
			throw new NoSuchOptionException();
		}
	}
	
	public static enum DetectMafia implements Options {
		DETECT_MAFIA_ON, DETECT_MAFIA_OFF;

		/**
		 * 
		 * @return next enum or if last enum return first enum
		 */
		public DetectMafia getNext() {
			return this.ordinal() < DetectMafia.values().length - 1 ? DetectMafia
					.values()[this.ordinal() + 1] : DetectMafia.values()[0];
		}
	}
	public static enum DetectArsonist implements Options {
		DETECT_ARSONIST_ON, DETECT_ARSONIST_OFF;

		/**
		 * 
		 * @return next enum or if last enum return first enum
		 */
		public DetectArsonist getNext() {
			return this.ordinal() < DetectArsonist.values().length - 1 ? DetectArsonist
					.values()[this.ordinal() + 1] : DetectArsonist.values()[0];
		}
	}
	
	public static enum DetectCultist implements Options {
		DETECT_CULTIST_ON, DETECT_CULTIST_OFF;

		/**
		 * 
		 * @return next enum or if last enum return first enum
		 */
		public DetectCultist getNext() {
			return this.ordinal() < DetectCultist.values().length - 1 ? DetectCultist
					.values()[this.ordinal() + 1] : DetectCultist.values()[0];
		}
	}
	
	public static enum DetectMassMurderer implements Options {
		DETECT_MASSMURDERER_ON, DETECT_MASSMURDERER_OFF;

		/**
		 * 
		 * @return next enum or if last enum return first enum
		 */
		public DetectMassMurderer getNext() {
			return this.ordinal() < DetectMassMurderer.values().length - 1 ? DetectMassMurderer
					.values()[this.ordinal() + 1] : DetectMassMurderer.values()[0];
		}
	}
	
}
