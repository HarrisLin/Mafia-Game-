package MafiaMain;

import java.util.ArrayList;
import java.util.List;

import GameEngine.GameRegistration.Player;
import Resources.GameMessage;
/**
 * Shell Commands (formerly Mafia Main) processes commands before they enter game engine.
 */
public class ShellCommands {

	List<Player> player_list = new ArrayList<Player>();
	boolean in_game = false;
	
	public static int main(String[] args) {
		if(args.length < 2) {
			GameMessage.CommandFilter.BAD_LENGTH();
			return GameMessage.exit_number;
		}
		CLInput CLICommand = CLInput.fromString(args[1]);
		switch(CLICommand) {
		case AddPlayer:
			break;
		case Admin:
			break;
		case AdminGameLog:
			break;
		case BadInput:
			break;
		case DelayGame:
			break;
		case ForceEndGame:
			break;
		case ListAlivePlayers:
			break;
		case ListDeadPlayers:
			break;
		case ListPlayers:
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
		case ShowDaily:
			break;
		case ShowGameStatus:
			break;
		case ShowLastWill:
			break;
		case ShowResult:
			break;
		case ShowRole:
			break;
		case ShowShotsLeft:
			break;
		case ShowTarget:
			break;
		case ShowVote:
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
		return GameMessage.exit_number;
	}

	public enum CLInput {
		ListPlayers, AddPlayer, RemovePlayer, RemoveAllPlayers, StartGame, DelayGame, PauseGame, ResumeGame, StartNewDay, StartLynch, ForceEndGame, SetLastWill, SetVote, SetTarget, RevealMayor, TauntPlayer, ListAlivePlayers, ListDeadPlayers, ShowLastWill, ShowVote, ShowTarget, ShowDaily, ShowResult, ShowRole, ShowShotsLeft, ShowGameStatus, AdminGameLog, Admin, BadInput;

		public static CLInput fromString(String input) {
			CLInput CLICommand;
			switch (input.toLowerCase()) {
			case "listplayers":
				CLICommand = CLInput.ListPlayers;
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
			case "listaliveplayers":
				CLICommand = CLInput.ListAlivePlayers;
				break;
			case "listdeadplayers":
				CLICommand = CLInput.ListDeadPlayers;
				break;
			case "showlastwill":
				CLICommand = CLInput.ShowLastWill;
				break;
			case "showvote":
				CLICommand = CLInput.ShowVote;
				break;
			case "showtarget":
				CLICommand = CLInput.ShowTarget;
				break;
			case "showdaily":
				CLICommand = CLInput.ShowDaily;
				break;
			case "showresult":
				CLICommand = CLInput.ShowResult;
				break;
			case "showrole":
				CLICommand = CLInput.ShowRole;
				break;
			case "showshotsleft":
				CLICommand = CLInput.ShowShotsLeft;
				break;
			case "showgamestatus":
				CLICommand = CLInput.ShowGameStatus;
				break;
			case "admingamelog":
				CLICommand = CLInput.AdminGameLog;
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
