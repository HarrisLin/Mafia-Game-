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
	private static RoleOptions sheriff;
	private static RoleOptions investigator;

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
	 * @return TRUE if the option has been sucessfully changed and FALSE if it
	 *         has not
	 */
	public static boolean changeOptions(Roles role, int option) {
		switch (role) {
		case Sheriff:
			return sheriff.changeOptions(option);
		case Investigator:
			return investigator.changeOptions(option);
			/*
			 * case Administrator: break; case Agent: break; case Amnesiac:
			 * break; case Arsonist: break; case Auditor: break; case Beguiler:
			 * break; case Blackmailer: break; case Bodyguard: break; case
			 * BusDriver: break; case Citizen: break; case Consigliere: break;
			 * case Consort: break; case Coroner: break; case Crier: break; case
			 * Cultist: break; case Deceiver: break; case Detective: break; case
			 * Disguiser: break; case Doctor: break; case DragonHead: break;
			 * case Enforcer: break; case Escort: break; case Executioner:
			 * break; case Forger: break; case Framer: break; case Godfather:
			 * break; case IncenseMaster: break; case Informant: break; case
			 * Interrogator: break; case Jailor: break; case Janitor: break;
			 * case Jester: break; case Judge: break; case Kidnapper: break;
			 * case Liaison: break; case Lookout: break; case Mafioso: break;
			 * case Marshall: break; case Mason: break; case MasonLeader: break;
			 * case MassMurderer: break; case Mayor: break; case SerialKiller:
			 * break; case Silencer: break; case Spy: break; case Stump: break;
			 * case Survivor: break; case Vanguard: break; case Veteran: break;
			 * case Vigilante: break; case Witch: break; case WitchDoctor:
			 * break;
			 */
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
	public static Options getOptions(Roles role, int option)
			throws NoSuchOptionException {
		switch (role) {
		case Sheriff:
			return sheriff.getOptions(option);
		case Investigator:
			return investigator.getOptions(option);
			/*
			 * case Administrator: break; case Agent: break; case Amnesiac:
			 * break; case Arsonist: break; case Auditor: break; case Beguiler:
			 * break; case Blackmailer: break; case Bodyguard: break; case
			 * BusDriver: break; case Citizen: break; case Consigliere: break;
			 * case Consort: break; case Coroner: break; case Crier: break; case
			 * Cultist: break; case Deceiver: break; case Detective: break; case
			 * Disguiser: break; case Doctor: break; case DragonHead: break;
			 * case Enforcer: break; case Escort: break; case Executioner:
			 * break; case Forger: break; case Framer: break; case Godfather:
			 * break; case IncenseMaster: break; case Informant: break; case
			 * Interrogator: break; case Jailor: break; case Janitor: break;
			 * case Jester: break; case Judge: break; case Kidnapper: break;
			 * case Liaison: break; case Lookout: break; case Mafioso: break;
			 * case Marshall: break; case Mason: break; case MasonLeader: break;
			 * case MassMurderer: break; case Mayor: break; case SerialKiller:
			 * break; case Silencer: break; case Spy: break; case Stump: break;
			 * case Survivor: break; case Vanguard: break; case Veteran: break;
			 * case Vigilante: break; case Witch: break; case WitchDoctor:
			 * break;
			 */
		default:
			throw new NoSuchOptionException();
		}
	}
}
