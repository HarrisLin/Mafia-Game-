package CLI;

public final class CLIStrings {
	protected static final String NEW_LINE = System.getProperty("line.separator");
	protected static final String INVALID_INPUT = "Not a valid input.";
	protected static final String ADD_PLAYER_PROMPT = "Enter the name of a new player:";
	protected static final String SUCCESSFULLY_ADDED_PLAYER(String name) {return "Successfully added player: " + name + ".";}
	protected static final String PLAYER_ALREADY_EXISTS(String name) {return "Player '" + name + "' is already registered.";}
	protected static final String LIST_OF_PLAYERS_PREAMBLE = "Registered players:";
	protected static final String REMOVE_PLAYER_PROMPT = "Enter the name of a player to be removed:";
	protected static final String SUCCESSFULLY_REMOVED_PLAYER(String name) { return "Successfully removed player: " + name + ".";}
	protected static final String PLAYER_DOES_NOT_EXIST(String name) { return "Player " + name + " has not been registered.";}
	protected static final String EXIT = "Goodbye!";
}
