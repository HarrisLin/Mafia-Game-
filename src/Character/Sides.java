package Character;

public enum Sides {
	Town ("Town"), Mafia ("Mafia"), Triad ("Triad"), Neutral ("Neutral");
	
	private final String side;
	Sides(String side){
		this.side = side;
	}
	
	public String toString(){
		return side;
	}
}
