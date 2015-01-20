package CLI;

/**
 * The MainMenu class displays the main menu to the user and waits for 
 * the user to select an option
 */
public class CLIMainMenu {
	private static final int ADD_PLAYER = 1;
	private static final int REMOVE_PLAYER = 2;
	private static final int LIST_PLAYERS = 3;
	private static final int EXIT = 4;
	private static final String[] MENU_OPTIONS = new String[]{	"Add Player",		//1	
																	"Remove Player",	//2
																	"List Players",		//3
																	"Exit"				//4
																};
	private static void printOptions() {
		System.out.println("");
		for (int i = 0; i < MENU_OPTIONS.length; i++) {
			System.out.println("(" + (i+1) + ") : " + MENU_OPTIONS[i]);
		}
		System.out.println("");
	}
	
	protected static CLIState run() {
		CLIState cli_state;
		while (true) {
			try{
				printOptions();
				int user_selection = CLI.readInt();
				
				switch (user_selection) {
				case ADD_PLAYER:
					cli_state = CLIState.ADD_PLAYER;
					return cli_state;
				case REMOVE_PLAYER:
					cli_state = CLIState.REMOVE_PLAYER;
					return cli_state;
				case LIST_PLAYERS:
					cli_state = CLIState.LIST_PLAYERS;
					return cli_state;
				case EXIT:
					cli_state = CLIState.EXIT;
					return cli_state;
				default:
					System.out.println(CLIStrings.INVALID_INPUT);
				}
			} catch (Exception e) {
				System.out.println(CLIStrings.INVALID_INPUT);
			}
		}
	}		
}