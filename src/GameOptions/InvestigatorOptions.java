package GameOptions;
/**
 * Options for the investigator role
 * @author pacified
 *
 */
public class InvestigatorOptions implements Options {
	
	boolean detectsExactRole;
	
	InvestigatorOptions() {
		defaultOptions();
	}
	
	private void defaultOptions() {
		detectsExactRole = false;
	}
	
	@Override
	public boolean changeOptions(int option) {
		switch(option) {
		case 0:
			defaultOptions();
			break;
		case 1:
			detectsExactRole = !detectsExactRole;
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public boolean getOptions(int option) throws NoSuchOptionException {
		switch(option) {
		case 1:
			return detectsExactRole;
		default:
			throw new NoSuchOptionException();
		}
	}

}
