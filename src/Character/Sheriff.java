package Character;

import java.util.List;

import Enumerators.Roles;
import Enumerators.Sides;
import GameEngine.Character;
import GameEngine.NoSuchOptionException;
import GameEngine.Player;
import GameEngine.GameEngine;
import GameEngine.GameOptions;

/**
 * The Sheriff targets a Player and determines if they are Suspicious or Not
 * Suspicious
 */
public class Sheriff extends Character {

	boolean detectsMafia;
	boolean detectsTriad;
	boolean detectsArsonist;
	boolean detectsCultist;
	boolean detectsMassMurderer;

	Sheriff() {
		super(Roles.Sheriff);
		try {
			// Detects mafia
			detectsMafia = GameOptions.getOptions(this.getCharacterRole(), 1);
			// Detects triad
			detectsTriad = GameOptions.getOptions(this.getCharacterRole(), 2);
			// Detects arsonist
			detectsArsonist = GameOptions
					.getOptions(this.getCharacterRole(), 3);
			// Detects cultist
			detectsCultist = GameOptions.getOptions(this.getCharacterRole(), 4);
			// Detects mass murderer
			detectsMassMurderer = GameOptions.getOptions(
					this.getCharacterRole(), 5);
		} catch (NoSuchOptionException e) {
			detectsMafia = true;
			detectsTriad = true;
			detectsArsonist = true;
			detectsCultist = true;
			detectsMassMurderer = true;
		}
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		// The sheriff can only target a single Player
		if (targets.size() != 1) {
			return false;
		}
		this.targets = targets;
		return true;
	}

	@Override
	public String doAction() {
		String target_name = targets.get(0).getName();
		String result;

		Roles targetRole = GameEngine.getCharacter(targets.get(0))
				.getCharacterRole();
		if (detectsArsonist && targetRole == Roles.Arsonist) {
			result = "a arsonist";
		} else if (detectsCultist && targetRole == Roles.Cultist) {
			result = "a cultist";
		} else if (detectsMassMurderer && targetRole == Roles.MassMurderer) {
			result = "a mass murderer";
		} else if (detectsMafia && targetRole.getSide() == Sides.Mafia) {
			result = "suspicious";
		} else if (detectsTriad && targetRole.getSide() == Sides.Triad) {
			result = "suspicious";
		} else {
			result = "not suspicious";
		}
		return "Player " + target_name + " is " + result + ".";
	}
}