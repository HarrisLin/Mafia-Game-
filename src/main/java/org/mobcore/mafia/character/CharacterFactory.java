package org.mobcore.mafia.character;

import org.mobcore.mafia.engine.GameRegistration.Player;

public class CharacterFactory {
	public static Character makeCharacter(Roles role, Player player) {
		switch (role) {
		case Administrator:
			break;
		case Agent:
			break;
		case Amnesiac:
			break;
		case Arsonist:
			break;
		case Auditor:
			break;
		case Beguiler:
			break;
		case Blackmailer:
			break;
		case Bodyguard:
			break;
		case BusDriver:
			break;
		case Citizen:
			break;
		case Consigliere:
			break;
		case Consort:
			break;
		case Coroner:
			break;
		case Crier:
			break;
		case Cultist:
			break;
		case Deceiver:
			break;
		case Detective:
			break;
		case Disguiser:
			break;
		case Doctor: return new Doctor(player);
		case DragonHead:
			break;
		case Enforcer:
			break;
		case Escort:
			break;
		case Executioner:
			break;
		case Forger:
			break;
		case Framer:
			break;
		case Godfather:
			break;
		case IncenseMaster:
			break;
		case Informant:
			break;
		case Interrogator:
			break;
		case Investigator: return new Investigator(player);
		case Jailor:
			break;
		case Janitor:
			break;
		case Jester:
			break;
		case Judge:
			break;
		case Kidnapper:
			break;
		case Liaison:
			break;
		case Lookout:
			break;
		case Mafioso:
			break;
		case Marshall:
			break;
		case Mason:
			break;
		case MasonLeader:
			break;
		case MassMurderer:
			break;
		case Mayor: return new Mayor(player);
		case SerialKiller:
			break;
		case Sheriff:
			break;
		case Silencer:
			break;
		case Spy:
			break;
		case Stump:
			break;
		case Survivor:
			break;
		case Vanguard:
			break;
		case Veteran:
			break;
		case Vigilante: return new Vigilante(player);
		case Witch:
			break;
		case WitchDoctor:
			break;
		default:
			break;
		
		}
		return null;
	}
	
	/**
	 * Enumeration of all the roles in the game.
	 */
	public enum Roles {

		Administrator(Sides.Triad, "Administrator"),
		Agent(Sides.Mafia, "Agent"),
		Amnesiac(Sides.Neutral, "Amnesiac"),
		Arsonist(Sides.Neutral, "Arsonist"),
		Auditor(Sides.Neutral, "Auditor"),
		Beguiler(Sides.Mafia, "Beguiler"),
		Blackmailer(Sides.Mafia, "Blackmailer"),
		Bodyguard(Sides.Town, "Bodyguard"),
		BusDriver(Sides.Town, "BusDriver"),
		Citizen(Sides.Town, "Citizen"),
		Consigliere(Sides.Mafia,"Consigliere"),
		Consort(Sides.Mafia, "Consort"),
		Coroner(Sides.Town, "Coroner"),
		Crier(Sides.Town, "Crier"),
		Cultist(Sides.Neutral, "Cultist"),
		Deceiver(Sides.Triad, "Deceiver"),
		Detective(Sides.Town, "Detective"),
		Disguiser(Sides.Mafia, "Disguiser"),
		Doctor(Sides.Town, "Doctor"),
		DragonHead(Sides.Triad, "DragonHead"),
		Enforcer(Sides.Triad, "Enforcer"),
		Escort(Sides.Town, "Escort"),
		Executioner(Sides.Neutral, "Executioner"),
		Forger(Sides.Triad, "Forger"),
		Framer(Sides.Mafia, "Framer"),
		Godfather(Sides.Mafia, "Godfather"),
		IncenseMaster(Sides.Triad,"IncenseMaster"),
		Informant(Sides.Triad, "Informant"),
		Interrogator(Sides.Triad, "Interrogator"),
		Investigator(Sides.Town, "Investigator"),
		Jailor(Sides.Town, "Jailor"),
		Janitor(Sides.Mafia, "Janitor"),
		Jester(Sides.Neutral, "Jester"),
		Judge(Sides.Neutral, "Judge"),
		Kidnapper(Sides.Mafia, "Kidnapper"),
		Liaison(Sides.Triad, "Liaison"),
		Lookout(Sides.Town, "Lookout"),
		Mafioso(Sides.Mafia, "Mafioso"),
		Marshall(Sides.Town, "Marshall"),
		MasonLeader(Sides.Town, "MasonLeader"),
		Mason(Sides.Town, "Mason"),
		MassMurderer(Sides.Neutral,"MassMurderer"),
		Mayor(Sides.Town, "Mayor"),
		SerialKiller(Sides.Neutral, "SerialKiller"),
		Sheriff(Sides.Town, "Sheriff"),
		Silencer(Sides.Triad, "Silencer"),
		Spy(Sides.Town, "Spy"),
		Stump(Sides.Town, "Stump"),
		Survivor(Sides.Neutral, "Survivor"),
		Vanguard(Sides.Triad, "Vanguard"),
		Veteran(Sides.Town, "Veteran"),
		Vigilante(Sides.Town, "Vigilante"),
		Witch(Sides.Neutral, "Witch"),
		WitchDoctor(Sides.Neutral, "WitchDoctor");

		private Sides side;
		private String role;

		/**
		 * Constructor
		 * 
		 * @param side
		 *            : Side of the character
		 * @param role
		 *            : Role as a string
		 */
		Roles(Sides side, String role) {
			this.side = side;
			this.role = role;
		}

		/**
		 * 
		 * @return the side of the character
		 */
		public String sideToString() {
			return side.toString();
		}

		/**
		 * Returns role as a string
		 */
		public String toString() {
			return role;
		}

		/**
		 * Get the role from string
		 * 
		 * @param string of a role
		 * @return role of the strings
		 */
		public static Roles fromString(String string) {
			if (string != null) {
				for (Roles role : Roles.values()) {
					if (string.equalsIgnoreCase(role.role)) {
						return role;
					}
				}
			}
			return null;
		}
	}

	/**
	 * Enumeration of all the sides in the game.
	 */
	public enum Sides {
		Town("Town"), Mafia("Mafia"), Triad("Triad"), Neutral("Neutral");

		private final String side;

		Sides(String side) {
			this.side = side;
		}

		@Override
		public String toString() {
			return side;
		}
	}
}
