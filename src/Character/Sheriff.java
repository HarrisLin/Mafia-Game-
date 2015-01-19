package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import Enumerators.Sides;
import GameEngine.Character;
import GameEngine.Player;
import GameEngine.GameEngine;

/**
 * The Sheriff targets a Player and determines if they are Suspicious or Not
 * Suspicious
 */
public class Sheriff extends Character {
	// Game options
	boolean detectsMafia;
	boolean detectsArsonist;
	boolean detectsCultist;
	boolean detectsMassMurderer;

	Sheriff() {
		super(Roles.Sheriff);
		detectsMafia = true;
		detectsArsonist = true;
		detectsCultist = true;
		detectsMassMurderer = true;
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// The sheriff can only target a single Player
		if (targets.size() != 1
				|| !GameEngine.alive_player.containsAll(targets)) {
			return false;
		}
		this.targets = new ArrayList<Player>(targets);
		return true;
	}

	@Override
	public String doAction() {
		String result;
		Roles targetRole = GameEngine.getCharacter(targets.get(0))
				.getCharacterRole();
		if (detectsArsonist && targetRole == Roles.Arsonist) {
			result = "a arsonist";
		} else if (detectsCultist && targetRole == Roles.Cultist) {
			result = "a cultist";
		} else if (detectsMassMurderer && targetRole == Roles.MassMurderer) {
			result = "a mass murderer";
		} else if (detectsMafia && targetRole.getSide() == Sides.Mafia
				|| detectsMafia && targetRole.getSide() == Sides.Triad) {
			result = "suspicious";
		} else {
			result = "not suspicious";
		}
		return "The outcome of your results suggests your target is " + result
				+ ".";
	}
}