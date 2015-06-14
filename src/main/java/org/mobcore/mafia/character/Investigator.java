package org.mobcore.mafia.character;

import org.mobcore.mafia.engine.GameRegistration.Player;
import org.mobcore.mafia.character.CharacterFactory.Roles;
import org.mobcore.mafia.resouces.GameLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Investigator extends Character {

	private Random random_generator;

	protected Investigator(Player player) {
		super(Roles.Investigator, player);
		random_generator = new Random();
	}

	@Override
	public boolean performAction(List<Player> alive_player, Character target) {
		if (this.character_status.isBlocked()) {
			result = GameLog.Character.BLOCKED(player);
			return true;
		}
		target.character_status.getVisitors().add(player);
		List<Investigations> investigation_list = Investigations
				.doInvestigation(target.getRole());
		int index = random_generator.nextInt(investigation_list.size());
		result = GameLog.Character.INVESTIGATION_SUCCESS(player,
				target.player, investigation_list.get(index).toString());
		return true;
	}

	private enum Investigations {
		Trespassing("Trespassing"), Kidnapping("Kidnapping"), NoCrime(
				"No Crime"), Corruption("Corruption"), IdentityThief(
				"Identity Thief"), Soliciting("Soliciting"), Murder("Murder"), DisturbanceOfPeace(
				"Disturbance of Peace"), Conspiracy("Conspiracy"), DestructionOfProperty(
				"Destruction of Property"), Arson("Arson");

		String investigation;

		Investigations(String investigation) {
			this.investigation = investigation;
		}

		@Override
		public String toString() {
			return investigation.toLowerCase();
		}

		/**
		 * Makes a list of possible investigations for a role
		 * 
		 * @param role
		 * @return list of possible investigation results for that role
		 */
		protected static List<Investigations> doInvestigation(Roles role) {
			List<Investigations> investigations = new ArrayList<Investigations>();

			switch (role) {
			case Administrator:
				investigations.add(Investigations.Trespassing);
				break;
			case Agent:
				investigations.add(Investigations.Trespassing);
				break;
			case Amnesiac:
				investigations.add(Investigations.NoCrime);
				break;
			case Arsonist:
				investigations.add(Investigations.Trespassing);
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.DestructionOfProperty);
				investigations.add(Investigations.Arson);
				break;
			case Auditor:
				investigations.add(Investigations.Corruption);
				break;
			case Beguiler:
				investigations.add(Investigations.Trespassing);
				investigations.add(Investigations.Murder);
				break;
			case Blackmailer:
				investigations.add(Investigations.NoCrime);
				break;
			case Bodyguard:
				investigations.add(Investigations.Murder);
				break;
			case BusDriver:
				investigations.add(Investigations.Murder);
				break;
			case Citizen:
				investigations.add(Investigations.NoCrime);
				break;
			case Consigliere:
				investigations.add(Investigations.Trespassing);
				break;
			case Consort:
				investigations.add(Investigations.Trespassing);
				investigations.add(Investigations.DisturbanceOfPeace);
				break;
			case Coroner:
				investigations.add(Investigations.NoCrime);
				break;
			case Crier:
				investigations.add(Investigations.DisturbanceOfPeace);
				break;
			case Cultist:
				investigations.add(Investigations.Soliciting);
				investigations.add(Investigations.Conspiracy);
				break;
			case Deceiver:
				investigations.add(Investigations.Trespassing);
				investigations.add(Investigations.Murder);
				break;
			case Detective:
				investigations.add(Investigations.Trespassing);
				break;
			case Disguiser:
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.IdentityThief);
				break;
			case Doctor:
				investigations.add(Investigations.NoCrime);
				break;
			case DragonHead:
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.Trespassing);
				break;
			case Enforcer:
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.Trespassing);
				break;
			case Escort:
				investigations.add(Investigations.Soliciting);
				investigations.add(Investigations.DisturbanceOfPeace);
				break;
			case Executioner:
				investigations.add(Investigations.NoCrime);
				break;
			case Forger:
				investigations.add(Investigations.Trespassing);
				break;
			case Framer:
				investigations.add(Investigations.Trespassing);
				break;
			case Godfather:
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.Trespassing);
				break;
			case IncenseMaster:
				investigations.add(Investigations.DestructionOfProperty);
				investigations.add(Investigations.Trespassing);
				break;
			case Informant:
				investigations.add(Investigations.IdentityThief);
				investigations.add(Investigations.Murder);
				break;
			case Interrogator:
				investigations.add(Investigations.Kidnapping);
				investigations.add(Investigations.Murder);
				break;
			case Investigator:
				investigations.add(Investigations.Trespassing);
				break;
			case Jailor:
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.Kidnapping);
				break;
			case Janitor:
				investigations.add(Investigations.Trespassing);
				investigations.add(Investigations.DestructionOfProperty);
				break;
			case Jester:
				investigations.add(Investigations.NoCrime);
				break;
			case Judge:
				investigations.add(Investigations.DisturbanceOfPeace);
				investigations.add(Investigations.Corruption);
				break;
			case Kidnapper:
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.Kidnapping);
				break;
			case Liaison:
				investigations.add(Investigations.Soliciting);
				investigations.add(Investigations.DisturbanceOfPeace);
				break;
			case Lookout:
				investigations.add(Investigations.Trespassing);
				break;
			case Mafioso:
				investigations.add(Investigations.Trespassing);
				investigations.add(Investigations.Murder);
				break;
			case Marshall:
				investigations.add(Investigations.Corruption);
				break;
			case Mason:
				investigations.add(Investigations.NoCrime);
				break;
			case MasonLeader:
				investigations.add(Investigations.Trespassing);
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.Conspiracy);
				investigations.add(Investigations.Soliciting);
				break;
			case MassMurderer:
				investigations.add(Investigations.Trespassing);
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.DestructionOfProperty);
				break;
			case Mayor:
				investigations.add(Investigations.Corruption);
				break;
			case SerialKiller:
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.Trespassing);
				break;
			case Sheriff:
				investigations.add(Investigations.NoCrime);
				break;
			case Silencer:
				investigations.add(Investigations.NoCrime);
				break;
			case Spy:
				investigations.add(Investigations.Trespassing);
				break;
			case Stump:
				investigations.add(Investigations.NoCrime);
				break;
			case Survivor:
				investigations.add(Investigations.NoCrime);
				break;
			case Vanguard:
				investigations.add(Investigations.Trespassing);
				break;
			case Veteran:
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.DestructionOfProperty);
				break;
			case Vigilante:
				investigations.add(Investigations.Murder);
				investigations.add(Investigations.Trespassing);
				break;
			case Witch:
				investigations.add(Investigations.NoCrime);
				break;
			case WitchDoctor:
				investigations.add(Investigations.Conspiracy);
				break;
			default:
				break;
			}
			return investigations;
		}
	}
}
