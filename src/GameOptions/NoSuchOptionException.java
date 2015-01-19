package GameOptions;

public class NoSuchOptionException extends Exception {

	private static final long serialVersionUID = -1776610954942087234L;
	
	public NoSuchOptionException() {
		System.out.println("There is not such option");
	}

}
