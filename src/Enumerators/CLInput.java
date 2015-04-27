package Enumerators;

public enum CLInput {
	Help, AddPlayer, RemovePlayer, StartGame, RebootGame, ResetGame, NewDay, ListPlayer, ListAlive, Vote, ShowVote, Target, LastWill, Daily, Admin, BadInput;

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
		case "daily":
			CLICommand = CLInput.Daily;
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
