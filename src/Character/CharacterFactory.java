package Character;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;
import Character.*;

/**
 * CharacterFactory allows us to create a character
 * object with only a Roles enum. This will be essential
 * for importing Character data from the SQL database.
 */
public class CharacterFactory {

	public static Character makeCharacter(Roles role, Player player) {
		switch (role) {
		case Bodyguard: return new Bodyguard(player);
		case BusDriver: return new BusDriver(player);
		//case Citizen: return new Citizen(player);
		//case Coroner: return new Coroner(player);
		//case Crier: return new Crier(player);
		case Detective: return new Detective(player);
		case Doctor: return new Doctor(player);
		case Escort: return new Escort(player);
		case Investigator: return new Investigator(player);
		//case Jailor: return new Jailor(player);
		case Lookout: return new Lookout(player);
		//case Marshall: return new Marshall(player);
		//case Mason: return new Mason(player);
		//case MasonLeader: return new MasonLeader(player);
		case Mayor: return new Mayor(player);
		case Sheriff: return new Sheriff(player);
		//case Spy: return new Spy(player);
		//case Stump: return new Stump(player);
		case Veteran: return new Veteran(player);
		case Vigilante: return new Vigilante(player);
		//case Agent: return new Agent(player);
		//case Beguiler: return new Beguiler(player);
		//case Blackmailer: return new Blackmailer(player);
		case Consigliere: return new Consigliere(player);
		case Consort: return new Consort(player);
		//case Disguiser: return new Disguiser(player);
		//case Framer: return new Framer(player);
		case Godfather: return new Godfather(player);
		//case Janitor: return new Janitor(player);
		//case Kidnapper: return new Kidnapper(player);
		case Mafioso: return new Mafioso(player);
		//case Administrator: return new Administrator(player);
		//case Deceiver: return new Deceiver(player);
		//case DragonHead: return new DragonHead(player);
		//case Enforcer: return new Enforcer(player);
		//case Forger: return new Forger(player);
		//case IncenseMaster: return new IncenseMaster(player);
		//case Informant: return new Informant(player);
		//case Interrogator: return new Interrogator(player);
		//case Liaison: return new Liaison(player);
		//case Silencer: return new Silencer(player);
		//case Vanguard: return new Vanguard(player);
		//case Amnesiac: return new Amnesiac(player);
		case Arsonist: return new Arsonist(player);
		//case Auditor: return new Auditor(player);
		case Cultist: return new Cultist(player);
		//case Executioner: return new Executioner(player);
		//case Jester: return new Jester(player);
		//case Judge: return new Judge(player);
		case MassMurderer: return new MassMurderer(player);
		case SerialKiller: return new SerialKiller(player);
		//case Survivor: return new Survivor(player);
		case Witch: return new Witch(player);
		//case WitchDoctor: return new WitchDoctor(player);
		}
		return null;
	}
	
}
