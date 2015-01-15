package GameEngine;

import java.util.List;

import Character.Roles;

public abstract class Character {
	private String name;
	private Roles role;
	private boolean alive;
	
	protected List<Player> targets;

	protected Character(String name, Roles role){
		this.name = name;
		this.role = role;
		alive = true;
	}
	
	/**
	 * Kills the character; s/he will no longer be alive
	 */
	public void kill() {
		alive = false;
	}
	
	/**
	 * @return The name of the Character role
	 */
	public String getCharacterName() {
		return name;
	}
	
	public abstract boolean setTarget(List<Player> targets);
	public abstract String doAction();
}
