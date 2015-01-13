package Character;

public enum Roles {
	Bodyguard(Sides.Town), BusDriver(Sides.Town), Citizen(Sides.Town), Coroner(
			Sides.Town), Crier(Sides.Town), Detective(Sides.Town), Doctor(
			Sides.Town), Escort(Sides.Town), Investigator(Sides.Town), Jailor(
			Sides.Town), Lookout(Sides.Town), Marshall(Sides.Town), Mason(
			Sides.Town), MasonLeader(Sides.Town), Mayor(Sides.Town), Sheriff(
			Sides.Town), Spy(Sides.Town), Stump(Sides.Town), Veteran(Sides.Town), Vigilante(
			Sides.Town), Agent(Sides.Mafia), Beguiler(Sides.Mafia), Blackmailer(
			Sides.Mafia), Consigliere(Sides.Mafia), Consort(Sides.Mafia), Disguiser(
			Sides.Mafia), Framer(Sides.Mafia), Godfather(Sides.Mafia), Janitor(
			Sides.Mafia), Kidnapper(Sides.Mafia), Mafioso(Sides.Mafia), Administrator(
			Sides.Triad), Deceiver(Sides.Triad), DragonHead(Sides.Triad), Enforcer(
			Sides.Triad), Forger(Sides.Triad), IncenseMaster(Sides.Triad), Informant(
			Sides.Triad), Interrogator(Sides.Triad), Liaison(Sides.Triad), Silencer(
			Sides.Triad), Vanguard(Sides.Triad), Amnesiac(Sides.Neutral), Arsonist(
			Sides.Neutral), Auditor(Sides.Neutral), Cultist(Sides.Neutral), Executioner(
			Sides.Neutral), Jester(Sides.Neutral), Judge(Sides.Neutral), MassMurderer(
			Sides.Neutral), SerialKiller(Sides.Neutral), Survivor(Sides.Neutral), Witch(
			Sides.Neutral), WitchDoctor(Sides.Neutral);

	Sides side;

	Roles(Sides side) {
		this.side = side;
	}
	
	public Sides getSide() {
		return side;
	}
}