package GameEngine;

public class CannotGetPlayerException extends Exception {

	private static final long serialVersionUID = -5661076618585986320L;

	protected CannotGetPlayerException(String string) {
		System.out.println(string);
	}
}
