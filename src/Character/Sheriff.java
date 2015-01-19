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
	GameOptions.Options detectsMafia;
	GameOptions.Options detectsArsonist;
	GameOptions.Options detectsCultist;
	GameOptions.Options detectsMassMurderer;

	/**
	 * Default constructor that set options to default
	 */
	Sheriff() {
		super(Roles.Sheriff);
		detectsMafia = GameOptions.SheriffOptions.DetectMafia.DETECT_MAFIA_ON;
		detectsArsonist = GameOptions.SheriffOptions.DetectArsonist.DETECT_ARSONIST_ON;
		detectsCultist = GameOptions.SheriffOptions.DetectCultist.DETECT_CULTIST_ON;
		detectsMassMurderer = GameOptions.SheriffOptions.DetectMassMurderer.DETECT_MASSMURDERER_ON;
	}

	/**
	 * Constructors with options param
	 * 
	 * @param detectsMafia
	 * @param detectsArsonist
	 * @param detectsCultist
	 * @param detectsMassMurderer
	 */
	Sheriff(GameOptions.Options detectsMafia,
			GameOptions.Options detectsArsonist,
			GameOptions.Options detectsCultist,
			GameOptions.Options detectsMassMurderer) {
		super(Roles.Sheriff);
		this.detectsMafia = detectsMafia;
		this.detectsArsonist = detectsArsonist;
		this.detectsCultist = detectsCultist;
		this.detectsMassMurderer = detectsMassMurderer;
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
		if (detectsArsonist
				.equals(GameOptions.SheriffOptions.DetectArsonist.DETECT_ARSONIST_ON)
				&& targetRole.equals(Roles.Arsonist)) {
			result = "a arsonist";
		} else if (detectsCultist
				.equals(GameOptions.SheriffOptions.DetectCultist.DETECT_CULTIST_ON)
				&& targetRole.equals(Roles.Cultist)) {
			result = "a cultist";
		} else if (detectsMassMurderer
				.equals(GameOptions.SheriffOptions.DetectMassMurderer.DETECT_MASSMURDERER_ON)
				&& targetRole.equals(Roles.MassMurderer)) {
			result = "a mass murderer";
		} else if (detectsMafia
				.equals(GameOptions.SheriffOptions.DetectMafia.DETECT_MAFIA_ON)
				&& targetRole.getSide().equals(Sides.Mafia)
				|| detectsMafia
						.equals(GameOptions.SheriffOptions.DetectMafia.DETECT_MAFIA_ON)
				&& targetRole.getSide().equals(Sides.Triad)) {
			result = "suspicious";
		} else {
			result = "not suspicious";
		}
		return "The outcome of your results suggests your target is " + result
				+ ".";
	}
}