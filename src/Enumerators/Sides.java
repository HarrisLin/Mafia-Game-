package Enumerators;
/**
 * Sides of the role and player.
 * @author pacified
 *
 */
public enum Sides {
	Town("Town", false), Mafia("Mafia", true), Triad("Triad", true), Neutral(
			"Neutral", true);

	//The side written out as a string
	private final String side;
	//Determines if the side is suspicious for the sheriff
	private boolean suspicious;

	Sides(String side, boolean suspicious) {
		this.side = side;
		this.suspicious = suspicious;
	}

	public String toString() {
		return side;
	}

	public boolean suspicious() {
		return suspicious;
	}
}
