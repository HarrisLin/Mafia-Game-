package Enumerators;

public enum CLInput {
	Help, Status, AddPlayer, RemovePlayer, StartGame, RebootGame, ResetGame, NewDay, ListPlayer, ListAlive, ListDead, Vote, ShowVote, SeeVote, Target, ShowTarget, LastWill, ShowLastWill, ShowDaily, ShowRole, Admin, AdminKey, BadInput;

	public static CLInput fromString(String input) {
		CLInput CLICommand;
		switch (input.toLowerCase()) {
		case "help":
			CLICommand = CLInput.Help;
			break;
		case "addplayer":
			CLICommand = CLInput.AddPlayer;
			break;
		case "removeplayer":
			CLICommand = CLInput.RemovePlayer;
			break;
		case "startgame":
			CLICommand = CLInput.StartGame;
			break;
		case "rebootgame":
			CLICommand = CLInput.RebootGame;
			break;
		case "resetgame":
			CLICommand = CLInput.ResetGame;
			break;
		case "forcenewday":
			CLICommand = CLInput.NewDay;
		case "listplayer":
			CLICommand = CLInput.ListPlayer;
			break;
		case "listalive":
			CLICommand = CLInput.ListAlive;
			break;
		case "vote":
			CLICommand = CLInput.Vote;
			break;
		case "showvote":
			CLICommand = CLInput.ShowVote;
			break;
		case "target":
			CLICommand = CLInput.Target;
			break;
		case "lastwill":
			CLICommand = CLInput.LastWill;
			break;
		case "showlastwill":
			CLICommand = CLInput.ShowLastWill;
		case "showdaily":
			CLICommand = CLInput.ShowDaily;
			break;
		case "showrole":
			CLICommand = CLInput.ShowRole;
		case "admin":
			CLICommand = CLInput.Admin;
			break;
		case "harrisisgay":
			CLICommand = CLInput.Admin;
			break;
		default:
			CLICommand = CLInput.BadInput;
		}
		return CLICommand;
	}
}
