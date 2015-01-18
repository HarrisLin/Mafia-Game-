package Enumerators;
/**
 * Sides of the role and player.
 * @author pacified
 *
 */
public enum Sides {
	Town("Town"), Mafia("Mafia"), Triad("Triad"), Neutral(
			"Neutral");

	//The side written out as a string
	private final String side;

	/** 
	 * @param side : String name of side
	 */
	Sides(String side) {
		this.side = side;
	}

	/**
	 * return side as a string
	 */
	public String toString() {
		return side;
	}
}
