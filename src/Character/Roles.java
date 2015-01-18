package Character;

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
			Sides.Mafia, "Framer"), Godfather(Sides.Mafia, "Godfather"), Janitor(
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

	Sides side;
	String roll;

	Roles(Sides side, String roll) {
		this.side = side;
		this.roll = roll;
	}

	public Sides getSide() {
		return side;
	}
	
	public String toString() {
		return roll;
	}
}