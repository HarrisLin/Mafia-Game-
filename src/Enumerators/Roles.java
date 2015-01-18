package Enumerators;

/**
 * All the roles as a enumerator Also includes which sides the role belongs to
 * as an enumerator inside the enumerator.
 * 
 * @author pacified
 *
 */
public enum Roles {
	Bodyguard(Sides.Town, "Bodyguard"), BusDriver(Sides.Town, "BusDriver"), Citizen(
			Sides.Town, "Citizen"), Coroner(Sides.Town, "Coroner"), Crier(
			Sides.Town, "Crier"), Detective(Sides.Town, "Detective"), Doctor(
			Sides.Town, "Doctor"), Escort(Sides.Town, "Escort"), Investigator(
			Sides.Town, "Investigator"), Jailor(Sides.Town, "Jailor"), Lookout(
			Sides.Town, "Lookout"), Marshall(Sides.Town, "Marshall"), Mason(
			Sides.Town, "Mason"), MasonLeader(Sides.Town, "MasonLeader"), Mayor(
			Sides.Town, "Mayor"), Sheriff(Sides.Town, "Sheriff"), Spy(
			Sides.Town, "Spy"), Stump(Sides.Town, "Stump"), Veteran(Sides.Town,
			"Veteran"), Vigilante(Sides.Town, "Vigilante"), Agent(Sides.Mafia,
			"Agent"), Beguiler(Sides.Mafia, "Beguiler"), Blackmailer(
			Sides.Mafia, "Blackmailer"), Consigliere(Sides.Mafia, "Consigliere"), Consort(
			Sides.Mafia, "Consort"), Disguiser(Sides.Mafia, "Disguiser"), Framer(
			Sides.Mafia, "Framer"), Godfather(Sides.Mafia, "Godfather", true), Janitor(
			Sides.Mafia, "Janitor"), Kidnapper(Sides.Mafia, "Kidnapper"), Mafioso(
			Sides.Mafia, "Mafioso"), Administrator(Sides.Triad, "Administrator"), Deceiver(
			Sides.Triad, "Deceiver"), DragonHead(Sides.Triad, "DragonHead"), Enforcer(
			Sides.Triad, "Enforcer"), Forger(Sides.Triad, "Forger"), IncenseMaster(
			Sides.Triad, "IncenseMaster"), Informant(Sides.Triad, "Informant"), Interrogator(
			Sides.Triad, "Interrogator"), Liaison(Sides.Triad, "Liaison"), Silencer(
			Sides.Triad, "Silencer"), Vanguard(Sides.Triad, "Vanguard"), Amnesiac(
			Sides.Neutral, "Amnesiac"), Arsonist(Sides.Neutral, "Arsonist"), Auditor(
			Sides.Neutral, "Auditor"), Cultist(Sides.Neutral, "Cultist"), Executioner(
			Sides.Neutral, "Executioner"), Jester(Sides.Neutral, "Jester"), Judge(
			Sides.Neutral, "Judge"), MassMurderer(Sides.Neutral, "MassMurderer"), SerialKiller(
			Sides.Neutral, "SerialKiller"), Survivor(Sides.Neutral, "Survivor"), Witch(
			Sides.Neutral, "Witch"), WitchDoctor(Sides.Neutral, "WitchDoctor");

	private Sides side;
	private String role;
	private boolean invulnerable;

	/**
	 * Constructor
	 * @param side : Side of the character
	 * @param role : Role as a string
	 */
	Roles(Sides side, String role) {
		this.side = side;
		this.role = role;
		invulnerable = false;
	}
	/**
	 * Constructor for characters with night invulnerability
	 * @param side : Side of the character
	 * @param role : Role as a string
	 * @param invulnerable : Invulnerability of the role
	 */
	Roles(Sides side, String role, boolean invulnerable) {
		this.side = side;
		this.role = role;
		this.invulnerable = invulnerable;
	}
	/**
	 * 
	 * @return the side of the character
	 */
	public Sides getSide() {
		return side;
	}
	
	/**
	 * Returns role as a string
	 */
	public String toString() {
		return role;
	}
	
	/**
	 * 
	 * @return true if role is invulnerable and false if not
	 */
	public boolean checkInvulnerable() {
		return invulnerable;
	}
}