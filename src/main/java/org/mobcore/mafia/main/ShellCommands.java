package org.mobcore.mafia.main;

import org.mobcore.mafia.engine.GameRegistration.Player;

import java.util.ArrayList;
import java.util.List;

import org.mobcore.mafia.resouces.GameLog;

/**
 * Shell Commands (formerly Mafia Main) processes commands before they enter
 * game engine.
 */
public class ShellCommands {

	List<Player> player_list = new ArrayList<Player>();
	boolean in_game;

	public static int main(String[] args) {
		if (args.length < 2) {
			GameLog.CommandFilter.BAD_LENGTH();
			return GameLog.exit_number;
		}
		CLInput CLICommand = CLInput.fromString(args[1]);
		
		switch (CLICommand) {
		case AddPlayer:
			break;
		case Admin:
			break;
		case BadInput:
			break;
		case DelayGame:
			break;
		case ForceEndGame:
			break;
		case ListAllPlayers:
			break;
		case PauseGame:
			break;
		case RemoveAllPlayers:
			break;
		case RemovePlayer:
			break;
		case ResumeGame:
			break;
		case RevealMayor:
			break;
		case SetLastWill:
			break;
		case SetTarget:
			break;
		case SetVote:
			break;
		case StartGame:
			break;
		case StartLynch:
			break;
		case StartNewDay:
			break;
		case TauntPlayer:
			break;
		default:
			break;
		}

		return GameLog.exit_number;
	}

	public enum CLInput {
		ListAllPlayers, AddPlayer, RemovePlayer, RemoveAllPlayers, StartGame, DelayGame, PauseGame, ResumeGame, StartNewDay, StartLynch, ForceEndGame, SetLastWill, SetVote, SetTarget, RevealMayor, TauntPlayer, Admin, BadInput;

		public static CLInput fromString(String input) {
			CLInput CLICommand;
			switch (input.toLowerCase()) {
			case "listallplayers":
				CLICommand = CLInput.ListAllPlayers;
				break;
			case "addplayer":
				CLICommand = CLInput.AddPlayer;
				break;
			case "removeplayer":
				CLICommand = CLInput.RemovePlayer;
				break;
			case "removeallplayers":
				CLICommand = CLInput.RemoveAllPlayers;
				break;
			case "startgame":
				CLICommand = CLInput.StartGame;
				break;
			case "delaygame":
				CLICommand = CLInput.DelayGame;
				break;
			case "pausegame":
				CLICommand = CLInput.PauseGame;
				break;
			case "resumegame":
				CLICommand = CLInput.ResumeGame;
				break;
			case "startnewday":
				CLICommand = CLInput.StartNewDay;
				break;
			case "startlynch":
				CLICommand = CLInput.StartLynch;
				break;
			case "forceendgame":
				CLICommand = CLInput.ForceEndGame;
				break;
			case "setlastwill":
				CLICommand = CLInput.SetLastWill;
				break;
			case "setvote":
				CLICommand = CLInput.SetVote;
				break;
			case "settarget":
				CLICommand = CLInput.SetTarget;
				break;
			case "revealmayor":
				CLICommand = CLInput.RevealMayor;
				break;
			case "tauntplayer":
				CLICommand = CLInput.TauntPlayer;
				break;
			case "harrisisgay":
				CLICommand = CLInput.Admin;
				break;
			default:
				CLICommand = CLInput.BadInput;
				break;
			}
			return CLICommand;
		}
	}
}
