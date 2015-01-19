package GameOptions;

public class ConsigliereOptions implements RoleOptions {

	private DetectRole detectsRole;

	public ConsigliereOptions() {
		defaultOptions();
	}

	private void defaultOptions() {
		detectsRole = DetectRole.DETECT_VAGUE_ROLE;
	}

	@Override
	public boolean changeOptions(int option) {
		switch (option) {
		case 0:
			defaultOptions();
			break;
		case 1:
			detectsRole = detectsRole.getNext();
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public Options getOptions(int option) throws NoSuchOptionException {
		switch (option) {
		case 1:
			return detectsRole;
		default:
			throw new NoSuchOptionException();
		}
	}

	/**
	 * The game options for Investigators. Detects if investigation yeild exact
	 * role or a vague role.
	 */
	public static enum DetectRole implements Options {
		DETECT_EXACT_ROLE, DETECT_VAGUE_ROLE;

		/**
		 * 
		 * @return next enum or if last enum return first enum
		 */
		public DetectRole getNext() {
			return this.ordinal() < DetectRole.values().length - 1 ? DetectRole
					.values()[this.ordinal() + 1] : DetectRole.values()[0];
		}
	}
}
