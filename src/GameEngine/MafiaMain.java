package GameEngine;

import java.util.ArrayList;
import java.util.List;

import GameEngine.GameRegistration.Player;

public class MafiaMain {

	List<Player> player_list = new ArrayList<Player>();

	public static void main(String[] args) {

	}

	public enum CLInput {
		ListPlayers, AddPlayer, RemovePlayer, RemoveAllPlayers, StartGame, DelayGame, PauseGame, ResumeGame, StartNewDay, StartLynch, ForceEndGame, SetLastWill, SetVote, SetTarget, TauntPlayer, ListAlivePlayers, ListDeadPlayers, ShowLastWill, ShowVote, ShowTarget, ShowDaily, ShowRole, ShowGameStatus, ShowAdminStatus, Admin, BadInput;

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
			case "showrole":
				CLICommand = CLInput.ShowRole;
				break;
			case "showgamestatus":
				CLICommand = CLInput.ShowGameStatus;
				break;
			case "showadminstatus":
				CLICommand = CLInput.ShowAdminStatus;
			case "admin":
				CLICommand = CLInput.Admin;
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
